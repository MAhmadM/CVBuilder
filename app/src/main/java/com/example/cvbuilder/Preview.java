package com.example.cvbuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
public class Preview extends AppCompatActivity {
    private TextView nameTextView, emailTextView, phoneTextView;
    private TextView summaryTextView, educationTextView, experienceTextView, referenceTextView, TVcertificates;
    private ImageFilterView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        phoneTextView = findViewById(R.id.phone);
        summaryTextView = findViewById(R.id.summary);
        educationTextView = findViewById(R.id.education);
        experienceTextView = findViewById(R.id.experience);
        referenceTextView = findViewById(R.id.references);
        profileImageView = findViewById(R.id.profile_Pic);
        TVcertificates = findViewById(R.id.certifications);
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("Person");

        if (person != null) {
            nameTextView.setText(person.getName() != null ? person.getName() : "NA");
            emailTextView.setText(person.getEmail() != null ? person.getEmail() : "NA");
            phoneTextView.setText(person.getPhone() != null ? person.getPhone() : "NA");
            summaryTextView.setText(person.getSummary() != null ? person.getSummary() : "NA");if (person.getImageSrc() != null && !person.getImageSrc().isEmpty()) {
                try {
                    Uri uri = Uri.parse(person.getImageSrc());
                    Toast.makeText(this,"uri is "+uri,Toast.LENGTH_SHORT).show();
                    if (profileImageView != null) {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        profileImageView.setImageBitmap(bitmap);
                    } else {
                        Toast.makeText(this, "profileImageView is null", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Invalid image URI", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            }

            if (!person.getEducationList().isEmpty()) {
                for(int i=0;i<person.educationList.size();i++){
                    educationTextView.setText(person.getEducationList().get(i).getDegree() + " - " +
                            person.getEducationList().get(i).getInstitution());
                }
            } else {
                educationTextView.setText("NA");
            }
            if (!person.getCertificationList().isEmpty()) {
                for(int i=0;i<person.certificationList.size();i++){
                    educationTextView.setText(person.getCertificationList().get(i).getTitle() + " - " +
                            person.getCertificationList().get(i).getIssuingOrganization());
                }
            } else {
                educationTextView.setText("NA");
            }
            if (!person.getExperienceList().isEmpty()) {
                for(int i=0;i<person.experienceList.size();i++){
                    experienceTextView.setText(person.getExperienceList().get(i).getCompanyName() + " - " +
                            person.getExperienceList().get(i).getStartDate());
                }
            } else {
                experienceTextView.setText("NA");
            }

            if (!person.getReferenceList().isEmpty()) {
                for(int i=0;i<person.referenceList.size();i++){
                    referenceTextView.setText(person.getReferenceList().get(i).getName()+"\ncontact: "+
                            person.getReferenceList().get(i).getContact());
                }
            } else {
                referenceTextView.setText("NA");
            }
        } else {
            nameTextView.setText("NA");
            emailTextView.setText("NA");
            phoneTextView.setText("NA");
            summaryTextView.setText("NA");
            educationTextView.setText("NA");
            experienceTextView.setText("NA");
            referenceTextView.setText("NA");
        }
    }
}