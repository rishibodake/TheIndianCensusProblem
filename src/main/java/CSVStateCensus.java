import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus
{

    //binding the column names in CsvBindByName class
    @CsvBindByName(column = "State",required = true)
    public String StateName;

    @CsvBindByName(column = "Population",required = true)
    public long Population;

    @CsvBindByName(column = "AreaInSqKm",required = true)
    public int AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm",required = true)
    public int DensityPerSqKm;



    public  CSVStateCensus(String name, int population, int area, int density ){
        StateName=name;
        Population=population;
        AreaInSqKm=area;
        DensityPerSqKm= density;
    }

    @Override
    public String toString(){
        return "CSVStateCensus Data { " + "State Population : " +Population + ",State AreaInSqKm : " +AreaInSqKm + ",State DensityPerSqKm : " +DensityPerSqKm + "}";
    }

}
