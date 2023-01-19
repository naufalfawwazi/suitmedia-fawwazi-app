package com.fawwazi.suitmediafawwaziapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstScreen extends AppCompatActivity implements View.OnClickListener {
    private EditText etName, etPalindrome;
    private Button btnCheck, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        etName = findViewById(R.id.et_name);
        etPalindrome = findViewById(R.id.et_palindrome);

        btnCheck = findViewById(R.id.btn_check);
        btnNext = findViewById(R.id.btn_next);

        btnCheck.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_check) {
            if (etPalindrome.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please fill the Palindrome form above...", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isPalindrome(etPalindrome.getText().toString().trim().replace(" ",""))) {
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_next) {
            if (etName.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please fill the Name form above...", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent i = new Intent(this, SecondScreen.class);
            i.putExtra("NAME", etName.getText().toString().trim());
            startActivity(i);
        }
    }

    private boolean isPalindrome(String sequence) {
        int first = 0;
        int end = sequence.length() - 1;

        while (first < end) {

            if (sequence.charAt(first) != sequence.charAt(end))
                return false;

            first++;
            end--;
        }
        return true;
    }
}