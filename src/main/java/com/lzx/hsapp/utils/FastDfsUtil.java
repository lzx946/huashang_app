package com.lzx.hsapp.utils;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Serializable;

public class FastDfsUtil implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(FastDfsUtil.class);

    private static final long serialVersionUID = 4251723708970039567L;


    /**
     * 文件上传
     * @param is
     * @param fileName
     * @return
     */
//    public static String uploadFile(InputStream is, String fileName){
//        TrackerServer trackerServer = TrackerServerPool.borrowObject();
//        StorageClient1 storageClient = new StorageClient1(trackerServer, null);
//        try {
//            //读取流
//            byte[] content = new byte[is.available()];
//            is.read(content, 0, content.length);
//            //文件扩展名
//            String ext = FilenameUtils.getExtension(fileName);
//            //mata list是表文件的描述
//            NameValuePair[] mata_list = new NameValuePair[1];
//            mata_list[0] = new NameValuePair("fileName", fileName);
//            // 上传
//            String path  = storageClient.upload_file1(content, ext, mata_list);
//            return path;
//        } catch (Exception e) {
//            log.error("上传图片到fastDFS失败={}", e.getMessage());
//            throw new UploadException("上传图片到fastDFS失败");
//        } finally{
//            // 返还对象
//            TrackerServerPool.returnObject(trackerServer);
//        }
//    }
}
