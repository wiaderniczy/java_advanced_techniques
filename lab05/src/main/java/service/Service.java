package service;

import algorithms.F1Score;
import algorithms.Kappa;
import algorithms.Precision;
import algorithms.Recall;
import ex.api.AnalysisException;
import ex.api.AnalysisService;
import ex.api.DataSet;

public class Service implements AnalysisService {
    private DataSet ds;
    private String algorithm;
    private float score;

    public Service() {
        this.algorithm = "Kappa"; //default value
    }
    @Override
    public void setOptions(String[] options) throws AnalysisException {
        this.algorithm = options[0];
    }

    @Override
    public String getName() {
        return this.algorithm;
    }

    @Override
    public void submit(DataSet data) throws AnalysisException {
        this.ds = data;
        switch(this.algorithm){
            case "Kappa":
                this.score = Kappa.getvalue(this.ds.getData());
                return;
            case "Precision":
                this.score = Precision.getvalue(this.ds.getData());
                return;
            case "Recall":
                this.score = Recall.getvalue(this.ds.getData());
                return;
            case "F1Score":
                this.score = F1Score.getvalue(Precision.getvalue(this.ds.getData()), Recall.getvalue(this.ds.getData()));
                return;
            default:
                this.score = 0;
                return;
        }
    }

    @Override
    public DataSet retrieve(boolean clear) throws AnalysisException {
        return this.ds;
    }

    public float getScore(){
        return this.score;
    }
}
