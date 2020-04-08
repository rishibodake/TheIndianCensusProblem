import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
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

    public String getStateWiseSortedData(String path) throws CSVBuilderException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(path)))
        {
            List<CSVStateCensus> listCSVfile = (List<CSVStateCensus>) openCSV.getCSVFileIterator(reader,CSVStateCensus.class);
            Comparator<CSVStateCensus> censusComparator = Comparator.comparing(Census -> Census.getState());
            this.sort(listCSVfile, censusComparator);
            String sortedStateCensusjson = new Gson().toJson(listCSVfile);
            return sortedStateCensusjson;

        }
        catch (NoSuchFileException e)
        {
            throw new CSVBuilderException("ENTERED FILE IS NOT FOUND..",CSVBuilderException.TypeOfException.NO_FILE_FOUND);
        }
        catch (RuntimeException e)
        {
            throw new CSVBuilderException("DELIMITER OR HEADER INCORRECT..",CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
        }
        catch ( IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
//For Sorting the list
    private void sort(List<CSVStateCensus> censusList, Comparator<CSVStateCensus> censusComparator)
    {
        for (int i = 0; i < censusList.size()-1; i++)
        {
            for(int j = 0; j < censusList.size()-i-1; j++)
            {
                CSVStateCensus census1 = censusList.get(j);
                CSVStateCensus census2= censusList.get(j+1);
                if(censusComparator.compare(census1,census2) > 0)
                {
                    censusList.set(j, census2);
                    censusList.set(j+1, census1);
                }
            }
        }
    }
    }

