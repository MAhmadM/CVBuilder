package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PersonalDetails extends AppCompatActivity {

    EditText name, email, phone;
    Button save, cancel;
    RadioGroup radioGroup;
    RadioButton male, female;

    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init(){
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        radioGroup = findViewById(R.id.radiogroup);
        male = findViewById(R.id.maleRadio);
        female = findViewById(R.id.femaleRadio);
        String gender="";
        int checkid = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(checkid);
        gender = selectedRadioButton.getText().toString();
        String finalGender = gender;
        save.setOnClickListener(v->{
            if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !phone.getText().toString().isEmpty() && !finalGender.isEmpty()){
                person= new Person(name.getText().toString(),email.getText().toString(),phone.getText().toString(),finalGender);
                Intent intent1= new Intent(this, Home.class);
                intent1.putExtra("personDetail",  person);
                setResult(RESULT_OK,intent1);
                finish();
            }
            else{
                if(name.getText().toString().isEmpty()){
                    name.setError("Enter Name");
                }
                if(email.getText().toString().isEmpty()){
                    email.setError("Enter Email");
                }
                if(phone.getText().toString().isEmpty()){
                    phone.setError("Enter Phone");
                }
            }
        });

        cancel.setOnClickListener(v->{
            Intent intent2= new Intent(this, Home.class);
            setResult(RESULT_CANCELED,intent2);
            finish();
        });
    }

}