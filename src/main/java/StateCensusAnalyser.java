import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//State Code and State Census Classes in one class

    public class StateCensusAnalyser <E> {
        //VARIABLES
        private String CSV_FILE_PATH;
        List<CSVStateCensus> StateCensusCSVList = null;
        List<CSVStates> StateDataCSVList = null;

        Map<String, CSVStateCensus> StateCensusCSVMap = null;
        Map<String, CSVStates> StateDataCSVMap = null;

        OpenCSV openCSV = new OpenCSV();

        //parametersied Constructor
        public StateCensusAnalyser(String path, Class<E> csvClass)
        {
            this.CSV_FILE_PATH = path;
            this.StateCensusCSVMap = new HashMap<>();
            this.StateDataCSVMap = new HashMap<>();
        }
//Constructor without parameter
        public StateCensusAnalyser() {
            this.StateCensusCSVMap = new HashMap<>();
            this.StateDataCSVMap = new HashMap<>();
        }


        //METHOD TO LOAD RECORDS OF CSV FILE
        public int loadRecords(String path) throws CSVBuilderException {
            try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
                OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
                Iterator<CSVStateCensus> StateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
                while (StateCensusCSVIterator.hasNext())
                {
                    CSVStateCensus csvStatesCensus = StateCensusCSVIterator.next();
                    this.StateCensusCSVMap.put(csvStatesCensus.state, csvStatesCensus);
                    StateCensusCSVList = StateCensusCSVMap.values().stream().collect(Collectors.toList());
                }
                int numberOfRecords = StateCensusCSVMap.size();
                return numberOfRecords;
            }
            catch (NoSuchFileException e)
            {
                throw new CSVBuilderException("File Not Found", CSVBuilderException.TypeOfException.NO_FILE_FOUND);
            }
            catch (RuntimeException e)
            {
                throw new CSVBuilderException("Incorrect Delimiter or Header", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
            }
            catch (Exception e)
            {
                e.getStackTrace();
            }

            return 0;
        }

        //METHOD TO LOAD RECORDS OF STATE CODE
        public int loadData(String path) throws CSVBuilderException
        {
            try (Reader reader = Files.newBufferedReader(Paths.get(path)))
            {
                OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
                Iterator<CSVStates> StateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStates.class);
                while (StateCensusCSVIterator.hasNext())
                {
                    CSVStates StateData = StateCensusCSVIterator.next();
                    this.StateDataCSVMap.put(StateData.StateCode, StateData);
                    StateDataCSVList = StateDataCSVMap.values().stream().collect(Collectors.toList());
                }
                int numberOfRecords = StateDataCSVMap.size();
                return numberOfRecords;
            }
            catch (NoSuchFileException e)
            {
                throw new CSVBuilderException("Given File Not Found ", CSVBuilderException.TypeOfException.NO_FILE_FOUND);
            }
            catch (RuntimeException e)
            {
                throw new CSVBuilderException("Check Delimiters Or Headers", CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return 0;
        }

        public String SortedCensusData() throws CSVBuilderException
        {
            if (StateCensusCSVList == null || StateCensusCSVList.size() == 0)
            {
                throw new CSVBuilderException("Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
            }
            Comparator<CSVStateCensus> comparator = Comparator.comparing(StateCensusCSV -> StateCensusCSV.state);
            this.sortData(comparator, StateCensusCSVList);
            String sortedStateCensusJson = new Gson().toJson(StateCensusCSVList);
            return sortedStateCensusJson;
        }

        public String SortedStateCodeData() throws CSVBuilderException {
            if (StateDataCSVList == null || StateDataCSVList.size() == 0)
            {
                throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
            }
            Comparator<CSVStates> comparator = Comparator.comparing(CSVStates -> CSVStates.StateCode);
            this.sortData(comparator, StateDataCSVList);
            String sortedStateCodeJson = new Gson().toJson(StateDataCSVList);
            return sortedStateCodeJson;
        }



        //For Sorting the list
        private <E> void sortData(Comparator<E> csvComparator, List<E> list) {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                    E census1 = list.get(j);
                    E census2 = list.get(j + 1);
                    if (csvComparator.compare(census1, census2) > 0) {
                        list.set(j, census2);
                        list.set(j + 1, census1);
                    }
                }
            }
        }




    }
