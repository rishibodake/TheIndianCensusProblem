
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class StateCensusTest {

    //test will pass when totalNumberOfRecords are 29
    @Test
    public void givenNumberOfRecords_WhenMatched_ShouldReturnTrue() {
        String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
            int numberOfRecords = stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (CSVBuilderException e) {

        }
    }

    //Test for improper file name
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFile_ReturnsException() {
        String CSV_FILE_PATH = "src/test/resources/stateCensus.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
        }
    }

    //Test Case 1.3 Name Correct but type Incorrect
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() {
        String CSV_FILE_PATH = "src/test/resources/StateCensus.jpg";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
        }
    }

    //Test Case 1.4 Return Custom Exception for Incorrect Delimiter
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperDelimiter_ReturnsException() {

        String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION, e.typeOfException);
        }
    }

    //Test case 1.5 Return Custom Exception For Improper Header
    @Test
    public void givenStateCensusAnalyserFile_WhenImproperHeader_ReturnsException() {
        String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION, e.typeOfException);
        }
    }

    //Test case 2.1 Check Number Of Records are matches
    @Test
    public void givenStateCode_WhenTrue_ReturnNumberOfRecordMatch() {
        String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
            int numberOfRecords = stateCensusAnalyzer.loadStateData(CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (CSVBuilderException e) {

        }

    }

    //TestCase 2.2 Test For Improper Name
    @Test
    public void givenStateCodeWhenFalse_ReturnExceptionFileNotFound() {
        String CSV_FILE_PATH = "src/test/resources/WrongNameForStateCode.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadStateData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
        }
    }

    //TestCase 2.3 Test For Improper Extension
    @Test
    public void givenStateCodeExtension_WhenFalse_ReturnExceptionFileNotFound() {
        String CSV_FILE_PATH = "src/test/resources/StateCode.jpg";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadStateData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_FILE_FOUND, e.typeOfException);
        }
    }

    //TestCase 2.4 Test For Improper Delimiter
    @Test
    public void givenStateCode_WhenImproperDelimiter_ReturnExceptionFileNotFound() {
        String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadStateData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION, e.typeOfException);
        }
    }

    //TestCase 2.5 Test For Improper Header
    @Test
    public void givenStateCode_WhenImproperHeader_ReturnExceptionFileNotFound() {
        String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        try {
            stateCensusAnalyzer.loadStateData(CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION, e.typeOfException);
        }
    }

    //UC3 test for sorted data
    @Test
    public void givenIndianStateCensusData_WhenSorted_ReturnSortedResult() {
        String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedStateCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(SortedData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].StateName);
        } catch (CSVBuilderException e) {

        }
    }

    //UC4 test for Sorting StateCodes
    @Test
    public void givenStateCodeData_WhenSorted_ShouldReturnSortedList() {
        final String CSV_FILE_PATH = "src/test/resources/StateCode.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
            String SortedData = stateCensusAnalyzer.SortedStateCensusData();
            CSVStates[] StateCodes = new Gson().fromJson(SortedData, CSVStates[].class);
            Assert.assertEquals("AD", StateCodes[0].StateCode);
        } catch (CSVBuilderException e) {

        }
    }

    //TEST FOR SORTING POPULATION WISE
    @Test
    public void givenStateCensusData_WhenBasedSortSorted_ReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.sortedPopulationWiseData();
            CSVStateCensus[] statesCensusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, statesCensusCSV[0].Population);
        } catch (CSVBuilderException e) {
            e.getStackTrace();
        }
    }

    //TEST FOR SORTING DENSITY WISE
    @Test
    public void givenStateCensusData_WhenSortByDensity_ReturnSortedResult() throws CSVBuilderException {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.sortedDataDensityWise();
            CSVStateCensus[] stateCensusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(1102, stateCensusCSV[0].DensityPerSqKm);
        } catch (CSVBuilderException e) {
            e.getStackTrace();

        }
    }

    //TEST FOR SORTING STATES AREA WISE
    @Test
    public void givenStateCensusData_WhenSortByArea_ReturnSortedResult() {
        final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
        try {
            StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();

            stateCensusAnalyzer.loadCensusData(CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyzer.sortedDataAreaWise();
            CSVStateCensus[] stateCensusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(342239, stateCensusCSV[0].AreaInSqKm);
        } catch ( CSVBuilderException e) {
            e.getStackTrace();
        }
    }
//TEST TO LOAD USA CENSUS DATA
    @Test
    public void givenUSCensusData_WhenTrue_RecordShouldMatch() throws CSVBuilderException {
        StateCensusAnalyser stateCensusAnalyzer = new StateCensusAnalyser();
        final String CSV_FILE_PATH = "src/test/resources/USCensusData.csv";
        int noOfRecords = stateCensusAnalyzer.loadUSCensusCSVData(CSV_FILE_PATH);
        Assert.assertEquals(51, noOfRecords);
    }
}
