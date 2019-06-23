package com.lzx.hsapp.service;

import com.lzx.hsapp.entity.Studentsinfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentsinfoService {
    int deleteByPrimaryKey(Integer paramInteger);

    int insert(Studentsinfo paramStudentsinfo);

    Studentsinfo selectByPrimaryKey(String paramString);

    List<Studentsinfo> selectAll();

    int updateByPrimaryKey(Studentsinfo paramStudentsinfo);

    Studentsinfo selectByPhone(String paramString);

    int updateByPhone(Studentsinfo paramStudentsinfo);
}


/* Location:              D:\eclipseWorkSpace\base-api-service-api-0.0.1-SNAPSHOT\ImportedClasses\!\top\tsama\baseapiserviceapi\StudentsinfoService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */