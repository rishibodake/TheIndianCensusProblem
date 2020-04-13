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


    @CsvBindByName(column = "State Id",required = true)
    public String StateID;

    @CsvBindByName(column = "StateName",required = true)
    public String StateName;

    @CsvBindByName(column = "Population Density",required = true)
    public long PopulationDensity;

    @CsvBindByName(column = "Population",required = true)
    public long Population;

    @CsvBindByName(column = "Total area",required = true)
    public long Area;

    @CsvBindByName(column = "Housing units",required = true)
    public String HousingUnits;

    @CsvBindByName(column = "Water area",required = true)
    public String WaterArea;

    @CsvBindByName(column = "Land Area",required = true)
    public String LandArea;


}
