
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest
{
    //test will pass when totalNumberOfRecords are 29
    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws IOException {
        StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser();
        int totalNumberOfRecords = censusAnalyserObject.loadData();
        Assert.assertEquals(29, totalNumberOfRecords);
    }
}
