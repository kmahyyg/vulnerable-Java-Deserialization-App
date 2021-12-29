package com.NGQ4ZmVhOTM0.vulnjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyYAMLController {
    @ResponseBody
    @PostMapping("/api/snakeyaml")
    public String vulnYAML(HttpServletRequest req, HttpServletResponse resp){
        req.getSession(true);
        String data = req.getParameter("data");
        Yaml yaml = new Yaml();
        try {
            Object obj = yaml.load(data);
            return obj.toString();
        } catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}
