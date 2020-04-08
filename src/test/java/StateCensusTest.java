
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest
{

    StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser();
    //test will pass when totalNumberOfRecords are 29
    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws Exception
    {
        Integer noOfRecords = censusAnalyserObject.readFile("./src/test/resources/StateCensusData.csv");
        Assert.assertEquals((Integer) 29, noOfRecords);
    }

    //Test for improper file name
   @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws Exception
    {
        try
        {
            censusAnalyserObject.readFile("src/test/resources/StateCensus.csv");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }
    }

    //Test Case 1.3 Name Correct but type Incorrect
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileExtension_ReturnsException() throws Exception
    {
        try
        {
            censusAnalyserObject.readFile("src/test/resources/StateCensusData.jpg");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }
    }

   //Test Case 1.4 Return Custom Exception for Incorrect Delimiter
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperDelimiter_ReturnsException() throws Exception
    {
        try
        {
            censusAnalyserObject.readFile("src/test/resources/StateCensusData.csv");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.typeOfException);
        }
    }

    //Test case 1.5 Return Custom Exception For Improper Header
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperHeader_ReturnsException() throws Exception
    {
        try
        {
            censusAnalyserObject.readFile("src/test/resources/StateCensusData.csv");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.typeOfException);
        }
    }
    //Test case 2.1 Check Number Of Records are matches
    @Test
    public void givenStateCode_WhenTrue_ReturnNumberOfRecordMatch() throws Exception
    {
        Integer result = censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        Assert.assertEquals((Integer) 37, result);

    }
   //TestCase 2.2 Test For Improper Name
    @Test
    public void givenStateCodeWhenFalse_ReturnExceptionFileNotFound()
    {
        try
        {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCodeWrong.csv");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }
    }
   //TestCase 2.3 Test For Improper Extension
    @Test
    public void givenStateCodeExtension_WhenFalse_ReturnExceptionFileNotFound()
    {
        try
        {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCode.jpg");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }
    }
    //TestCase 2.4 Test For Improper Delimiter
    @Test
    public void givenStateCode_WhenImproperDelimiter_ReturnExceptionFileNotFound()
    {
        try
        {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.typeOfException);
        }
    }
   //TestCase 2.5 Test For Improper Header
    @Test
    public void givenStateCode_WhenImproperHeader_ReturnExceptionFileNotFound()
    {
        try
        {
            censusAnalyserObject.loadIndianStateCodeData("src/test/resources/StateCode.csv");
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.typeOfException);
        }
    }

}
