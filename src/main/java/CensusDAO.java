public class CensusDAO {
    public String StateID;
    public float HousingDensity;
    public String StateName;
    public long Population;
    public long AreaInSqKm;
    public long DensityPerSqkm;
    public String StateCode;
    public int TIN;
    public int SrNo;

    public CensusDAO(CSVStateCensus stateCensusCSV) {
        this.StateName = stateCensusCSV.StateName;
        this.Population = stateCensusCSV.Population;
        this.AreaInSqKm = stateCensusCSV.AreaInSqKm;
        this.DensityPerSqkm = stateCensusCSV.DensityPerSqKm;
    }
    public CensusDAO(CSVStates stateDataCSV) {
        this.StateName = stateDataCSV.StateName;
        this.SrNo = stateDataCSV.SrNo;
        this.TIN = stateDataCSV.TIN;
        this.StateCode = stateDataCSV.StateCode;
    }

    public CensusDAO(USCensusCSV usCensusCSV){
        this.StateID = usCensusCSV.StateID;
        this.StateName = usCensusCSV.StateName;
        this.Population = usCensusCSV.Population;
        this.AreaInSqKm= usCensusCSV.Area;
        this.DensityPerSqkm = usCensusCSV.PopulationDensity;
        this.HousingDensity = usCensusCSV.HousingDensity;
    }

    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long population) {
        Population = population;
    }

    public long getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(long areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public long getDensityPerSqkm() {
        return DensityPerSqkm;
    }

    public void setDensityPerSqKm(long densityPerSqkm) {
        DensityPerSqkm = densityPerSqkm;
    }

    public Object getCensusModel(CensusAnalyser.Country country)
    {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new CSVStateCensus(StateName, Population, AreaInSqKm, DensityPerSqkm);
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(StateCode, StateName, Population, AreaInSqKm, DensityPerSqkm);
        return null;
    }
}