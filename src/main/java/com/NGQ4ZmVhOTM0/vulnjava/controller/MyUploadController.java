package com.NGQ4ZmVhOTM0.vulnjava.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@RestController
public class MyUploadController {
    @PostMapping("/api/upload4j")
    public String vulnUpload4j(HttpServletRequest req, HttpServletResponse resp) {
        // start session
        req.getSession(true);
        // use commons-fileupload to parse multipart request
        // due to insufficient implementation of resin
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            // build uuid to rename
            UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString() + ".jsp";
            try {
                // create factory for disk-based file items
                ServletContext context = req.getServletContext();
                DiskFileItemFactory dfif = new DiskFileItemFactory();
                // set factory properties, especially temporary file tracker
                FileCleaningTracker fct = FileCleanerCleanup.getFileCleaningTracker(context);
                dfif.setFileCleaningTracker(fct);
                dfif.setSizeThreshold(10485760);
                // configure safe tempdir factory
                dfif.setRepository(new File(context.getAttribute("javax.servlet.context.tempdir").toString()));
                // create servlet uploader
                ServletFileUpload upload = new ServletFileUpload(dfif);
                // parse request
                List<FileItem> lfitems = upload.parseRequest(req);
                // set hasFile flag
                boolean hasFile = false;
                // iterate over file items
                Iterator<FileItem> iter = lfitems.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()) {
                        // this is a file item
                        // get file path on disk
                        String myPath = context.getRealPath("/");
                        Files.createDirectories(Paths.get(myPath + "/uploads/"));
                        File optFile = new File(myPath + "/uploads/" + fileName);
                        // write file to disk
                        item.write(optFile);
                        hasFile = true;
                    } else {
                        // this is a text item, ignored
                        // do nothing
                        System.out.println("Text item: " + item.getFieldName() + " = " + item.getString());
                    }
                }
                if (hasFile) {
                    // return success
                    return "/uploads/" + fileName;
                } else {
                    // return failure
                    return "Upload Failed.";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        } else {
            resp.setStatus(400);
            return "Not multipart/form-data POST request";
        }
    }
}
