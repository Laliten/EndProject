package com.aigrow.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础功能操作类
 * 其他DAO继承此类获取常用的数据库操作方法
 * @author YangDeJian
 */
public interface BaseDao<T> {
    /**
     * 保存一个对象
     *
     * @param o
     *            对象
     * @return 对象的ID
     */
    public Serializable save(T o);

    /**
     * 删除一个对象
     *
     * @param o
     *            对象
     */
    public void delete(T o);

    /**
     * 更新一个对象
     *
     * @param o
     *            对象
     */
    public void update(T o);

    /**
     * 保存或更新一个对象
     *
     * @param o
     *            对象
     */
    public void saveOrUpdate(T o);

    /**
     * 通过主键获得对象
     *
     * @param c
     *            类名.class
     * @param id
     *            主键
     * @return 对象
     */
    public T get(Class<T> c, Serializable id);

    /**
     * 通过HQL语句获取一个对象
     *
     * @param hql
     *            HQL语句
     * @return 对象
     */
    public T get(String hql);

    /**
     * 通过HQL语句获取一个对象
     *
     * @param hql
     *            HQL语句
     * @param params
     *            参数
     * @return 对象
     */
    public T get(String hql, Map<String, Object> params);

    /**
     * 获得对象列表
     *
     * @param hql
     *            HQL语句
     * @return List
     */
    public List<T> find(String hql);

    /**
     * 获得分页后的对象列表
     *
     * @param hql
     *            HQL语句
     * @param page
     *            要显示第几页
     * @param rows
     *            每页显示多少条
     * @return List
     */
    public List<T> find(String hql, int page, int rows);

    /**
     * 统计数目
     *
     * @param hql
     *            HQL语句(select count(*) from T)
     * @return long
     */
    public Long count(String hql);


    /**
     * 执行一条HQL语句
     *
     * @param hql
     *            HQL语句
     * @return 响应结果数目
     */
    public int executeHql(String hql);

    List<T> findByHQL(String hql, Map<String,Object> map,int row,int page);

    List<T> findByHQL(String hql,Map<String,Object> map);

    List<T> findBySQL(String sql,Map<String,Object> map);
}