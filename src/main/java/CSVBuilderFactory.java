public class CSVBuilderFactory
{
    public static OpenCSV createCsvBuilder()
    {
        return new OpenCSV();
    }

    public static OpenCSV createCSVBuilder() {
        return null;
    }
}