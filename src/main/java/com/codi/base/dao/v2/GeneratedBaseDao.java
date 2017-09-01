package com.codi.base.dao.v2;

/**
 * 插件自动生成接口
 * 
 * @author hzren
 * @date 2017-05-02 14:48
 */
public interface GeneratedBaseDao<T> extends BaseDao<T> {
	
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);
}