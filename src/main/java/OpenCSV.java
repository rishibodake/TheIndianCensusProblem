import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

//Concrete class

class OpenCSV implements CSVInterface
{
    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException
    {
        try {
                CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
                return csvToBean.iterator();
        }
        catch (IllegalStateException e)
        {
            throw new CSVBuilderException("Parsing is failed ", CSVBuilderException.TypeOfException.UNABLE_TO_PARSE_EXCEPTION);
        }
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException
    {
        try {
                CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
                return csvToBean.parse();
            }
        catch(IllegalStateException e)
            {
                throw new CSVBuilderException("Parsing is failed ", CSVBuilderException.TypeOfException.UNABLE_TO_PARSE_EXCEPTION);
            }
    }
}

