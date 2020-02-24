package com.demotxt.myapp.myapplication.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.demotxt.myapp.myapplication.R;
import com.demotxt.myapp.myapplication.adapters.RecyclerViewAdapter;
import com.demotxt.myapp.myapplication.model.Anime;
import com.demotxt.myapp.myapplication.model.Presenca;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "http://35.241.153.86/piso_s2.php" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Anime> lstAnime ;
    private BroadcastReceiver minuteUpdateReceiver;

    private RecyclerView recyclerView ;
    RecyclerViewAdapter myadapter ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




            lstAnime = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);

        jsonrequest();








    }





    public void startMinuteUpdater() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        minuteUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                jsonrequest2();



                /*finish();
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                getApplicationContext().startActivity(intent2);*/
            }
        };

        registerReceiver(minuteUpdateReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMinuteUpdater();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(minuteUpdateReceiver);
    }






private void jsonrequest() {
        lstAnime.clear();


        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;


                for (int i = 0 ; i < response.length(); i++ ) {





                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Anime anime = new Anime() ;
                        anime.setName(jsonObject.getString("summary"));
                        anime.setDescription(jsonObject.getString("location"));
                        anime.setRating(jsonObject.getString("location"));
                        anime.setDia(jsonObject.getString("start").substring(25, jsonObject.getString("start").length()-47));
                        anime.setNb_episode(jsonObject.getString("summary"));
                        anime.setStudio(jsonObject.getString("start").substring(36, jsonObject.getString("start").length()-41));

                        //if (arraypresencas.get(i).getChaveunica().equalsIgnoreCase(anime.getName()+anime.getDescription()+anime.getStudio()+currentDia)) {
                            lstAnime.add(anime);
                      //  }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                if (!lstAnime.get(0).getName().equalsIgnoreCase("SEM AULAS")) {

                    setuprecyclerview(lstAnime);

                }else{
                    Intent semAulas = new Intent(getApplicationContext(),sem_aulas.class);
                    getApplicationContext().startActivity(semAulas);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Anime> lstAnime) {

        myadapter = new RecyclerViewAdapter(this, lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);


    }



    private void jsonrequest2() {
        lstAnime.clear();


        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;


                for (int i = 0 ; i < response.length(); i++ ) {





                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Anime anime = new Anime() ;
                        anime.setName(jsonObject.getString("summary"));
                        anime.setDescription(jsonObject.getString("location"));
                        anime.setRating(jsonObject.getString("location"));
                        anime.setDia(jsonObject.getString("start").substring(25, jsonObject.getString("start").length()-47));
                        anime.setNb_episode(jsonObject.getString("summary"));
                        anime.setStudio(jsonObject.getString("start").substring(36, jsonObject.getString("start").length()-41));

                        //if (arraypresencas.get(i).getChaveunica().equalsIgnoreCase(anime.getName()+anime.getDescription()+anime.getStudio()+currentDia)) {
                        lstAnime.add(anime);
                        //  }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                if (!lstAnime.get(0).getName().equalsIgnoreCase("SEM AULAS")) {

                    myadapter.notifyDataSetChanged();
                    finish();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    getApplicationContext().startActivity(intent);


                }else{
                    Intent semAulas = new Intent(getApplicationContext(),sem_aulas.class);
                    getApplicationContext().startActivity(semAulas);

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;


    }

















}