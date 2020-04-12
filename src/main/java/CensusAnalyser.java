import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser <E>{

    public enum Country {INDIA, US}
    public enum SortingMode { STATENAME, POPULATION, DENSITY, AREA }

    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusDAOMap = null;

    private Country country;

    //CONSTRUCTOR
    public CensusAnalyser(Country country) {
        this.country = country;
    }
    //METHOD TO LOAD RECORDS OF CSV FILE
    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws CSVBuilderException
    {
        censusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        return censusDAOMap.size();
    }

    //METHOD TO LOAD STATE CODE CSV DATA AND COUNT NUMBER OF RECORD IN CSV FILE
    public String sortedStateCensusData(SortingMode mode) throws CSVBuilderException
    {
        if (censusList == null || censusList.size() == 0)
        {
            throw new CSVBuilderException( "Census Data Not Found", CSVBuilderException.TypeOfException.CENSUS_DATA_NOT_FOUND_EXCEPTION);
        }
        ArrayList arrayList = censusDAOMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);
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