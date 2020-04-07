import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Iterator;

import static java.nio.file.Files.newBufferedReader;

public class StateCensusAnalyser
{
    public static String CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";

    int totalNumberOfRecords=0;
    public int loadData() throws IOException {
        try (Reader reader = newBufferedReader(Paths.get(CSV_FILE_PATH));)
        {
            CsvToBean<CSVStateCensus> csvStateCensusBeanObj = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStateCensus> stateCensusCSVIterator = csvStateCensusBeanObj.iterator();

            while (stateCensusCSVIterator.hasNext())
            {
                CSVStateCensus stateCensusCSV = stateCensusCSVIterator.next();
                System.out.println("State: " +stateCensusCSV.getState());
                System.out.println("Area: " +stateCensusCSV.getAreaInSqKm());
                System.out.println("Density: " +stateCensusCSV.getDensityPerSqKm());
                System.out.println("Population: " +stateCensusCSV.getPopulation());
                totalNumberOfRecords++;
            }
        }
        return totalNumberOfRecords;
    }
}