package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.SysFile;

/**
 * Created by wangdaren on 2018/2/2.
 */
public interface FileService {
    int insert(SysFile record);
    int deleteByPrimaryKey(Integer id);
    SysFile selectByPrimaryKey(Integer id);

    SysFile getByUrl(String url);
}
