package chung.bb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {

    private String TAG = StoryActivity.class.getSimpleName();
    private TextView storyboard;

    // URL to get contacts JSON
    private static String url = "http://www.carolynhung.com/stories.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyboard = (TextView)findViewById(R.id.storyboard);

        GetStories getStories = new GetStories(this);
        getStories.execute();
    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetStories extends AsyncTask<String, Void, ArrayList<Story>> {

        public GetStories(Context context) {

        }

        ProgressDialog pDialog = new ProgressDialog(StoryActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected ArrayList<Story> doInBackground(String... params) {
            ArrayList<Story> storyList = new ArrayList<>();
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
           // System.out.println("json str: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray sod_array = jsonObj.getJSONArray("stories");

                    for (int i=0;i<sod_array.length();i++){
                        JSONObject sod_obj = sod_array.getJSONObject(i);
                        Story story = new Story();
                        String storyOfTheDay = null;
                        storyOfTheDay = sod_obj.getString("story_of_the_day");
                        story.setStory(storyOfTheDay);
                       // System.out.println("Story: " + story.getStory());
                        storyList.add(story);
                    }
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

            return storyList;
        }

        @Override
        protected void onPostExecute(ArrayList<Story> storyList) {
            super.onPostExecute(storyList);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into TextView
             * */

            storyboard.setText(storyList.get(1).getStory());
            //storyboard.setBackgroundColor(R.color.colorPrimary);
        }
    }
}
