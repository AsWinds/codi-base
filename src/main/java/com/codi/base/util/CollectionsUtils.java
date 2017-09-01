package com.codi.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;

/**
 * 
 * @author hzren
 * @since pangu003
 * 
 * */
public class CollectionsUtils {
	
	/**
	 * 生成一个新的Set, Set里的元素来自集合 source, 经 function转化后放入结果Set
	 * 
	 * @param source
	 * @param function
	 * 
	 * @author hzren
	 * 
	 * */
	public static <F, T> Set<T> newSet(Collection<F> source, Function<F, T> function) {
		HashSet<T> set = new HashSet<>();
		for (F f : source) {
			set.add(function.apply(f));
		}
		return set;
	}
	
	/**
	 * 
	 * 生成一个新的 List<Map<K, V>>, List和Map都是新的, 可修改的, Map里的元素来自目标集合
	 * 该方法并不clone Map里的元素
	 * 
	 * @author hzren
	 * 
	 * */
	public static <K, V> List<Map<K, V>> copy(List<Map<K, V>> source){
		ArrayList<Map<K, V>> res = new ArrayList<>();
		for (Map<K, V> map : source) {
			res.add(new HashMap<K, V>(map));
		}
		return res;
	}
	
}
