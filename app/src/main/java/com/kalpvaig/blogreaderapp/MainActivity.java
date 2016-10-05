package com.kalpvaig.blogreaderapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kalpvaig.blogreaderapp.Adapters.PostAdapter;
import com.kalpvaig.blogreaderapp.Model.Post;
import com.kalpvaig.blogreaderapp.Settings.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RequestQueue requestQueue;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VolleySingleton volleySingleton = VolleySingleton.getsInstance();
        requestQueue = volleySingleton.getRequestQueue();

        listView  = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //check whether the network is connected or not.
        if (!isNetworkAvailable()){
            Toast.makeText(this, "Please make sure you are connected to internet", Toast.LENGTH_SHORT).show();
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            jsonRequest();
        }


    }

    private void jsonRequest() {
        StringRequest request = new StringRequest(Request.Method.GET,
                "http://blog.teamtreehouse.com/api/get_recent_summary/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("logging",response);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Successfully Retrieved data", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getString("status").equals("ok"))
                                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();

                            final ArrayList<Post> list = new ArrayList();

                            JSONArray array = obj.getJSONArray("posts");
                            for(int i=0;i<array.length();i++){
                                Post post = new Post();

                                JSONObject object = array.getJSONObject(i);

                                post.setAuthor(object.getString("author"));
                                post.setDate(object.getString("date"));
                                post.setThubnail(object.getString("thumbnail"));
                                post.setTitle(object.getString("title"));
                                post.setUrl(object.getString("url"));


                                list.add(post);
                            }

                            PostAdapter adapter = new PostAdapter(MainActivity.this,R.layout.single_post,list);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    Intent intent= new Intent(MainActivity.this,Website.class);
                                    intent.putExtra("url",list.get(position).getUrl());
                                    startActivity(intent);
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something is not right", Toast.LENGTH_SHORT).show();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(30000, 5, 2));
        requestQueue.add(request);



    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
