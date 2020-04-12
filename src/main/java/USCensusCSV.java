import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    public USCensusCSV(String stateID,String name,long population, long area, long populationDensity){
        StateName=name;
        Population = population;
        Area = area;
        PopulationDensity = populationDensity;
        StateID=stateID;
    }

    @Override
    public String toString() {
        return "USCensusCSV{" + "State=" + StateName + "Population=" + Population + ", Area=" + Area + ", PopulationDensity=" + PopulationDensity + '}';
    }


    @CsvBindByName(column = "State Id")
    public String StateID;

    @CsvBindByName(column = "StateName")
    public String StateName;

    @CsvBindByName(column = "Population Density")
    public long PopulationDensity;

    @CsvBindByName(column = "Population")
    public long Population;

    @CsvBindByName(column = "Total area")
    public long Area;

    @CsvBindByName(column = "Housing units")
    public String HousingUnits;

    @CsvBindByName(column = "Water area")
    public String WaterArea;

    @CsvBindByName(column = "Land Area")
    public String LandArea;

    @CsvBindByName(column = "Housing Density")
    public float HousingDensity;

}
