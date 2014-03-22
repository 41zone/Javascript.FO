package cc.fozone.javascript.utils;

import org.apache.commons.lang.StringUtils;

/**
 * API工具包
 * @author jimmy song
 *
 */
public class APIUtils {
	/**
	 * 根据Prototype的全名，获取其域名空间和类型名
	 * @param prototypeName 类型名称
	 * @return 解析后的数据
	 */
	public static Property execPrototypeName(String prototypeName){
		if(StringUtils.isBlank(prototypeName)) return null;
		Property p = new Property();
		int lastP = prototypeName.lastIndexOf(".");
		if(lastP < 0) { 
			p.setKey("window");
			p.setValue(prototypeName);
		} else {
			p.setKey(prototypeName.substring(0,lastP));
			p.setValue(prototypeName.substring(lastP+1));
		}
		return p;
	}
}	
