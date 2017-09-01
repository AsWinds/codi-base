package com.codi.base.dao.v2;

public class GeneratedBaseDaoImpl<T> extends BaseDaoImpl<T> implements GeneratedBaseDao<T> {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return super.delete(generateStatement("deleteByPrimaryKey"), id);
	}

	@Override
	public int insertSelective(T record) {
		return super.insert(generateStatement("insertSelective"), record);
	}

	@Override
	public T selectByPrimaryKey(Long id) {
		return super.getObject(generateStatement("selectByPrimaryKey"), id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return super.update(generateStatement("updateByPrimaryKeySelective"), record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(T record) {
		return update(generateStatement("updateByPrimaryKeyWithBLOBs"), record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return update(generateStatement("updateByPrimaryKey"), record);
	}

	@Override
	public int insert(T record) {
		return super.insert(generateStatement("insert"), record);
	}

}
