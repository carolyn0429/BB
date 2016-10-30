package chung.bb;

import java.util.ArrayList;

/**
 * Created by carolynhung on 10/12/16.
 */
public class Problem {

    private String problem;
    private ArrayList<Answer> ansList;
    public Problem(){};
    public Problem(String problem){
        this.problem = problem;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public ArrayList<Answer> getAnsList() {
        return ansList;
    }

    public void setAnsList(ArrayList<Answer> ansList) {
        this.ansList = ansList;
    }
}
