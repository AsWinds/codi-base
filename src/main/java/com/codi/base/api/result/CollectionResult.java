package com.codi.base.api.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.alibaba.fastjson.annotation.JSONField;
import com.codi.base.domain.BaseResult;

/**
 * 
 * 表示一般意义的集合类返回结果
 * 
 * @author hzren
 * @since pangu003
 * 
 * */
public class CollectionResult<T> extends BaseResult {

	private static final long serialVersionUID = 1L;
	
	private final Collection<T> objs;
	private final Long totalNum;
	
	public CollectionResult(Collection<T> objs) {
		super();
		this.objs = objs;
		this.totalNum = null;
	}
	
	public CollectionResult(Collection<T> objs, long total) {
		super();
		this.objs = objs;
		this.totalNum = total;
	}

	/**
	 * 
	 * @return 返回一个不可修改的元素集合, 如果没有元素, 返回一个空的不可修改集合
	 * 
	 * */
	@JSONField
	public Collection<T> getObjs() {
		if (objs == null) {
			return Collections.unmodifiableList(new ArrayList<T>(0));
		}
		return Collections.unmodifiableCollection(objs);
	}
	
	/**
	 * 
	 * @return 获取集合里所有元素的个数, 没有元素, 返回0
	 * 
	 * */
	@JSONField
    public long getRowCount() {
        if (objs != null) {
        	return objs.size();
		}
        return 0;
    }

	/**
	 * 
	 * @return 返回设置过的所有元素个数, 如果没有设置过总个数, 返回null
	 * 
	 * */
	@JSONField
	public Long getTotalNum() {
		return totalNum;
	}  

}
