
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest
{

    //test will pass when totalNumberOfRecords are 29
    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue()
    {
        String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
            int numberOfRecords = stateCensusAnalyzer.loadRecords();
            Assert.assertEquals(29, numberOfRecords);
        }
        catch(CSVBuilderException e)
        {

        }
    }

    //Test for improper file name
    @Test
   public void givenStateCensusAnalyserFile_WhenImproperFile_ReturnsException()
    {
         final String CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
         StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
         try
         {
            stateCensusAnalyzer.loadRecords();
         }
         catch (CSVBuilderException e)
         {
             Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
         }
     }

    //Test Case 1.3 Name Correct but type Incorrect
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException()
    {
         String CSV_FILE_PATH = "src/test/resources/StateCensus.jpg";
         StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
         try
         {
            stateCensusAnalyzer.loadRecords();
         }
         catch (CSVBuilderException e)
         {
             Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
         }
     }
   //Test Case 1.4 Return Custom Exception for Incorrect Delimiter
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperDelimiter_ReturnsException() throws Exception
    {

         String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
         StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
         try
         {
            stateCensusAnalyzer.loadRecords();
         }
         catch (CSVBuilderException e)
         {
             Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION, e.typeOfException);
         }
    }

    //Test case 1.5 Return Custom Exception For Improper Header
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperHeader_ReturnsException() throws Exception
    {
        String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
         StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
         try
         {
            stateCensusAnalyzer.loadRecords();
         }
         catch (CSVBuilderException e)
         {
             Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION, e.typeOfException);
         }
    }
    //Test case 2.1 Check Number Of Records are matches
    @Test
    public void givenStateCode_WhenTrue_ReturnNumberOfRecordMatch() throws Exception
    {
       String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
         try
         {
             StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStates.class);
             int numberOfRecords = stateCensusAnalyzer.loadData();
             Assert.assertEquals(37, numberOfRecords);
         }
         catch (CSVBuilderException e)
         {

         }

    }
   //TestCase 2.2 Test For Improper Name
    @Test
    public void givenStateCodeWhenFalse_ReturnExceptionFileNotFound()
    {
        String CSV_FILE_PATH = "src/test/resources/WrongNameForStateCode.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStates.class);
        try {
            stateCensusAnalyzer.loadRecords();
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
        }
    }
   //TestCase 2.3 Test For Improper Extension
    @Test
    public void givenStateCodeExtension_WhenFalse_ReturnExceptionFileNotFound()
    {
        String CSV_FILE_PATH = "src/test/resources/StateCode.jpg";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStates.class);
        try {
            stateCensusAnalyzer.loadRecords();
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
        }
    }
    //TestCase 2.4 Test For Improper Delimiter
    @Test
    public void givenStateCode_WhenImproperDelimiter_ReturnExceptionFileNotFound()
    {
        String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStates.class);
        try
        {
            stateCensusAnalyzer.loadRecords();
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
        String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStates.class);
        try
        {
            stateCensusAnalyzer.loadRecords();
        }
        catch (CSVBuilderException e)
        {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION, e.typeOfException);
        }
    }
//UC3 test for sorted data
    @Test
    public void givenIndianStateCensusData_WhenSorted_ReturnSortedResult()
    {
         String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
         try
        {
             StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStateCensus.class);
             stateCensusAnalyzer.loadRecords();
             String SortedData = stateCensusAnalyzer.SortedCensusData();
             CSVStateCensus[] censusCSV = new Gson().fromJson(SortedData, CSVStateCensus[].class);
             Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        }
        catch (CSVBuilderException e)
        {

        }
    }
//UC4 test for Sorting StateCodes
    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList()
    {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try
        {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser(CSV_FILE_PATH, CSVStates.class);
            stateCensusAnalyzer.loadRecords();
            String SortedData = stateCensusAnalyzer.SortedStateCodeData();
            CSVStates[] StateCodes = new Gson().fromJson(SortedData, CSVStates[].class);
            Assert.assertEquals("AD", StateCodes[0].StateCode);
        }
        catch (CSVBuilderException e)
        {

        }
    }

}
