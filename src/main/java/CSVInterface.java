import java.io.Reader;
import java.util.Iterator;
//Should have Introduced in Refactor 3 but insted in 2
public class CSVInterface {
    public <E> Iterator<E> getCSVfileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException{
        return null;
    }
}