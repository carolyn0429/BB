package chung.bb;

/**
 * Created by carolynhung on 10/11/16.
 */
public class Answer {
    public Answer(){};
    private String pros;
    private String cons;
    private String resp;

    public Answer(String resp, String pros,String cons){
        this.resp = resp;
        this.pros = pros;
        this.cons = cons;
    };

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }
}
