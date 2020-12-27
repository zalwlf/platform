package com.zalwlf.common.util;

import com.zalwlf.common.constant.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * platform
 * <p>集合操作API</p>
 *
 * @author : lcq
 * @date : 2020-09-13 23:15
 **/
public class CollectionsUtil {

    public static String listToStringSplit(Collection<String> collection){
        StringBuilder result = new StringBuilder();
        collection.forEach(str->result.append(str).append(Constants.PLATFORM_BLOG_V_CHAR_SPLIT));
        return result.substring(0,result.length() - 1);
    }

    public static Collection<String> stringToListSplit(String content){
        String[] split = content.split(Constants.PLATFORM_BLOG_V_CHAR_SPLIT);
        return new ArrayList<>(Arrays.asList(split));
    }

}
