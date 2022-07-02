package com.example.yuan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Integer mid;
    private String mname;
    private String mmail;
    private String mpw;
}