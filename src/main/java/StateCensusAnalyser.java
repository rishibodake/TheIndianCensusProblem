import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;
//State Code and State Census Classes in one class
public class StateCensusAnalyser
{
    OpenCSV openCSV = new OpenCSV();
    public Integer readFile(String filePath) throws Exception //it will load data from StateCensusDada.csv
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath)) )
        {
            List<CSVStates> listCSVfile = openCSV.getCSVFileList(reader,CSVStates.class);
            return listCSVfile.size();
        }
        catch (NoSuchFileException e)
        {
            throw new CSVBuilderException("FILE IS NOT FOUND.",CSVBuilderException.TypeOfException.NO_FILE_FOUND);
        }
        catch (RuntimeException e)
        {
            throw new CSVBuilderException("DELIMITER OR HEADER INCORRECT..",CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return (null);
    }



    public Integer loadIndianStateCodeData (String csvFilePath) throws CSVBuilderException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)) )
        {
            List<CSVStates> listCSVfile = openCSV.getCSVFileList(reader,CSVStates.class);
            return listCSVfile.size();
        }
        catch (NoSuchFileException e)
        {
            throw new CSVBuilderException("ENTERED FILE IS NOT FOUND..",CSVBuilderException.TypeOfException.NO_FILE_FOUND);
        }
        catch (RuntimeException e)
        {
            throw new CSVBuilderException("DELIMITER OR HEADER INCORRECT..",CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
            return null;
        }
    }

