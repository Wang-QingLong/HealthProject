package com.itcast.mapper;

import com.github.pagehelper.Page;
import com.itcast.entity.PageResult;
import com.itcast.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

/**
 * @version V1.0
 * @author: WangQingLong
 * @date: 2019/11/18 18:51
 * @description: 持久层dao接口
 */
public interface CheckItemMapper {
    /**
     * 新增检查项
     *
     * @param checkItem
     */
    void add(CheckItem checkItem);


    /**
     * 分页查询
     *
     * @param queryString
     * @return
     */
    Page<CheckItem> findPage(@Param("queryString") String queryString);

    /**
     * Id查询
     *
     * @param row
     * @return
     */
    PageResult findById(@Param("row") Integer row);
}
