package com.fawwazi.suitmediafawwaziapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondScreen extends AppCompatActivity implements View.OnClickListener {
    private TextView tvName, tvSelectedName;
    private Button btnChoose;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        tvName = findViewById(R.id.tv_name);
        tvSelectedName = findViewById(R.id.tv_selectedname);
        btnChoose = findViewById(R.id.btn_choose);
        ivBack = findViewById(R.id.iv_back);

        tvName.setText(getIntent().getStringExtra("NAME"));
        btnChoose.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences mySharedPreferences = this.getSharedPreferences("SELECTEDNAME_PREFS", Context.MODE_PRIVATE);
        String selected_name = mySharedPreferences.getString("SELECTED_NAME", "gada");

        tvSelectedName.setText(selected_name);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_choose) {
            Intent i = new Intent(this, ThirdScreen.class);
            startActivity(i);
        } else if (v.getId() == R.id.iv_back) {
            finish();
        }
    }
}