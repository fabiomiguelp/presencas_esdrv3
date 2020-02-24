package com.demotxt.myapp.myapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demotxt.myapp.myapplication.R;

import java.util.Date;

public class sem_aulas extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_aulas);
        Button Recarregar = (Button) findViewById(R.id.btnRecarregar);
        Recarregar.setOnClickListener(this);
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
