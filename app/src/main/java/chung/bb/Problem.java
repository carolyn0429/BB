package chung.bb;

import java.util.List;

/**
 * Created by carolynhung on 10/12/16.
 */
public class Problem {

    private String problem;
    private List<Answer> ansList;
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

    public List<Answer> getAnsList() {
        return ansList;
    }

    public void setAnsList(List<Answer> ansList) {
        this.ansList = ansList;
    }
}
