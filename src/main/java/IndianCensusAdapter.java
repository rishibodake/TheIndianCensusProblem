import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndianCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException {
        Map<String, CensusDAO> censusDAOMap = super.loadCensusData(CSVStateCensus.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusDAOMap;
        return loadStateCodeCSVData(censusDAOMap, csvFilePath[1]);
    }

    private Map<String, CensusDAO> loadStateCodeCSVData(Map<String, CensusDAO> censusDAOMap, String csvFilePath) throws CSVBuilderException
    {
        String extension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!extension.equals("csv"))
        {
            throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfException.NO_FILE_FOUND);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            OpenCSV csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStates> stateCodeIterator = csvBuilder.getCSVIterator(reader, CSVStates.class);
            Iterable<CSVStates> stateCodes = () -> stateCodeIterator;
            StreamSupport.stream(stateCodes.spliterator(), false)
                    .filter(StateDataCSV -> censusDAOMap.get(StateDataCSV.StateName) != null)
                    .forEach(StateDataCSV -> censusDAOMap.get(StateDataCSV.StateName).StateCode = StateDataCSV.StateCode);
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