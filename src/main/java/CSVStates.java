import com.opencsv.bean.CsvBindByName;

public class CSVStates
{


    @CsvBindByName(column = "SrNo",required = true)
    public int SrNo;

    @CsvBindByName(column = "StateName",required = true)
    public String StateName;

    @CsvBindByName(column = "StateCode",required = true)
    public String StateCode;

    @CsvBindByName(column = "TIN",required = true)
    public int TIN;


}
