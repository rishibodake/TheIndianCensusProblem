
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest
{
    public static String CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    public static String CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/StateCensus.csv";
    //test will pass when totalNumberOfRecords are 29
    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws IOException, CustomExceptions
    {
        StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH);
        int totalNumberOfRecords = censusAnalyserObject.loadData();
        Assert.assertEquals(29, totalNumberOfRecords);
    }

    //Test will pass only after giving proper name to file
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws IOException,CustomExceptions
    {
        try
        {
           // StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH);
            StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH_FOR_WRONG_FILE);
            censusAnalyserObject.loadData();
        }
        catch (CustomExceptions e)
        {
            Assert.assertEquals(CustomExceptions.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }

    }
}
