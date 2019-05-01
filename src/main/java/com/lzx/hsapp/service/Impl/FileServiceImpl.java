package com.lzx.hsapp.service.Impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lzx.hsapp.dao.SysFileMapper;
import com.lzx.hsapp.entity.SysFile;
import com.lzx.hsapp.service.FileService;
import com.lzx.hsapp.utils.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

/**
 * Created by wangdaren on 2018/2/2.
 */
@Service
@Component
public class FileServiceImpl implements FileService {

    Logger logger= LoggerFactory.getLogger(getClass().getName());

    @Value("${preview.url}")
    private String preview;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Override
    public int insert(SysFile record) {
        int flag=sysFileMapper.insert(record);
        if(flag==1){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        try {
            SysFile sysFiles=sysFileMapper.selectByPrimaryKey(id);
            fastFileStorageClient.deleteFile(sysFiles.getUrl());
            int flag=sysFileMapper.deleteByPrimaryKey(id);
            if(flag==1){
                return 1;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return 0;
    }

    @Override
    public SysFile selectByPrimaryKey(Integer id) {
        SysFile sysFile=sysFileMapper.selectByPrimaryKey(id);
        if(sysFile!=null){
            return sysFile;
        }
        else
        {
            return null;
        }
    }

    @Override
    public SysFile getByUrl(String url){
        if (StringUtils.isEmpty(url)){
            return null;
        }

        SysFile file = sysFileMapper.findByUrl(url);
        if (file == null){
            return null;
        }else {
            return file;
        }
    }

    @Override
    public String download2UploadImg(String oldUrl, String courseId) throws Exception {
        //1.下载图片
        File tempFile = null;
        InputStream is = null;

        StorePath storePath = null;
        try {
            tempFile = ImageUtil.downloadFromUrl(oldUrl);
            String fileName = courseId + "_sign_in_QRCode.jpg";
//            if (oldUrl.contains(SPECIAL_SIGN)) {
//                fileName = oldUrl.substring(oldUrl.lastIndexOf("/") + 1, oldUrl.indexOf(SPECIAL_SIGN));
//            } else {
//                fileName = oldUrl.substring(oldUrl.lastIndexOf("/") + 1);
//            }
            //校验格式
//            String suffix = fileName.substring(fileName.indexOf("."));
//            if (!ImageContentType.toMap().containsKey(suffix)) {
//                log.info("文件格式不对，该格式为={}", suffix);
//                throw new Exception("图片格式不对");
//            }
            is = new FileInputStream(tempFile);
            if (tempFile == null || is == null || is.available() <= 0) {
                logger.info("图片为空");
                throw new Exception("图片为空");
            }
            //2.上传图片
            storePath = fastFileStorageClient.uploadFile(is,tempFile.length(), FilenameUtils.getExtension(fileName),null);
//            newPic = FastDfsUtil.uploadFile(is, fileName);
        } finally {
            //手动删除临时文件
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new IOException("文件流关闭失败");
                }
            }
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        logger.info("上传完之后新图片地址="+(preview + storePath.getFullPath()));
        return storePath.getFullPath();
    }
}
