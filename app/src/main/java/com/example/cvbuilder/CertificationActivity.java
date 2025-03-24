package com.example.cvbuilder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CertificationActivity extends AppCompatActivity {
    TextView result;
    TextInputEditText ETcertificationTitle, ETOrganisation, ETissueDate;
    Button Addbtn, Savebtn, Cancelbtn;
    FragmentManager fragmentManager;
    List<Certification> certificationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_certification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        result = findViewById(R.id.TVresult);
        ETcertificationTitle = findViewById(R.id.ETcertificationTitle);
        ETOrganisation = findViewById(R.id.ETissuingOrganization);
        ETissueDate = findViewById(R.id.ETissueDate);


        Addbtn = findViewById(R.id.Addbtn);
        Savebtn = findViewById(R.id.Save);
        Cancelbtn = findViewById(R.id.Cancel);
            Addbtn.setOnClickListener(v -> {
                String certficateTitle = ETcertificationTitle.getText().toString();
                String issueOrg = ETOrganisation.getText().toString();
                String issueDate = ETissueDate.getText().toString();

                if (!certficateTitle.isEmpty() && !issueOrg.isEmpty() && !issueDate.isEmpty()) {
                    if(!isValidStartDate(issueDate)){
                        Toast.makeText(this,"Date can't be greater than today",Toast.LENGTH_SHORT);
                        ETissueDate.setError("Enter correct date");
                        return;
                    }
                    // Add to experience list
                    Certification certification = new Certification(certficateTitle, issueOrg, issueDate);
                    certificationList.add(certification);
                    result.setText(result.getText().toString() + "\n" + certification.getTitle());

                    // Clear input fields
                    ETissueDate.setText("");
                    ETcertificationTitle.setText("");
                    ETOrganisation.setText("");
                } else {
                    // Set field errors
                    if (issueOrg.isEmpty()) ETOrganisation.setError("Enter Organisation Name");
                    if (certficateTitle.isEmpty())
                        ETcertificationTitle.setError("Enter Certificate name");
                    if (issueDate.isEmpty()) {
                        ETissueDate.setError("Enter issue date");
                    }
                }
            });


            Savebtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Home.class);
                intent.putExtra("certificationDetail", (Serializable) certificationList);
                setResult(RESULT_OK, intent);
                finish();
            });

            Cancelbtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Home.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            });


        ETissueDate.setOnClickListener(v->{showDatePickerDialog(ETissueDate);});

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
    public boolean isValidStartDate(String startDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
            sdf.setLenient(false);
            Date enteredDate = sdf.parse(startDate);
            Date currentDate = new Date();

            return !enteredDate.after(currentDate);
        } catch (Exception e) {
            return false;
        }
    }
}