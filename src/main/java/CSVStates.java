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

    public CSVStates(int no, String name, String code, int tin){
        SrNo=no;
        StateName=name;
        StateCode=code;
        TIN=tin;
    }

    public String toString(){
        return "CSVStates{ " + "State Number :" +SrNo + ", State Name : " +StateName + ", State Code : " +StateCode + ", TIN : " +TIN + "}";
    }

}
