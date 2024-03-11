package com.upload.read.file.demo.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.upload.read.file.demo.helper.FileUploadHelper;
import com.upload.read.file.demo.services.ConvertXMLToJSON;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;
    @Autowired
    private ConvertXMLToJSON convertXMLToJSON;

    //upload file
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile multipartFile) {

        try {

            // Validation
            if (multipartFile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Request must contain file");
            }

            // Which type of file needs to be uploaded (in our scenario only "csv" and "ods" allowed)
            if (multipartFile.getContentType().equals("csv")) {
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("Only csv type files are allowed");
            }

            // File upload code.

            boolean checkUpload = fileUploadHelper.uploadFile(multipartFile);
            if (checkUpload) {

                // here it will return this message
                //return ResponseEntity.ok("File uploaded successfully");

                // it will return the path or url of the file
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/show-file").path(multipartFile.getOriginalFilename()).toUriString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong. Try again.");
    }

    @PostMapping("/file-upload")
    public ResponseEntity<String> fileUpload(
            @RequestParam("file") MultipartFile multipartFile) {

        try {

            // Validation
            if (multipartFile.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Request must contain file");
            }

            // Which type of file needs to be uploaded (in our scenario only "csv" and "ods" allowed)
            if (multipartFile.getContentType().equals("csv")) {
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("Only csv type files are allowed");
            }

            // File upload code.

            boolean checkUpload = fileUploadHelper.fileUpload(multipartFile);
            if (checkUpload) {

                // here it will return this message
                return ResponseEntity.ok("File uploaded successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong. Try again.");
    }


    // Read the file by row number
    @PostMapping("/readByRow")
    public Object parseCSV(@RequestParam("file")MultipartFile multipartFile) throws IOException, CsvException {


        try {
        boolean checkUpload = fileUploadHelper.uploadFile(multipartFile);
        if (checkUpload) {

            // here it will return this message
            //return ResponseEntity.ok("File uploaded successfully");

            // it will return the path or url of the file
            /*return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/show-file").path(multipartFile.getOriginalFilename()).toUriString());*/
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

        // it will take the input from user (like enter row number)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row number : ");
        int row = scanner.nextInt();
        Reader reader = new InputStreamReader(multipartFile.getInputStream());

        // Parse CSV data
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
        List<String[]> rows = csvReader.readAll();

        // Analyze data...

        //return "Processed " + rows.size() + " rows!";
        return rows.get(row);
    }


    // convert xml to json
    @GetMapping("/xml-to-json")
    public String showJson() {

        String str = convertXMLToJSON.showJSON();
        return str;
    }
}
