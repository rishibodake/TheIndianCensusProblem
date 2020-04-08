import com.opencsv.bean.CsvBindByName;

public class CSVStates
{
    @CsvBindByName(column = "SrNo",required = true)
    private String SrNo;

    @CsvBindByName(column = "StateName",required = true)
    private String StateName;

    @CsvBindByName(column = "StateCode",required = true)
    private String StateCode;

    @CsvBindByName(column = "TIN",required = true)
    private String TIN;

}
