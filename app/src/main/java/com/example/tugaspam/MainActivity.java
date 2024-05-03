package com.example.tugaspam;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActivitiesAdapter adapter;
    private List<ActivityItem> activityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ActivitiesAdapter(activityList);
        recyclerView.setAdapter(adapter);

        // Start the AsyncTask to fetch the data
        new FetchActivitiesTask().execute("https://mgm.ub.ac.id/todo.php");
    }

    private class FetchActivitiesTask extends AsyncTask<String, Void, List<ActivityItem>> {
        protected List<ActivityItem> doInBackground(String... urls) {
            try {
                // Get the JSON data from the URL
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(urls[0]).openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return parseJsonData(stringBuilder.toString());
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(List<ActivityItem> activities) {
            if (activities != null) {
                activityList.clear();
                activityList.addAll(activities);
                adapter.notifyDataSetChanged();
            }
        }

        private List<ActivityItem> parseJsonData(String jsonData) {
            List<ActivityItem> newActivities = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String what = jsonObject.getString("what");
                    String time = jsonObject.getString("time");
                    newActivities.add(new ActivityItem(id, what, time));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return newActivities;
        }
    }
}
