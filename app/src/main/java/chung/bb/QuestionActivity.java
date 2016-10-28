package chung.bb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private String TAG = QuestionActivity.class.getSimpleName();
    private TextView qTextView ;
    private Button btn_ans1;
    private Button btn_ans2;
    private Button btn_ans3;

    // URL to get contacts JSON
    private static String url = "http://www.carolynhung.com/questions.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        qTextView = (TextView) findViewById(R.id.qTextView);
        btn_ans1 = (Button)findViewById(R.id.btn_ans1);
        btn_ans2 = (Button)findViewById(R.id.btn_ans2);
        btn_ans3 = (Button)findViewById(R.id.btn_ans3);
        GetQuestions getQuestions = new GetQuestions(this);
        getQuestions.execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetQuestions extends AsyncTask<String, Void, ArrayList<Problem>> {

        public GetQuestions(Context context) {

        }
        ProgressDialog pDialog = new ProgressDialog(QuestionActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected ArrayList<Problem> doInBackground(String... params) {
            ArrayList<Problem> probList = new ArrayList<>();
            List<Answer> ansList = new ArrayList<>();
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    String questionStr = jsonObj.getString("questions");
                    JSONObject questions = new JSONObject(questionStr);
                    String problem = null;
                    problem = questions.getString("Problem");
                    Problem prob = new Problem(problem);
                    //System.out.println("problem: "+ prob);
                    JSONArray answers = questions.getJSONArray("Answers");
                    System.out.println("ans length: " + answers.length());
                    for (int i=0; i<answers.length(); i++){
                        Answer ans = new Answer();
                        JSONObject object3 = answers.getJSONObject(i);
                        String id = object3.getString("id");
                        String resp = object3.getString("Response");
                        String pros = object3.getString("Pros");
                        String cons = object3.getString("Cons");
                        System.out.println("id: "+ id + " resp: "+ resp);
                        ans.setResp(resp);
                        ans.setPros(pros);
                        ans.setCons(cons);
                        ansList.add(ans);
                        System.out.println("size: " + ansList.size());
                    }
                    System.out.println("ansList length: " + ansList.size());
                    prob.setAnsList(ansList);
                    probList.add(prob);
                    // Getting JSON Array node
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return probList;
        }

        @Override
        protected void onPostExecute(ArrayList<Problem> probList) {
            super.onPostExecute(probList);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */

            qTextView.setText(probList.get(0).getProblem());
            List<Answer> showAnsText = new ArrayList<>();
            showAnsText = probList.get(0).getAnsList();
            btn_ans1.setText(showAnsText.get(0).getResp());
            btn_ans2.setText(showAnsText.get(1).getResp());
            btn_ans3.setText(showAnsText.get(2).getResp());

        }

    }
}
