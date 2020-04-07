
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest
{
    public static String CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    public static String CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/StateCensus.csv";
    public static String CSV_FILE_PATH_FOR_WRONG_FILE_EXTENSION = "./src/test/resources/StateCensus.jpg";
    public static String CSV_STATES_CODE_FILE_PATH = "./src/test/resources/StateCode.csv";
    public static String CSV_WRONG_STATES_CODE_FILE_PATH = "./src/test/resources/WrongStateCode.csv";
    //test will pass when totalNumberOfRecords are 29
    @Test
    public void givenStateCensusCSV_WhenConditionTrue_ReturnNumberOfRecordMatch() throws IOException, CustomExceptions
    {
        StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH);
        int totalNumberOfRecords = censusAnalyserObject.loadData();
        Assert.assertEquals(29, totalNumberOfRecords);
    }

    //Test for improper file name
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() throws IOException
    {
        try
        {
            StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH_FOR_WRONG_FILE);
            censusAnalyserObject.loadData();
        }
        catch (CustomExceptions e)
        {
            Assert.assertEquals(CustomExceptions.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }
    }

    //Test Case 1.3 Name Correct but type Incorrect
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileExtension_ReturnsException() throws IOException
    {
        try
        {
            StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH_FOR_WRONG_FILE_EXTENSION);
            censusAnalyserObject.loadData();
        }
        catch (CustomExceptions e)
        {
            Assert.assertEquals(CustomExceptions.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }
    }

    //Test Case 1.4 Return Custom Exception for Incorrect Delimiter
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperDelimiter_ReturnsException() throws IOException
    {
        try
        {
            StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH);
            censusAnalyserObject.loadData();
        }
        catch (CustomExceptions e)
        {
            Assert.assertEquals(CustomExceptions.TypeOfException.INCORRECT_DELIMITER_EXCEPTION,e.typeOfException);
        }
    }

    //Test case 1.5 Return Custom Exception For Improper Header
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperHeader_ReturnsException() throws IOException
    {
        try
        {
            StateCensusAnalyser censusAnalyserObject = new StateCensusAnalyser(CSV_FILE_PATH);
            censusAnalyserObject.loadData();
        }
        catch (CustomExceptions e)
        {
            Assert.assertEquals(CustomExceptions.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.typeOfException);
        }
    }
    //Test case 2.1 Check Number Of Records are matches
    @Test
    public void givenStateCode_WhenTrue_ReturnNumberOfRecordMatch() throws IOException, CustomExceptions {
        StateDataCensusAnalyser csvStatesObject = new StateDataCensusAnalyser(CSV_STATES_CODE_FILE_PATH);
        int noOfRecords = csvStatesObject.loadStateCodeData();
        Assert.assertEquals(37, noOfRecords);
    }
    //Test Case 2.2 Test For Improper Name
    @Test
    public void givenStateCodeWhenFalse_ReturnExceptionFileNotFound() throws CustomExceptions
    {
        StateDataCensusAnalyser censusAnalyserObject = new StateDataCensusAnalyser(CSV_WRONG_STATES_CODE_FILE_PATH);
        try
        {
            censusAnalyserObject.loadStateCodeData();
        }
        catch (CustomExceptions e)
        {
            Assert.assertEquals(CustomExceptions.TypeOfException.NO_FILE_FOUND,e.typeOfException);
        }
    }
}
