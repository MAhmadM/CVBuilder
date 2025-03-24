package com.example.cvbuilder;

public class Experience {
    public Experience(String companyName, String role, String startDate, String endDate) {
        CompanyName = companyName;
        Role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    String CompanyName, Role, startDate, endDate;
}
