package com.example.yuan.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
    private Integer aid;
    private String astart;
    private String aend;
    private String acomment;//申请理由
    private String aevaluate;//评价
    private int mid;//用户ID
    private int eid;//仪器ID
    private int flag = 0;//仪器状态（请求是否通过,0表示未通过，仪器未预约.1表示通过，仪器已被预约）
    private String mname;
    private String ename;
    private String fname;
    private String flagss;
}
