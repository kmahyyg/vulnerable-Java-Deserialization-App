package com.NGQ4ZmVhOTM0.vulnjava.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyXMLController {

    @ResponseBody
    @PostMapping("/api/xstream")
    public String vulnXML(HttpServletRequest req, HttpServletResponse resp){
        req.getSession(true);
        String reqData = req.getParameter("data");
        if (reqData == null || reqData.isEmpty()) {return "Done.";}
        XStream xstDrv = new XStream(new DomDriver());
        try {
            Object p = xstDrv.fromXML(reqData);
            return p.toString();
        } catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}
