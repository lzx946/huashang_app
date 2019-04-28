package com.lzx.hsapp.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 类型转换工具
 */
public class Transform {

    /**
     * List转String
     * @param list
     * @return
     */
    public static String listToString(List<String> list){

        if(list==null){
            return null;
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;

        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    /**
     * String转List
     * @param strs
     * @return
     */
    private List<String> stringToList(String strs){
        String str[] = strs.split(",");
        return Arrays.asList(str);
    }
}
