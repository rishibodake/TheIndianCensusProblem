import java.io.IOException;
import java.io.Reader;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;
import static java.nio.file.Files.newBufferedReader;

public abstract class CensusAdapter {
    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) throws CSVBuilderException
    {
        Map<String, CensusDAO> censusDAOMap = new HashMap<>();
        try (Reader reader = newBufferedReader(Paths.get(csvFilePath));)
        {
            OpenCSV csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getCSVIterator(reader, censusCSVClass);
            Iterable<E> stateCensuses = () -> stateCensusIterator;
            if (censusCSVClass.getName().contains("CsvStatesCensus"))
            {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(CSVStateCensus.class::cast)
                        .forEach(censusCSV -> { censusDAOMap.put(censusCSV.StateName, new CensusDAO(censusCSV));
                        });
            } else if (censusCSVClass.getName().contains("CSVUSCensus"))
            {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.State, new CensusDAO(censusCSV)));
            }
            return censusDAOMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfException.NO_FILE_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("Check Delimiters Or Headers", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return censusDAOMap;
    }
}