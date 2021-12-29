package com.NGQ4ZmVhOTM0.vulnjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class MyIndexController {
    private static volatile boolean notSecure = false;
    private static final Object lockObject = new Object();

    protected synchronized void setNotSecure(boolean secureSetting) {
        MyIndexController.notSecure = secureSetting;
    }

    @RequestMapping("/")
    public String myIndexShow(HttpServletRequest req){
        req.getSession(true);
        return "index";
    }

    @GetMapping("/turnoff")
    @ResponseBody
    public String turnOffSecurityMechanism(HttpServletRequest req){
        req.getSession(true);
        if (notSecure) { return "Security Mechanism Already Turned OFF.";}
        synchronized (lockObject) {
            System.setProperty("java.rmi.server.useCodebaseOnly", "false");
            System.setProperty("com.sun.jndi.cosnaming.object.trustURLCodebase", "true");
            System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
            System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
            this.setNotSecure(true);
        }
        return "Security Mechanism is Turned OFF.";
    }

    @GetMapping("/shiro/authCleanUp")
    @ResponseBody
    public String cleanUpUploadsFile(HttpServletRequest req){
        ServletContext context = req.getServletContext();
        String myPath = context.getRealPath("/");
        try {
            File uploadsDir = new File(myPath + "/uploads");
            if (uploadsDir.exists()) {
                String[] pathNames = uploadsDir.list();
                for (String singleFileName : pathNames) {
                    Files.delete(Paths.get(uploadsDir.getAbsolutePath() + "/" + singleFileName));
                }
                return "All Uploaded Files are Cleaned.";
            }
            return "No Uploaded Files Found.";
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
