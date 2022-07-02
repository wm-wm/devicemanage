package com.example.yuan.controller;

import com.example.yuan.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private MemberService memberService;
    public void setup()
    {
        Mockito.when(memberService.SelectAllMember()).thenReturn(new ArrayList<>());
    }
    @Test
    public void listall() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/MemberInformationSelect"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}