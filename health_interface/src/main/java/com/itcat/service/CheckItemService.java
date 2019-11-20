package com.itcat.service;


import com.itcast.entity.PageResult;
import com.itcast.pojo.CheckItem;

/**
 * @version V1.0
 * @author: WangQingLong
 * @date: 2019/11/18 18:46
 * @description: 检查项服务接口
 */
public interface CheckItemService {
    /**
     * 新增
     *
     * @param checkItem
     */
    void add(CheckItem checkItem);


    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

}
