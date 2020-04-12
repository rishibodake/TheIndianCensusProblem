public class CensusDAO {
    public String StateID;
    public float HousingDensity;
    public String State;
    public long Population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
    public String StateCode;
    public int TIN;
    public int SrNo;

    public CensusDAO(CSVStateCensus stateCensusCSV) {
        this.State = stateCensusCSV.StateName;
        this.Population = stateCensusCSV.Population;
        this.AreaInSqKm = stateCensusCSV.AreaInSqKm;
        this.DensityPerSqkm = stateCensusCSV.DensityPerSqKm;
    }
    public CensusDAO(CSVStates stateDataCSV) {
        this.State = stateDataCSV.StateName;
        this.SrNo = stateDataCSV.SrNo;
        this.TIN = stateDataCSV.TIN;
        this.StateCode = stateDataCSV.StateCode;
    }

    public CensusDAO(USCensusCSV usCensusCSV){
        this.StateID = usCensusCSV.StateID;
        this.State = usCensusCSV.State;
        this.Population = usCensusCSV.Population;
        this.AreaInSqKm= usCensusCSV.Area;
        this.DensityPerSqkm = usCensusCSV.PopulationDensity;
        this.HousingDensity = usCensusCSV.HousingDensity;
    }
}