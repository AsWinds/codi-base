package com.codi.base.dao.v2;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

interface BaseDao<E> {
    /*
     * 插入数据
     */
    int insert(String statement, Object parameter);

    int insertBatch(List<?> list);

    int insertBatch(String statement, List<?> list);

    /*
     * 删除数据
     */
    int delete(String statement, Object parameter);

    int deleteBatch(List<?> list);

    int deleteBatch(String statement, List<?> list);

    /*
     * 更新数据
     */
    int update(String statement, Object parameter);

    int updateBatch(List<?> list);

    int updateBatch(String statement, List<?> list);

    /*
     * 获得单个对象
     */
    E getObject(String statement, Object parameter);

    /*
     * 获得list
     */
    List<E> findList(String statement, Object parameter);
    
    List<E> findList(String statement, Object parameter, RowBounds rowBounds);
}
