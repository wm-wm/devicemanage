package com.example.yuan.pojo;

public class Laboratory {
    private Integer Lid;
    private String Lname;

    public Integer getLid() {
        return Lid;
    }

    public String getLname() {
        return Lname;
    }

    public void setLid(Integer lid) {
        Lid = lid;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "Lid=" + Lid +
                ", Lname='" + Lname + '\'' +
                '}';
    }
}
