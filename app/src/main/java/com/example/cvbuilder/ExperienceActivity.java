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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ExperienceActivity extends AppCompatActivity {

    List<Experience> experienceList=new ArrayList<>();
    TextInputEditText ETcompanyname, ETRole, ETstartdate, Etenddate;
    Button Addbtn, Savebtn, Cancelbtn;
    FragmentManager fragmentManager;
    TextView TVresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

    }

    public void init(){
        ETcompanyname=findViewById(R.id.ETcompanyname);
        ETRole=findViewById(R.id.ETrolename);
        ETstartdate=findViewById(R.id.ETstart_date);
        Etenddate=findViewById(R.id.ETend_date);
        TVresult = findViewById(R.id.TVresult);

        Savebtn = findViewById(R.id.Save);
        Cancelbtn = findViewById(R.id.Cancel);
        Addbtn  = findViewById(R.id.Addbtn);

        if(Savebtn != null && Cancelbtn != null && Addbtn != null) {
            Addbtn.setOnClickListener(v -> {
                String companyName = ETcompanyname.getText().toString();
                String roleName = ETRole.getText().toString();
                String startDate = ETstartdate.getText().toString();
                String endDate = Etenddate.getText().toString();

                if (!companyName.isEmpty() && !roleName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                    // Convert dates to LocalDate for validation
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                    LocalDate startLocalDate;
                    LocalDate endLocalDate;
                    LocalDate today = LocalDate.now();

                    try {
                        startLocalDate = LocalDate.parse(startDate, formatter);
                        endLocalDate = LocalDate.parse(endDate, formatter);
                    } catch (DateTimeParseException e) {
                        Toast.makeText(this, "Invalid date format. Use YYYY-MM-DD", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validate Start Date < End Date
                    if (startLocalDate.isAfter(endLocalDate)) {
                        ETstartdate.setError("Start date must be before End date");
                        return;
                    }

                    // If End Date is today or in the future, set it to "Present"
                    String finalEndDate = endLocalDate.isAfter(today) || endLocalDate.isEqual(today) ? "Present" : endDate;

                    // Add to experience list
                    Experience experience = new Experience(companyName, roleName, startDate, finalEndDate);
                    experienceList.add(experience);
                    TVresult.setText(TVresult.getText().toString() + "\n" + experience.getCompanyName());

                    // Clear input fields
                    ETcompanyname.setText("");
                    ETRole.setText("");
                    ETstartdate.setText("");
                    Etenddate.setText("");
                } else {
                    // Set field errors
                    if (companyName.isEmpty()) ETcompanyname.setError("Enter Company Name");
                    if (roleName.isEmpty()) ETRole.setError("Enter Role Name");
                    if (startDate.isEmpty()) ETstartdate.setError("Enter Start Date");
                    if (endDate.isEmpty()) Etenddate.setError("Enter End Date");
                }
            });


            Savebtn.setOnClickListener(v->{
                Intent intent = new Intent(this, Home.class);
                intent.putExtra("experienceDetail", (Serializable) experienceList);
                setResult(RESULT_OK,intent);
                finish();
            });

            Cancelbtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Home.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            });
        }
        ETstartdate.setOnClickListener(v -> showDatePickerDialog(ETstartdate));

        Etenddate.setOnClickListener(v -> showDatePickerDialog(Etenddate));
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