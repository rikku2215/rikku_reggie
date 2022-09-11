package com.rikku.reggie.controller;

import com.rikku.reggie.common.R;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController()
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;


    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix=  originalFilename.substring(originalFilename.lastIndexOf("."));
        //利用UUID重新生成文件名，防止文件名重复造成文件覆盖
        String fileName = UUID.randomUUID().toString()+suffix;

        File dir = new File(basePath);
        if (!dir.exists()){
            dir.mkdir();
        }

        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }



    @GetMapping("/download")
    public void download(HttpServletResponse response, String name){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));

            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len=0;
            byte[] bytes = new byte[1024];
            while((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
