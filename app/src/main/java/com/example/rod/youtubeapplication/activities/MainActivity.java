package com.example.rod.youtubeapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rod.youtubeapplication.R;
import com.example.rod.youtubeapplication.adapters.YTResultRecyclerAdapter;
import com.example.rod.youtubeapplication.interfaces.OnResultSelectedListener;
import com.example.rod.youtubeapplication.models.RecyclerItemClickListener;
import com.example.rod.youtubeapplication.models.YTResult;
import com.example.rod.youtubeapplication.models.YTResults;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private Button butSearch;
    private EditText txtQuery;
    private RecyclerView recyclerView;
    private final YTResults results = new YTResults();
    private final String API_KEY = "AIzaSyAT1L5ImVuWdgxolsvkrvKmjY-BlVSRCmk";
    private final String BASE_URL =
            "https://www.googleapis.com/youtube/v3/search?type=video&part=snippet&maxResults=16&key="
                    + API_KEY + "&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butSearch = (Button) findViewById(R.id.butSearch);
        txtQuery = (EditText) findViewById(R.id.txtQuery);
        butSearch.setOnClickListener(new OnSearchClickListener());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(

                new RecyclerItemClickListener(MainActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        YTResult item = results.get(position);
                        String json = item.toJson();
                        Intent nextScreen = new Intent(view.getContext(), VideoActivity.class);
                        nextScreen.putExtra("yt", json);
                        startActivityForResult(nextScreen, 1);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }


    private class OnSearchClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            View view = MainActivity.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            String query = txtQuery.getText().toString();
            getResults(query);
        }
    }


    public void getResults(String query) {
        String URL_TOTAL = BASE_URL + query.replace(" ", "%20");

        StringRequest stringRequest = new StringRequest(URL_TOTAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //parse data from webservice to get Contracts as Java object
                            JSONObject data = new JSONObject(response);
                            JSONArray items = data.getJSONArray("items");
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject res = items.getJSONObject(i);
                                String video = res.getJSONObject("id").getString("videoId");
                                JSONObject snip = res.getJSONObject("snippet");
                                String title = snip.getString("title");
                                String author = snip.getString("channelTitle");
                                String descr = snip.getString("description");
                                String publi = snip.getString("publishedAt");
                                String url = snip.getJSONObject("thumbnails")
                                        .getJSONObject("high").getString("url");
                                YTResult ytr = new YTResult(title, author, descr, publi, url, video);
                                results.add(ytr);
                            }

                            recyclerView.setAdapter(new YTResultRecyclerAdapter(results));

                        } catch (Exception ex) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

}
