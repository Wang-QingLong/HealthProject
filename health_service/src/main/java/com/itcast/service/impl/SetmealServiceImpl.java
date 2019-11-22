package com.itcast.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itcast.entity.PageResult;
import com.itcast.mapper.CheckGroupMapper;
import com.itcast.mapper.SetmealMapper;
import com.itcast.pojo.CheckGroup;
import com.itcast.pojo.Setmeal;
import com.itcat.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @author: WangQingLong
 * @date: 2019/11/21 22:28
 * @description: 套餐服务
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
  private CheckGroupMapper checkGroupMapper;

//    @Autowired
//    private JedisPool jedisPool;


    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //使用分页插件(告诉分页拦截器我现在要分页)
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealMapper.findPage(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 新增
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //将前台返回的套餐信息加入到t_setmeal当中
        setmealMapper.add(setmeal);
        //设置关联关系
        setmealIdAndCheckGroupIds(setmeal.getId(), checkgroupIds);

//        //将图片名称保存到Redis
//        savePic2Redis(setmeal.getImg());
    }

    /**查询回显数据查询:套餐表单数据,所有检查组数据,被勾选的检查组数据
     * @param id
     * @return
     */
    @Override
    public Map dialogFormVisible4Edit(Integer id) {
        //创建一个map集合,封装这些数据
        Map<String, Object> map = new HashMap<>();
        //获取setmeal数据
         Setmeal setmeal=setmealMapper.findSetmealById(id);
         //获取所有检查组数据
        List<CheckGroup> checkGroups = checkGroupMapper.findAll();
        //获取中间表里面的checkgroupIds
     List<Integer> groupIds=  setmealMapper.findCheckGroupIdsById(id);

     //封装需要查询的数据
        map.put("setmeal",setmeal);
        map.put("checkGroups",checkGroups);
        map.put("groupIds",groupIds);

               return map;

    }

    /**更新数据
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void Update(Setmeal setmeal, Integer[] checkgroupIds) {

        //首先需要删除原来的关联关系,根据套餐表id删除中间表数据（清理原有关联关系）
        setmealMapper.deleteAssociation(setmeal.getId());
        //向中间表(t_setmeal_checkgroup)插入数据（建立套餐和检查组关联关系）
        setmealIdAndCheckGroupIds(setmeal.getId(), checkgroupIds);
        //更新套餐基本信息
        setmealMapper.Update(setmeal);
    }

    /**查询中间表引用关系
     * @param id
     * @return
     */
    @Override
    public Integer findCountById(Integer id) {
        return setmealMapper.findCountById(id);

    }

    /**逻辑删除套餐数据
     * @param id
     */
    @Override
    public void delete(Integer id) {
        setmealMapper.delete(id);
    }

    /**
     * 将图片名称保存到Redis
     *
     * @param pic
     */
//    private void savePic2Redis(String pic) {
//        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, pic);
//    }


    /**
     * 创建检查组和套餐之间的关联关系，并且加入到中间表t_setmeal_checkgroup表当中
     *
     * @param setmealId
     * @param checkgroupIds
     */
    private void setmealIdAndCheckGroupIds(Integer setmealId, Integer[] checkgroupIds) {

        //判断返回的数组是否为空
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            //不为空,则遍历
            //先将两张表之间的关联数据封装到map集合
            List<Map> maps = new ArrayList<>();
            for (Integer checkgroupId : checkgroupIds) {
                Map map = new HashMap();
                map.put("setmeal_id", setmealId);
                map.put("checkgroup_id", checkgroupId);
                maps.add(map);
            }
            setmealMapper.setCheckGroupAndCheckItem(maps);

        }
    }
}
