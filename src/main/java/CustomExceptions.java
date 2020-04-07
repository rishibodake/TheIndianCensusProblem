public class CustomExceptions extends Exception {
    public enum TypeOfException
    {
        NO_FILE_FOUND
    }
    public TypeOfException typeOfException;

    public CustomExceptions(TypeOfException typeOfException)
    {
        this.typeOfException=typeOfException;
    }
}
