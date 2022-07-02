package com.example.yuan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Integer tid;
    private String tname;
    private String tmail;
    private String tpw;
    private int lid;
    private String lname;
    private String libName = null;

}
