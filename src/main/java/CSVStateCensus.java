import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus
{
    //binding the column names in CsvBindByName class
    @CsvBindByName(column = "State",required = true)
    private String state;

    @CsvBindByName(column = "Population",required = true)
    private String population;

    @CsvBindByName(column = "AreaInSqKm",required = true)
    private String AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm",required = true)
    private String DensityPerSqKm;
}
