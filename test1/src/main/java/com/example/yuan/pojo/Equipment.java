package com.example.yuan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {
    private Integer eid;
    private String ename;
    private Integer cid;
    private int lid;
    private String cname;
    private String lname;
    private int flag;
    private String fname;
}
