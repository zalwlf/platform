package com.zalwlf.common.algorithm.drawlots;

import java.util.HashMap;
import java.util.Map;

/**
 * platform
 * <p>元素池，保存元素及单位总频次</p>
 *
 * @author : lcq
 * @date : 2020-09-14 18:48
 **/
public class ElementsCache<K> extends HashMap<K,Integer> implements Cache<K> {

    private int initialCapacity;

    public ElementsCache(int initialCapacity) {
        super(initialCapacity);
        this.initialCapacity = initialCapacity;
    }

    @Override
    public Integer put(K key, Integer value) {
        if (this.containsKey(key) || this.size() < this.initialCapacity){
            return super.put(key, value);
        }else{
            return null;
        }
        //return super.put(key, value);
    }





    public static void main(String[] args) {
        Cache<String> cache2 = new ElementsCache<>(4);



    }
}
