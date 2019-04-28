package com.lzx.hsapp.dto;

import java.io.Serializable;

public class FileDto implements Serializable {


    private static final long serialVersionUID = -8246079640015004070L;

    private Integer fileId;     //文件ID

    private String fileName;        //文件名称

    private String fileUrl;     //文件url

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
