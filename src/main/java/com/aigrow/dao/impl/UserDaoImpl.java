package com.aigrow.dao.impl;

import com.aigrow.dao.UserDao;
import com.aigrow.model.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangDeJian
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    /**
     * 根据用户id列表进行用户的删除
     *
     * @param userIds
     */
    @Override
    public void batchDelete(String userIds) {
        if(userIds == null||userIds.trim().length()==0){
            return;
        }
        List<Integer> idList = new ArrayList<Integer>(0);
        String[] idArr = userIds.split(",");
        for(String id:idArr){
            if(id!=null){
                idList.add(Integer.parseInt(id));
            }
        }

        Query query = getCurrentSession().createQuery("delete from User u where u.id in:ids");
        query.setParameter("ids",idList);
        query.executeUpdate();
    }
}
