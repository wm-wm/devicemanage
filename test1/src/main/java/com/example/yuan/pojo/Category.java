package com.example.yuan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    //存储仪器类别
    int cid;
    String cname;
    int lid;
}
