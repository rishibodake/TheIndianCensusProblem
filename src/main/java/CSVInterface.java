import java.io.Reader;
import java.util.Iterator;
import java.util.List;

//Should have Introduced in Refactor 3 but insted in 2
public interface CSVInterface
{
    public <E> Iterator<E> getCSVIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException ;
    public <E> List<E> getCSVList(Reader reader, Class<E> csvClass) throws CSVBuilderException ;
    }
