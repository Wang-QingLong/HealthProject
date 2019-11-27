package com.itcast.mapper;

import com.itcast.pojo.Member;
import com.itcast.pojo.OrderSuccessMsg;
import org.apache.ibatis.annotations.Param;

/**
 * @version V1.0
 * @author: WangQingLong
 * @date: 2019/11/26 0:43
 * @description:
 */
public interface MemberMapper {
    /**
     * 根据用户提供的手机号查询是否存在数据
     *
     * @param telephone
     * @return
     */
    Member findByTelephone(@Param("telephone") String telephone);

    /**添加数据到t_member
     * @param member
     */
    void add(Member member);


}
