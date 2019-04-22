package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.SysDictionary;

import java.util.List;

/**
 * Created by wangdaren on 2018/3/26.
 */
public interface SysDictonaryService {

    List<SysDictionary> selectAll(SysDictionary sysDictionary);
}
