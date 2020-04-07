import com.opencsv.bean.CsvToBean;
        import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import static java.nio.file.Files.newBufferedReader;

public class StateDataCensusAnalyser
{
    private static String CSV_STATE_CODE_FILE_PATH = "./src/test/resources/StateCode.csv";
    int totalNumberOfRecords = 0;
    public StateDataCensusAnalyser(String path)
    {
        CSV_STATE_CODE_FILE_PATH = path;
    }

    public int loadStateCodeData() throws CustomExceptions
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_STATE_CODE_FILE_PATH));)
        {
            CsvToBean<CSVStates> csvStateCensuses = new CsvToBeanBuilder(reader)
                    .withType(CSVStates.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStates> csvStatesIterator = csvStateCensuses.iterator();
            while (csvStatesIterator.hasNext()) {
                CSVStates csvStates = csvStatesIterator.next();
                System.out.println("SrNo :" +csvStates.getSrNo());
                System.out.println("StateName :" +csvStates.getStateName());
                System.out.println("TIN :" +csvStates.getTIN());
                System.out.println("StateCode :" +csvStates.getStateCode());
                totalNumberOfRecords++;
            }
        } catch (IOException e)
        {
            throw new CustomExceptions(CustomExceptions.TypeOfException.NO_FILE_FOUND);
        }
        return totalNumberOfRecords;
    }
}
