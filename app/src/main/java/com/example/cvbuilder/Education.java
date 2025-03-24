package com.example.cvbuilder;

import java.io.Serializable;

public class Education implements Serializable {
    private String degree;
    private String institution;
    private String start_date;
    private String end_date;
    private String total_marks;
    private String obtained_marks;

    public Education(String degree, String institution, String start_date, String end_date,String total_marks,String obtained_marks) {
        this.degree = degree;
        this.institution = institution;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total_marks = total_marks;
        this.obtained_marks = obtained_marks;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getObtained_marks() {
        return obtained_marks;
    }

    public void setObtained_marks(String obtained_marks) {
        this.obtained_marks = obtained_marks;
    }

    public String getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(String total_marks) {
        this.total_marks = total_marks;
    }

    public Education() {
        degree=null;
        institution=null;
        start_date=null;
        end_date=null;
    }
}
