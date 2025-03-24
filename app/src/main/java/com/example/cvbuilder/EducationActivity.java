package com.example.cvbuilder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EducationActivity extends AppCompatActivity {

    TextInputEditText ETdegree, ETinstitution, ETstartDate, ETendDate, ETtotal_marks, ETobtained_marks;
    Button Addbtn, Savebtn, Cancelbtn;
    FragmentManager fragmentManager;
    TextView TVresult;
    List<Education> educationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }

    private void init(){

        ETdegree = findViewById(R.id.ETdegree_name);
        ETinstitution = findViewById(R.id.ETinstitution);
        ETstartDate = findViewById(R.id.ETstart_date);
        ETendDate = findViewById(R.id.ETend_date);
        ETtotal_marks = findViewById(R.id.ETtotalmarks);
        ETobtained_marks = findViewById(R.id.ETobtainedmarks);
        TVresult = findViewById(R.id.TVresult);

        Savebtn = findViewById(R.id.Save);
        Cancelbtn = findViewById(R.id.Cancel);
        Addbtn  = findViewById(R.id.Addbtn);

            Addbtn.setOnClickListener(v -> {
                String degreeName = ETdegree.getText().toString();
                String institutionName = ETinstitution.getText().toString();
                String StartDate = ETstartDate.getText().toString();
                String EndDate = ETendDate.getText().toString();
                String total = ETtotal_marks.getText().toString();
                String obtained = ETobtained_marks.getText().toString();
                if(!degreeName.isEmpty()&&!institutionName.isEmpty()&&!StartDate.isEmpty()&&!EndDate.isEmpty()&&!total.isEmpty()&&!obtained.isEmpty()){
                    Education education = new Education(degreeName,institutionName,StartDate,EndDate,total,obtained);
                    educationList.add(education);
                    TVresult.setText(TVresult.getText().toString()+"\n"+education.getDegree());
                    ETdegree.setText("");
                    ETinstitution.setText("");
                    ETstartDate.setText("");
                    ETendDate.setText("");
                    ETtotal_marks.setText("");
                    ETobtained_marks.setText("");
                }
                else{
                    if(degreeName.isEmpty()){
                        ETdegree.setError("Enter Summary");
                    }
                    if(institutionName.isEmpty()){
                        ETinstitution.setError("Enter Institution");
                    }
                    if(StartDate.isEmpty()){
                        ETstartDate.setError("Enter degree Start date");
                    }
                    if(EndDate.isEmpty()){
                        ETendDate.setError("Enter degree Start date");
                    }
                    if(total.isEmpty()){
                        ETtotal_marks.setError("Add Total Marks");
                    }
                    if(obtained.isEmpty()){
                        ETobtained_marks.setError("Add Obtained Marks");
                    }
                }
            });

            Savebtn.setOnClickListener(v->{
                Intent intent = new Intent(this, Home.class);
                intent.putExtra("educationDetail", (Serializable) educationList);
                setResult(RESULT_OK,intent);
                finish();
            });

            Cancelbtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Home.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            });

        // Start Date Picker
        ETstartDate.setOnClickListener(v -> showDatePickerDialog(ETstartDate));

        // End Date Picker
        ETendDate.setOnClickListener(v -> showDatePickerDialog(ETendDate));


    }

    private void showDatePickerDialog(TextInputEditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            editText.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }

}