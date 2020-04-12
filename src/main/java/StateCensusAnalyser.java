/* com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static java.nio.file.Files.newBufferedReader;


//State Code and State Census Classes in one class

    public class StateCensusAnalyser <E> {
        //VARIABLES
        private String CSV_FILE_PATH;
        List<CensusDAO> list = null;
        Map<String, CensusDAO> map = null;
        OpenCSV openCSV = new OpenCSV();

//Constructor without parameter
        public StateCensusAnalyser() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();

        }


        //METHOD TO LOAD RECORDS OF CSV FILE
        public int loadCensusData(String path) throws CSVBuilderException {
            int numberOfRecords = 0;
            String extension = path.substring(path.lastIndexOf(".") + 1);
            if (!extension.equals("csv"))
            {
                throw new CSVBuilderException("given file has wrong extension",CSVBuilderException.TypeOfException.NO_FILE_FOUND);
            }
            try (Reader reader = Files.newBufferedReader(Paths.get(path)))
            {
                OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
                Iterator<CSVStateCensus> StateCensusCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
                Iterable<CSVStateCensus> stateCensusIterable = () -> StateCensusCSVIterator;
                StreamSupport.stream(stateCensusIterable.spliterator(), false)
                        .forEach(stateCensusCSV -> map.put(stateCensusCSV.StateName, new CensusDAO(densityPerSqKm, stateCensusCSV)));
                list = map.values().stream().collect(Collectors.toList());
                numberOfRecords=map.size();
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

            return numberOfRecords;
        }

        //METHOD TO LOAD RECORDS OF STATE CODE
        public int loadStateData(String path) throws CSVBuilderException
        {
            int numberOfRecords=0;
            String extension = path.substring(path.lastIndexOf(".") + 1);
            if (!extension.equals("csv"))
            {
                throw new CSVBuilderException("given file has wrong extension",CSVBuilderException.TypeOfException.NO_FILE_FOUND);
            }
            try (Reader reader = Files.newBufferedReader(Paths.get(path)))
            {
                OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
                Iterator<CSVStates> stateDataCSVIterator = csvBuilder.getCSVFileIterator(reader, CSVStates.class);
                Iterable<CSVStates> stateCodeIterable = () -> stateDataCSVIterator;
                StreamSupport.stream(stateCodeIterable.spliterator(), false)
                        .filter(stateDataCSV -> map.get(stateDataCSV.StateName) != null)
                        .forEach(stateDataCSV -> map.get(stateDataCSV.StateName).StateCode = stateDataCSV.StateCode);

                numberOfRecords = map.size();
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
            return numberOfRecords;
        }
//Function that can load CSV data from USCensusData.csv
        public int loadUSCensusCSVData(String path) throws CSVBuilderException
        {
            int noOfRecords = 0;
            try (Reader reader = newBufferedReader(Paths.get(path)))
            {
                CsvToBean<USCensusCSV> usCensusCSV = new CsvToBeanBuilder(reader)
                        .withType(USCensusCSV.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                Iterator<USCensusCSV> usCensusIterator = usCensusCSV.iterator();
                while (usCensusIterator.hasNext())
                {
                    USCensusCSV USCensus = usCensusIterator.next();
                    System.out.println("State ID: " + USCensus.StateID);
                    System.out.println("State Name : " + USCensus.State);
                    System.out.println("Area : " + USCensus.Area);
                    System.out.println("Housing units : " + USCensus.HousingUnits);
                    System.out.println("Water area : " + USCensus.WaterArea);
                    System.out.println("Land Area : " + USCensus.LandArea);
                    System.out.println("Density : " + USCensus.PopulationDensity);
                    System.out.println("Population : " + USCensus.Population);
                    System.out.println("Housing Density : " + USCensus.HousingDensity);
                    noOfRecords++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return noOfRecords;
        }


        public String SortedStateCensusData() throws CSVBuilderException
        {
            if (list == null || list.size() == 0)
            {
                throw new CSVBuilderException("Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
            }
            Comparator<CensusDAO> comparator = Comparator.comparing(CensusDAO -> CensusDAO.Population);
            this.sortData(comparator);
            String sortedStateCensusJson = new Gson().toJson(list);
            return sortedStateCensusJson;
        }
//Function that sort data Pupulation Wise
        public String sortedPopulationWiseData() throws CSVBuilderException {
            if (list == null || list.size() == 0)
            {
                throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
            }
            Comparator<CensusDAO> censusComparator = Comparator.comparing(CensusDAO -> CensusDAO.Population);
            this.sortData(censusComparator);
            Collections.reverse(list);
            String sortedStateCodeJson = new Gson().toJson(list);
            return sortedStateCodeJson;
        }
//Function that can sort data density wise
        public String sortedDataDensityWise() throws CSVBuilderException {
            if (list == null || list.size() == 0)
            {
                throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
            }
            Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.DensityPerSqkm);
            this.sortData(censusComparator);
            Collections.reverse(list);
            String sortedStateCensusJson = new Gson().toJson(list);
            return sortedStateCensusJson;
        }
//Function that can sort states area wise
        public String sortedDataAreaWise() throws CSVBuilderException {
            if (list == null || list.size() == 0)
            {
                throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
            }
            Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.AreaInSqKm);
            this.sortData(censusComparator);
            Collections.reverse(list);
            String sortedStateCensusJson = new Gson().toJson(list);
            return sortedStateCensusJson;
        }



        //For Sorting the list
        private void sortData(Comparator<CensusDAO> csvComparator)
        {
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                    CensusDAO census1 = list.get(j);
                    CensusDAO census2 = list.get(j + 1);
                    if (csvComparator.compare(census1,census2) > 0) {
                        list.set(j, census2);
                        list.set(j + 1, census1);
                    }
                }
            }
        }




    }
*/