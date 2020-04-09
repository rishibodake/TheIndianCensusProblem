import com.opencsv.bean.CsvBindByName;

public class CSVStates
{
    public CSVStates(String srNo, String stateName, String stateCode, String TIN)
    {
        SrNo = srNo;
        StateName = stateName;
        StateCode = stateCode;
        this.TIN = TIN;
    }

    @CsvBindByName(column = "SrNo",required = true)
    public String SrNo;

    @CsvBindByName(column = "StateName",required = true)
    public String StateName;

    @CsvBindByName(column = "StateCode",required = true)
    public String StateCode;

    @CsvBindByName(column = "TIN",required = true)
    public String TIN;

    public String getSrNo() {
        return SrNo;
    }
    public void setSrNo(String SrNo) {
        this.SrNo = SrNo;
    }
    public String getStateName() {
        return StateName;
    }
    public void setStateName(String StateName) {
        this.StateName = StateName;
    }
    public String getStateCode() {
        return StateCode;
    }
    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }
    public String getTIN() {
        return TIN;
    }
    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

}
