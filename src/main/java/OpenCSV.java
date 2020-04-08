import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
class OpenCSV extends CSVInterface {
    public static <E> Iterator<E> CSVfileIterator(Reader reader, Class<E> csvClass) throws CustomExceptions {
        return getCSVToBeen(reader,csvClass).iterator();
    }

    public static <E> CsvToBean<E> getCSVToBeen(Reader reader, Class<E> csvClass) throws CustomExceptions {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean;
        } catch (RuntimeException e) {
            throw new CustomExceptions("DELIMITER OR HEADER INCORRECT..",CustomExceptions.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION);
        }
    }
}
