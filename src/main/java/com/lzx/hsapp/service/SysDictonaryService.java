package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.SysDictionary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangdaren on 2018/3/26.
 */
@Component
public interface SysDictonaryService {

    List<SysDictionary> selectAll(SysDictionary sysDictionary);

    SysDictionary getById(Integer id);

    SysDictionary getByCodeFlag(Integer codeFlag);
}
