package com.zzq.controller;

import com.zzq.utils.MailUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Index;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class CommonController {
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping("/upload")
    public ModelAndView uploadFile(MultipartFile uploadFile, HttpSession session) {
        String filename = uploadFile.getOriginalFilename();
        String leftPath = session.getServletContext().getRealPath("/WEB-INF/file");
        System.out.println(leftPath);
        File file = new File(leftPath, filename);
        try {
            uploadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        File file = new File("D:\\679009.jpg");
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = new String("丝袜美女.jpg".getBytes("UTF-8"), "iso-8859-1");
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);

    }

    @RequestMapping("/email")
    @ResponseBody
    public void sendEmail(){
        mailUtil.send();
    }
}
