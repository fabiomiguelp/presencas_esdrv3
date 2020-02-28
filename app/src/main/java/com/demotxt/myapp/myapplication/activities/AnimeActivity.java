package com.demotxt.myapp.myapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.demotxt.myapp.myapplication.R ;
import com.demotxt.myapp.myapplication.model.Anime;
import com.demotxt.myapp.myapplication.model.Presenca;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class AnimeActivity extends AppCompatActivity implements View.OnClickListener {


    private final String JSON_PRESENCAS = "http://104.197.69.0/api/employee";
    private List<Presenca> arraypresencas;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;




    String name;
    String description;
    String studio;
    String category;
    int nb_episode;
    String rating;
    String image_url;
    String dia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        // hide the default actionbar
        getSupportActionBar().hide();

        // Recieve data

        name = getIntent().getExtras().getString("anime_name");
        description = getIntent().getExtras().getString("anime_description");
        studio = getIntent().getExtras().getString("anime_studio");
        category = getIntent().getExtras().getString("anime_category");
//        nb_episode = getIntent().getExtras().getInt("anime_nb_episode");
        rating = getIntent().getExtras().getString("anime_rating");
        image_url = getIntent().getExtras().getString("anime_img");
        dia = getIntent().getExtras().getString("dia");

        getChaveUnica(name + description + studio + dia);

        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_categorie = findViewById(R.id.aa_categorie);
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating = findViewById(R.id.aa_rating);
        TextView tv_dia = findViewById(R.id.aa_dia);

        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);
       // getChaveUnica("7");
       // tv_dia.setText(dia);


            tv_dia.setText(dia);




        collapsingToolbarLayout.setTitle(name);


        Button marcarPresenca = (Button) findViewById(R.id.btnPresenca);
        Button Voltar = (Button) findViewById(R.id.btnVoltar);
        marcarPresenca.setOnClickListener(this);
        Voltar.setOnClickListener(this);

    }


    public void showAlertDialogButtonClicked() {






        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Date d2 = new Date();
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sdf = new SimpleDateFormat("hh:mm a");
        }
        String currentDateTimeString2 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            currentDateTimeString2 = sdf.format(d2);
        }

        builder.setTitle("Sucesso!");
        builder.setMessage("Presenca Marcada às : " + currentDateTimeString2);

        // add a button

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sendPost();

                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                getApplicationContext().startActivity(intent);


            }
        });

        // create and show the alert dialog

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPresenca:

                showAlertDialogButtonClicked();
                break;

            case R.id.btnVoltar:
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                getApplicationContext().startActivity(intent);
                break;


        }

    }


    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    Date d = new Date();
                    SimpleDateFormat hora = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        hora = new SimpleDateFormat("hh:mm a");
                    }
                    String currentHora = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        currentHora = hora.format(d);
                    }


                    URL url = new URL("http://104.197.69.0/api/employee");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("id", "");
                    jsonParam.put("chaveunica", name + description + studio + dia);
                    jsonParam.put("nome", name);
                    jsonParam.put("edificio", description);
                    jsonParam.put("horainicio", studio);
                    jsonParam.put("horamarcada", currentHora);
                    jsonParam.put("dataaula", dia);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });


        thread.start();

    }




    private void getChaveUnica(final String chaveUnica) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://104.197.69.0/api/employee/chave/"+chaveUnica);//your url i.e fetch data from .
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP Error code : "
                                + conn.getResponseCode());
                    }
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    StringBuilder responseStrBuilder = new StringBuilder();


                    String inputStr;
                    while ((inputStr = br.readLine()) != null)
                        responseStrBuilder.append(inputStr);




                    JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

                    //returns the json object
                    if (jsonObject.get("chaveunica").toString().equalsIgnoreCase(name + description + studio + dia)) {

                        Button MarcarPresenca = (Button) findViewById(R.id.btnPresenca);
                        MarcarPresenca.setVisibility(View.GONE);



                        TextView presenca = findViewById(R.id.lblPresenca);

                        presenca.setText("Presença marcada às: " + jsonObject.get("horamarcada").toString());



                    } else {
                        TextView presenca = findViewById(R.id.lblPresenca);

                        presenca.setText("Presença ainda não marcada");
                    }

                    conn.disconnect();

                } catch (Exception e) {
                    System.out.println("Exception in NetClientGet:- " + e);
                }


            }


        });


        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }


}


















