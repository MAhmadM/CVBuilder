package com.example.cvbuilder;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Home extends AppCompatActivity {

    Button pp, pd, sum, ref, edu, exp, clr, cert, pre;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init(){
        pp = findViewById( R.id.pp);
        pd = findViewById( R.id.pd);
        edu = findViewById( R.id.edu);
        exp = findViewById( R.id.exp);
        sum = findViewById( R.id.sum);
        ref = findViewById( R.id.ref);
        cert = findViewById( R.id.cert);
        pre = findViewById( R.id.pre);
        clr = findViewById( R.id.clr);

        pp.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, ProfilePicture.class);
            Toast.makeText(this, "Clicked on profile button", Toast.LENGTH_LONG).show();
            profilePicLauncher.launch(intent);
        });

        pd.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, PersonalDetails.class);
            personalDetailLauncher.launch(intent);
        });

        sum.setOnClickListener(v -> {
            Intent intent = new Intent(this, SummaryActivity.class);
            SummaryLauncher.launch(intent);
        });

        edu.setOnClickListener(v -> {
            Intent intent = new Intent(this, EducationActivity.class);
            EducationLauncher.launch(intent);
        });

        exp.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExperienceActivity.class);
            ExperienceLauncher.launch(intent);
        });

        cert.setOnClickListener(v -> {
            Intent intent = new Intent(this, CertificationActivity.class);
            CertificationLauncher.launch(intent);
        });

        ref.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReferenceActivity.class);
            ReferenceLauncher.launch(intent);
        });

        pre.setOnClickListener(v -> {
            Intent intent = new Intent(this,Preview.class);
            intent.putExtra("Person",(Serializable) person);
            startActivity(intent);
            onPause();
        });

        clr.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Discard Data") // Dialog title
                    .setMessage("Are you sure you want to discard all data?") // Dialog message
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // User confirmed, reset the person object
                        person = new Person();
                        Toast.makeText(this, "All data discarded", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // User canceled, do nothing
                        dialog.dismiss();
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert) // Optional: Set an icon
                    .show(); // Show the dialog
        });
    }

    private ActivityResultLauncher<Intent> ReferenceLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        List<Reference> referenceList = (List<Reference>) data.getSerializableExtra("referenceDetail");
                        if (referenceList == null) {
                            referenceList = new ArrayList<>();
                        }
                        person.Addreference(referenceList);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> EducationLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        List<Education> educationList = (List<Education>) data.getSerializableExtra("educationDetail");
                        if (educationList == null) {
                            educationList = new ArrayList<>();
                        }
                        person.AddEducation(educationList);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> ExperienceLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        List<Experience> experienceList = (List<Experience>) data.getSerializableExtra("experienceDetail");
                        if (experienceList == null) {
                            experienceList = new ArrayList<>();
                        }
                        person.Addexperience(experienceList);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> CertificationLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        List<Certification> certificationList = (List<Certification>) data.getSerializableExtra("certificationDetail");
                        if (certificationList == null) {
                            certificationList = new ArrayList<>();
                        }
                        person.Addcertification(certificationList);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> personalDetailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        person= (Person) data.getSerializableExtra("personDetail");
                        if (person != null) {
                            // Do something with the received Person object
                            Toast.makeText(this, "Received: " + person.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    private ActivityResultLauncher<Intent> SummaryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
        if(result.getResultCode()==RESULT_OK){
            Intent data = result.getData();
            if(data!=null){
                String summary = data.getStringExtra("summary");
                Toast.makeText(this,"sumaary is "+summary,Toast.LENGTH_LONG).show();
                person.setSummary(summary);
            }
        } else{
            person.setSummary("");
        }
    });

    private ActivityResultLauncher<Intent> profilePicLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    String imageUri = result.getData().getStringExtra("imageUri");
                    if (imageUri != null) {
                        person.setImageSrc(imageUri);
                    }
                }
            });
}