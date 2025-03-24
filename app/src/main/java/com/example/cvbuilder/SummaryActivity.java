package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class SummaryActivity extends AppCompatActivity {


    TextInputEditText ETSummary;
    Button savebtn,cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init(){
        ETSummary =findViewById(R.id.ETsummary);
        savebtn = findViewById(R.id.Save);
        cancelbtn = findViewById(R.id.Cancel);


            savebtn.setOnClickListener(v -> {
                String summary = ETSummary.getText().toString();
                if(!summary.isEmpty()){
                    Intent intent = new Intent(this,Home.class);
                    intent.putExtra("summary",summary);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else{
                    ETSummary.setError("Enter Summary");
                }
            });
            cancelbtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Home.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            });
        }
    }
