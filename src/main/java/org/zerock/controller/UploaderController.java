package org.zerock.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.util.MediaUtil;
import org.zerock.util.UploadFileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    @ResponseBody
    @RequestMapping(value = "/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws IOException {

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        logger.info("FILE NAME: " + fileName);

        final String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        final MediaType mType = MediaUtil.getMediaType(formatName);

        final HttpHeaders headers = new HttpHeaders();

        try {
            in = new FileInputStream(uploadPath + fileName);

            if (mType != null) {
                headers.setContentType(mType);
            } else {
                fileName = fileName.substring(fileName.indexOf("_") + 1);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return entity;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {

        logger.info("delete file: " + fileName);

        final String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        final MediaType mType = MediaUtil.getMediaType(formatName);

        if (mType != null) {
            final String front = fileName.substring(0, 12);
            final String end = fileName.substring(14);

            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }
        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files) {

        logger.info("delete file: " + files);

        if (files == null || files.length == 0) {
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }

        for (String fileName : files) {
            final String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            final MediaType mType = MediaUtil.getMediaType(formatName);

            if (mType != null) {
                final String front = fileName.substring(0, 12);
                final String end = fileName.substring(14);

                new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
            }
            new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
        }
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
