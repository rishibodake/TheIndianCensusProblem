import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser <E>{

    public enum Country {INDIA, US}

    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusDAOMap = null;

    //CONSTRUCTOR
    public CensusAnalyser() {
        this.censusDAOMap = new HashMap<>();
        this.censusList = new ArrayList<>();
    }
    //METHOD TO LOAD RECORDS OF CSV FILE
    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws CSVBuilderException
    {
        censusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        return censusDAOMap.size();
    }

    //METHOD TO LOAD STATE CODE CSV DATA AND COUNT NUMBER OF RECORD IN CSV FILE
    public String getSortedStateCensusData() throws CSVBuilderException
    {
        if (censusList == null || censusList.size() == 0)
        {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.StateName);
        this.sortData(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    public String sortedDataPopulationWise() throws CSVBuilderException
    {
        if (censusList == null || censusList.size() == 0)
        {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(CensusDAO -> CensusDAO.Population);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD FOR SORTING STATE CENSUS DATA CSV FILE BY DENSITY WISE
    public String sortedDataDensityWise() throws CSVBuilderException
    {
        if (censusList == null || censusList.size() == 0) {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.DensityPerSqkm);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD FOR SORTING STATE CENSUS DATA CSV FILE BY AREA WISE
    public String sortedDataAreaWise() throws CSVBuilderException
    {
        if (censusList == null || censusList.size() == 0)
        {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.AreaInSqKm);
        this.sortData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    private void sortData(Comparator<CensusDAO> csvComparator)
    {
        for (int i = 0; i < censusList.size() - 1; i++)
        {
            for (int j = 0; j < censusList.size() - i - 1; j++)
            {
                CensusDAO census1 = censusList.get(j);
                CensusDAO census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1,census2) > 0)
                {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }
            }
        }
    }
}