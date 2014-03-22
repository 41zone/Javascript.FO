package cc.fozone.javascript.handler;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import cc.fozone.javascript.struct.At;
import cc.fozone.javascript.utils.Property;

/**
 * 标示符比较方法
 * @author jimmy song
 */
public class AtPropertyComparator implements Comparator<Property> {
	private static final String[] AT_INDEX = new String[]{
		At.NAMESPACE,At.CLASS,At.ATTRIBUTE,At.METHOD,At.PARAM,At.RETURN,At.TEXT
	};
	@Override
	public int compare(Property p1, Property p2) {
		// TODO Auto-generated method stub
		String key1 = p1.getKey();
		String key2 = p2.getKey();
		int index1 = indexOfArray(AT_INDEX, key1);
		int index2 = indexOfArray(AT_INDEX, key2);
		return index1 - index2;
	}
	
	/**
	 * 获取数组下表
	 * @param array
	 * @param str
	 * @return
	 */
	private int indexOfArray(String[] array, String str){
		if(array == null) return -1;
		if(StringUtils.isBlank(str)) return -1;
		String to = str.trim().toLowerCase();
		for(int i = 0;i<array.length;i++){
			if(to.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}
}
