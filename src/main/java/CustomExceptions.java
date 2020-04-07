public class CustomExceptions extends Exception {
    public enum TypeOfException
    {
        NO_FILE_FOUND,
        INCORRECT_DELIMITER_EXCEPTION,
        INCORRECT_DELIMITER_HEADER_EXCEPTION
    }
    public TypeOfException typeOfException;

    public CustomExceptions(TypeOfException typeOfException)
    {
        this.typeOfException=typeOfException;
    }
}
