import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus
{

    //binding the column names in CsvBindByName class
    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Population",required = true)
    public String population;

    @CsvBindByName(column = "AreaInSqKm",required = true)
    public int AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm",required = true)
    public int DensityPerSqKm;

}
