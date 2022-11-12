package com.zzy.controller;

import cn.hutool.core.io.resource.ResourceUtil;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.constant.CommonEnum;
import com.zzy.utils.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 文件上传下载controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API)
public class UploadDownController {

    @Value("${server.port}")
    private  int port;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        //获取文件存储的位置
        String path= ResourceUtils.getURL("classpath:").getPath()+"/static/upload";
        File filePath=new File(path);
        System.out.println("文件保存的位置："+path);
        if(!filePath.exists()&&!filePath.isDirectory()){
            System.out.println("目标目录不存在，创建目录" +filePath);
            filePath.mkdir();
        }
        //获取原始文件的名称
        String originFileName=file.getOriginalFilename();
        System.out.println("原始文件的名称："+originFileName);

        //获取文件类型
        String type =originFileName.substring(originFileName.lastIndexOf('.')+1);
        System.out.println("文件类型："+type);
        //获取文件名称
        String name=originFileName.substring(0,originFileName.lastIndexOf('.'));

        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String date=sdf.format(d);
        String fileName=date+name+'.'+type;
        System.out.println("新文件的名称："+fileName);

        //在指定的路径下创建文件
        File targetFile=new File(path,fileName);
        try{
            file.transferTo(targetFile);
            System.out.println("上传成功");
            Map<String ,Object> map=new HashMap<>();
            //将文件在服务器的存储路径返回
            map.put("url","http://localhost:"+port+"/upload/"+fileName);
            return new Result(map);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
            return new Result(CommonEnum.UPLOAD_FAIL);
        }

    }

}
