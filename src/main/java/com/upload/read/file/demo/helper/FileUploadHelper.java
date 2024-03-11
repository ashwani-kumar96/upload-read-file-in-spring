package com.upload.read.file.demo.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    // Here in UPLOAD_DIR, I have given the location of my spring boot
    // resource->static->show-file(new folder created by me, so I can upload the file here) package


    // By dynamic (it will be uploaded inside target folder)
    public final String UPLOAD_FILE = new ClassPathResource("static/show-file").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile multipartFile) {

        boolean flag = false;
        try {

            //
            /*InputStream inputStream = multipartFile.getInputStream();
            byte data[] = new byte[inputStream.available()];
            inputStream.read();

            // Write data
            FileOutputStream fileOutputStream = new FileOutputStream(
                    UPLOAD_DIR+ File.separator+ multipartFile.getOriginalFilename());

            fileOutputStream.write(data);

            fileOutputStream.flush();
            fileOutputStream.close();*/

            // Alternative (By using nio package)
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_FILE+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean fileUpload(MultipartFile multipartFile) {

        boolean flag = false;
        try {

            //
            /*InputStream inputStream = multipartFile.getInputStream();
            byte data[] = new byte[inputStream.available()];
            inputStream.read();

            // Write data
            FileOutputStream fileOutputStream = new FileOutputStream(
                    UPLOAD_DIR+ File.separator+ multipartFile.getOriginalFilename());

            fileOutputStream.write(data);

            fileOutputStream.flush();
            fileOutputStream.close();*/

            // Alternative (By using nio package)
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_FILE+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
