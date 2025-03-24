package com.example.cvbuilder;

public class Reference {
    private String name;
    private String contact;
    private String relation;

    public Reference(String name, String contact, String relation) {
        this.name = name;
        this.contact = contact;
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
