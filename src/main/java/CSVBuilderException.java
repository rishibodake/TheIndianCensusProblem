public class CSVBuilderException extends Exception {
    public enum TypeOfException
    {
        NO_FILE_FOUND,
        INCORRECT_DELIMITER_EXCEPTION,
        INCORRECT_DELIMITER_HEADER_EXCEPTION
    }
    public TypeOfException typeOfException;

    public CSVBuilderException(String message, TypeOfException typeOfException)
    {
        super(message);
        this.typeOfException=typeOfException;
    }
}
