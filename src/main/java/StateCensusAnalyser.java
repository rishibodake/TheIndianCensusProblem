import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.nio.file.Files.newBufferedReader;
//State Code and State Census Classes in one class

    public class StateCensusAnalyser <E>
    {
        //VARIABLES
        private String CSV_FILE_PATH;
        private final Class<E> csvClass;
        List<E> csvUserList = null;
        OpenCSV openCSV = new OpenCSV();

        public StateCensusAnalyser(String path, Class<E> csvClass) {
            this.CSV_FILE_PATH = path;
            this.csvClass = csvClass;
        }


        //METHOD TO LOAD RECORDS OF CSV FILE
        public int loadRecords() throws CSVBuilderException {
            try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
                OpenCSV csvBuilder = CSVBuilderFactory.createCsvBuilder();
                csvUserList = csvBuilder.getCSVFileList(reader, csvClass);
                return csvUserList.size();
            } catch (NoSuchFileException e) {
                throw new CSVBuilderException(e.getMessage(), CSVBuilderException.TypeOfException.NO_FILE_FOUND);
            } catch (RuntimeException e) {
                throw new CSVBuilderException(e.getMessage(), CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
            } catch (Exception e) {
                e.getStackTrace();
            }

            return 0;
        }

            public String SortedCensusData ()
            {
                Comparator<CSVStateCensus> CSVComparator = Comparator.comparing(census -> census.state);
                this.sort((Comparator<E>) CSVComparator);
                String SortedCSVJson = new Gson().toJson(csvUserList);
                return SortedCSVJson;
            }

            public String SortedStateCodeData() {
            Comparator<CSVStates> CodeComparator = Comparator.comparing(code -> code.StateCode);
            this.sort((Comparator<E>) CodeComparator);
            String SortedCodeJson = new Gson().toJson(csvUserList);
            return SortedCodeJson;
        }
//For Sorting the list
            private void sort (Comparator < E > csvComparator)
            {
                for (int i = 0; i < csvUserList.size() - 1; i++) {
                    for (int j = 0; j < csvUserList.size() - i - 1; j++) {
                        E census1 = csvUserList.get(j);
                        E census2 = csvUserList.get(j + 1);
                        if (csvComparator.compare(census1, census2) > 0) {
                            csvUserList.set(j, census2);
                            csvUserList.set(j + 1, census1);
                        }
                    }
                }
            }
    }
