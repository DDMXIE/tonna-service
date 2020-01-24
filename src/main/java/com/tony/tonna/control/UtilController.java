/**
 * Create by Tony
 * 2020.1.23 20:01
 * 配置工具业务 Control
 */
package com.tony.tonna.control;

import com.tony.tonna.service.UtilService;
import com.tony.tonna.util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class UtilController {

    @Resource
    private UtilService utilService;

    /**
     * 获取配置类相应类型全部数据
     * @param constType
     * @return
     */
    @GetMapping("/admin/findConstType")
    public Result findArticleType(@RequestParam(value = "constType") String constType){
        if(constType == null){
            return Result.fail();
        }else{
            return Result.success(200,utilService.findConstType(constType));
        }
    }

    /**
     * 上传文件
     * @param uploadFile
     * @param req
     * @return
     */
    @PostMapping("/admin/uploadFile")
    public String upload(MultipartFile uploadFile, HttpServletRequest req){
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try{
            uploadFile.transferTo(new File(folder,newName));
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" +req.getServerPort()
                    + "/uploadFile/" + format + newName;
            return filePath;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "上传失败！";
    }
}
