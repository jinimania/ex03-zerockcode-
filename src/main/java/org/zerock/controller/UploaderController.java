package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.util.UploadFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Resource;

/**
 * @author LeeSoohoon
 */
@Controller
public class UploaderController {

    @Resource
    private String uploadPath;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
    public void uploadForm() {

    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
    public String uploadForm(MultipartFile file, Model model) throws IOException {
        logger.info("bytes: " + file.getBytes());
        logger.info("contentType: " + file.getContentType());
        logger.info("inputStream: " + file.getInputStream());
        logger.info("name: " + file.getName());
        logger.info("originalName: " + file.getOriginalFilename());
        logger.info("size: " + file.getSize());

        final String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
        model.addAttribute("savedName", savedName);

        return "uploadResult";
    }

    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public void uploadAjax() {

    }

    @ResponseBody
    @RequestMapping(value = "/uploadAjax",
            method = RequestMethod.POST,
            produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
        logger.info("contentType: " + file.getContentType());
        logger.info("inputStream: " + file.getInputStream());
        logger.info("name: " + file.getName());
        logger.info("originalName: " + file.getOriginalFilename());
        logger.info("size: " + file.getSize());

        return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath,
                file.getOriginalFilename(),
                file.getBytes()), HttpStatus.CREATED);
    }

    private String uploadFile(String originalName, byte[] fileData) throws IOException {
        UUID uid = UUID.randomUUID();
        final String savedName = uid.toString() + "_" + originalName;
        final File target = new File(uploadPath, savedName);
        FileCopyUtils.copy(fileData, target);

        return savedName;
    }
}
