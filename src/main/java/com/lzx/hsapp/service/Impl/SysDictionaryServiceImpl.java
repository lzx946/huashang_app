package com.lzx.hsapp.service.Impl;

import com.lzx.hsapp.dao.SysDictionaryMapper;
import com.lzx.hsapp.entity.SysDictionary;
import com.lzx.hsapp.service.SysDictonaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangdaren on 2018/3/26.
 */
@Service
public class SysDictionaryServiceImpl implements SysDictonaryService {
    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;
    @Override
    public List<SysDictionary> selectAll(SysDictionary sysDictionary) {
        sysDictionary.setCode(100);
        List<SysDictionary> sysDictionaries=sysDictionaryMapper.selectAll(sysDictionary);
        if(sysDictionaries!=null){
            return sysDictionaries;
        }
        return null;
    }
}
