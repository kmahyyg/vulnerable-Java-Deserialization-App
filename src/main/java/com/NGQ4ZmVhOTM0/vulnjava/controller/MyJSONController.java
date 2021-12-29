package com.NGQ4ZmVhOTM0.vulnjava.controller;

import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.parser.ParserConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyJSONController {

    @PostMapping("/api/fastjson")
    @ResponseBody
    public String vulnFastJSON(HttpServletRequest req, HttpServletResponse res){
//        By default, 1.2.24 is vulnerable and autoType is always on.
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        req.getSession(true);
        String jsonData = req.getParameter("data");
        if (jsonData == null || jsonData.isEmpty()) {
            return "";
        }
        try {
            Object obj = JSON.parseObject(jsonData);
            return obj.toString();
        } catch (Exception e){
            e.printStackTrace();
            res.setStatus(200);
            return e.toString();
        }
    }
}
