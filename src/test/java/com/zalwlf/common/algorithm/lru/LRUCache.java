package com.zalwlf.common.algorithm.lru;

import com.zalwlf.common.constant.Constants;
import com.zalwlf.common.util.Assert;
import com.zalwlf.common.util.CollectionsUtil;
import com.zalwlf.common.util.FileUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * platform
 * <p>LRU算法实现</p>
 *
 * @author : lcq
 * @date : 2020-09-13 22:02
 **/
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    //首先设定最大缓存空间 MAX_ENTRIES 为 3；
    private static int MAX_ENTRIES = 3;

    //使用LinkedHashMap的构造函数将 accessOrder设置为 true，开启 LRU顺序；
    public LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }

    //使用LinkedHashMap的构造函数将 accessOrder设置为 true，开启 LRU顺序，并初始化空间大小
    public LRUCache(int maxEntries) {
        super(MAX_ENTRIES, 0.75f, true);
        Assert.isTrue(maxEntries >= 2, "The minimum initial capacity of LRU algorithm is 2.");
        MAX_ENTRIES = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return  size() > MAX_ENTRIES;
    }

    public static void main(String[] args) {
        System.out.println("周1执行者 : 郭子敬\n" +
                "周2执行者 : 霍晓杰\n" +
                "周3执行者 : 刘超奇\n" +
                "周4执行者 : 吕继伦");
        for (int n = 0; n < 3; n++) {
            String[] allExecutors;
            String filePath = "E:\\Data\\privateData\\ProhibitDelete\\LRUExecutionRecord.txt";
            String s = FileUtil.readString(filePath);
            if (s == null){
                allExecutors = new String[]{"郭子敬","霍晓杰","刘超奇","吕继伦"};
            }else{
                allExecutors = s.split(Constants.PLATFORM_BLOG_V_CHAR_SPLIT);
            }
            //初始化数据
            LRUCache<Integer, String> cache = new LRUCache<>(allExecutors.length - 1);
            Map<String,Integer> lastExecutorRecord = new HashMap<>();
            for (int i = 0; i < allExecutors.length - 1; i++) {
                cache.put(i,allExecutors[i]);
            }

            //具体实现
            cache.put(allExecutors.length,allExecutors[allExecutors.length - 1]);
            AtomicReference<String> currExecutor = findMissingExecutor(allExecutors, cache);
            System.out.println("周"+(n+5)+"执行者 : "+currExecutor.get());
            List<String> record = new ArrayList<>(cache.values());
            record.add(currExecutor.get());

            FileUtil.writeString(filePath, CollectionsUtil.listToStringSplit(record));
        }
    }

    private static AtomicReference<String> findMissingExecutor(String[] allExecutors, LRUCache<Integer, String> cache) {
        Set<Map.Entry<Integer, String>> entries = cache.entrySet();
        List<String> readyExecutors = new ArrayList<>(allExecutors.length - 1);
        entries.forEach(entry -> readyExecutors.add(entry.getValue()));

        ArrayList<String> allExecutorsList = new ArrayList<>(Arrays.asList(allExecutors));
        AtomicReference<String> missExecutor = new AtomicReference<>();
        allExecutorsList.forEach(executor -> {if (!readyExecutors.contains(executor)) missExecutor.set(executor);});
        return missExecutor;
    }

}
