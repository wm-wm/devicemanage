package com.example.yuan.controller;

import com.example.yuan.pojo.Equipment;
import com.example.yuan.service.EquipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EquipmentService equipmentService;
    int eid;
    String ename;
    String cname;
    public void setup()
    {
        Mockito.when(equipmentService.SelectAllEquipment()).thenReturn(new ArrayList<>());
        Mockito.when(equipmentService.DeleteEquipment(10001)).thenReturn(true);
        Mockito.when(equipmentService.UpdateEquipment(eid,ename,cname));
    }
    @Test
    public void listAll() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/EquipmentInformationSelect"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void delete() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/delete/{eid}",10001))
                .andExpect(status().isOk())
                .andDo(print());
    }
    /*@Test
    public void modify() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/EquipmentInformationModify")
                .param()
                .andExpect(status().isOk())
                .andDo(print());
    }
    */
}