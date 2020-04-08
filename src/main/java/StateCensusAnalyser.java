import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;

public class StateCensusAnalyser
{
    OpenCSV openCSV = new OpenCSV();
    public int readFile(String filePath) throws Exception
    {
        int totalNumberOfRecords= 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));)
        {
            Iterator<CSVStates> stateCSVIterator = openCSV.CSVfileIterator(reader, CSVStates.class);
            Iterable<CSVStates> csvIterable = () -> stateCSVIterator;
            int numOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfRecords;
        }
        catch (RuntimeException e)
        {
            throw new CustomExceptions("HEADER OR DELIMITER ERROR",CustomExceptions.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
        }
    }

    public Integer loadIndianStateCodeData (String csvFilePath) throws CustomExceptions {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            Iterator<CSVStates> statesCSVIterator = OpenCSV.CSVfileIterator(reader, CSVStates.class);
            Iterable<CSVStates> iterableStateCode = () -> statesCSVIterator;
            int countRecord = (int) StreamSupport.stream(iterableStateCode.spliterator(), false).count();
            return countRecord;
        }
         catch (NoSuchFileException e)
         {
                throw new CustomExceptions("NO_SUCH_FILE_FOUND..",CustomExceptions.TypeOfException.NO_FILE_FOUND);
         }
        catch (RuntimeException e)
        {
            throw new CustomExceptions("HEADER OR DELIMITER ERROR",CustomExceptions.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return (null);
    }

}