package com.NGQ4ZmVhOTM0.vulnjava.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyLog4JController {

    @RequestMapping("/api/log4j")
    @ResponseBody
    public String vulnLog4j(HttpServletRequest request) {
        request.getSession(true);
        String reqData = request.getParameter("data");
        String reqDataEncoded = Base64.encodeBase64String(reqData.getBytes());
        Logger logger = LogManager.getLogger(MyLog4JController.class);
        logger.info("Exploit Received: " + reqDataEncoded);
        logger.info("Request data: ");
        logger.info(reqData);
        return reqData;
    }
}
