package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.SysFile;
import org.springframework.stereotype.Component;

/**
 * Created by wangdaren on 2018/2/2.
 */
@Component
public interface FileService {
    int insert(SysFile record);
    int deleteByPrimaryKey(Integer id);
    SysFile selectByPrimaryKey(Integer id);

    SysFile getByUrl(String url);
}
