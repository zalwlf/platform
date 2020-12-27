package com.zalwlf.po;

/**
 * platform
 * <p>数据初始化规范</p>
 *
 * @author : lcq
 * @date : 2020-09-21 10:42
 **/
public interface EntityInitializer {

    /**
     * 默认数据初始化方法,用于新增时初始化部分数据
     */
    void createDefaultNewItem();

    /**
     * 默认数据初始化方法,用于修改时初始化部分数据
     */
    void createDefaultModification();

    /**
     * 默认数据初始化方法,用于返回视图时初始化部分数据
     */
    void loadDefaultViewData();


}
