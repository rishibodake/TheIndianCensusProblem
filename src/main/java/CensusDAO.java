public class CensusDAO {
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
        this.AreaInSqKm = stateCensusCSV. AreaInSqKm;
        this.DensityPerSqkm = stateCensusCSV. DensityPerSqKm;
    }
    public CensusDAO(CSVStates stateDataCSV) {
        this.State = stateDataCSV.StateName;
        this.SrNo = stateDataCSV.SrNo;
        this.TIN = stateDataCSV.TIN;
        this.StateCode = stateDataCSV.StateCode;
    }
}