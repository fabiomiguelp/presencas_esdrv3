package com.demotxt.myapp.myapplication.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.demotxt.myapp.myapplication.R;
import com.demotxt.myapp.myapplication.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class sem_aulas extends AppCompatActivity implements View.OnClickListener {

    private BroadcastReceiver minuteUpdateReceiver;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private final String JSON_URL = "http://35.241.153.86/piso_s2.php" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_aulas);
        Button Recarregar = (Button) findViewById(R.id.btnRecarregar);
        Recarregar.setOnClickListener(this);
    }




    public void startMinuteUpdater() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        minuteUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                jsonrequest3();



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



    private void jsonrequest3() {



        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;






                    try {
                        jsonObject = response.getJSONObject(0) ;


                        if (!jsonObject.get("summary").toString().equalsIgnoreCase("SEM AULAS")) {
                            finish();
                            Intent comAulas = new Intent(getApplicationContext(),MainActivity.class);
                            getApplicationContext().startActivity(comAulas);


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(sem_aulas.this);
        requestQueue.add(request) ;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecarregar:

                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                getApplicationContext().startActivity(intent);
                break;


        }

    }
}
