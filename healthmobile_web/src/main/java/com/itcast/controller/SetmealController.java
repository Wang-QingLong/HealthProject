package com.itcast.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itcast.constant.MessageConstant;
import com.itcast.entity.Result;
import com.itcast.pojo.Setmeal;
import com.itcat.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version V1.0
 * @author: WangQingLong
 * @date: 2019/11/24 23:22
 * @description:
 */
@RestController
@RequestMapping("setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    /**
     * 查询所有的套餐信息
     *
     * @return
     */
    @RequestMapping("getSetmeal")
    public Result getSetmeal() {
        List<Setmeal> setmealList = setmealService.findAll();
        if (CollUtil.isNotEmpty(setmealList)) {
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmealList);
        }
        return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
    }


    /**
     * 根据Id查询该套餐所有信息:包括检查组和检查项信息
     *
     * @param id
     * @return
     */
    @RequestMapping("findById")
    public Result findById(Integer id) {
        Setmeal setmeal = setmealService.findById(id);
        if (setmeal!=null) {
            return  new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }
        return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
    }
}
