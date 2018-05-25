package com.example.upshottechonologies.networklibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.util.Log;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    Button request;
    List<ModelClass> heroList;
    GridView gridView;
    GridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request = findViewById(R.id.buttonRequest);
        gridView = findViewById(R.id.gridView);

        heroList = new ArrayList<>();
        adapter = new GridAdapter(MainActivity.this, heroList);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netWorkRequest();
                request.setVisibility(View.INVISIBLE);
            }
        });
    }

    private static final String TAG = "MainActivity";
    private void netWorkRequest()
    {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://simplifiedcoding.net/demos/view-flipper/heroes.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: "+response.toString());

                try {
                    JSONArray jsonArray = response.getJSONArray("heroes");
                    for(int i = 0;i<jsonArray.length();i++)
                    {
                        ModelClass object = new ModelClass();
                        JSONObject obj = jsonArray.getJSONObject(i);
                        object.setName(obj.get("name").toString());
                        object.setUrl(obj.get("imageurl").toString());
                        heroList.add(object);
                    }
                    gridView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getLocalizedMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);
    }
}
