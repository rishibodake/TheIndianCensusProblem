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

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String SrNo)
    {
        this.SrNo = SrNo;
    }

    public String getStateName()
    {
        return StateName;
    }

    public void setStateName(String StateName)
    {
        this.StateName = StateName;
    }

    public String getStateCode()
    {
        return StateCode;
    }

    public void setStateCode(String StateCode)
    {
        this.StateCode = StateCode;
    }

    public String getTIN()
    {
        return TIN;
    }

    public void setTIN(String TIN)
    {
        this.TIN = TIN;
    }
}
