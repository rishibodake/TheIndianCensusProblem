public class CensusDAO {
    public String state;
    public String population;
    public int area;
    public int density;

    public CensusDAO(CSVStateCensus stateCensusCSV) {
        this.state = stateCensusCSV.state;
        this.population = stateCensusCSV.population;
        this.area = stateCensusCSV. AreaInSqKm;
        this.density = stateCensusCSV. DensityPerSqKm;
    }
}