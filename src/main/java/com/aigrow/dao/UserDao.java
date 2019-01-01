package com.aigrow.dao;

import com.aigrow.model.entity.User;

/**
 * 用户数据库操作类
 * @author YangDeJian
 */
public interface UserDao extends BaseDao<User>{
    /**
     * 根据用户id列表进行用户的删除
     * @param userIds
     */
    void batchDelete(String userIds);
}
