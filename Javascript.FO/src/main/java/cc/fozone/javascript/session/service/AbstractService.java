package cc.fozone.javascript.session.service;

import java.util.Map;

import javax.annotation.Resource;

import cc.fozone.javascript.session.SessionManager;
import cc.fozone.javascript.struct.Namespace;
import cc.fozone.javascript.struct.Prototype;

/**
 * 抽象服务层
 * @author sunspot
 *
 */
public class AbstractService {
	@Resource SessionManager sessionManager;
	public Map<String,Namespace> getNamespaceMap() {
		return this.sessionManager.getNamespaceMap();
	}
	
	/**
	 * 根据命名空间获得原型对象映射表
	 * @param namespace 原型名称
	 * @return 原型映射
	 */
	public Map<String,Map<String,Prototype>> getPrototypeMap(String namespace){
		Map<String,Namespace> map = this.getNamespaceMap();
		if(map == null || map.isEmpty()) return null;
		Namespace ns = map.get(namespace);
		return ns.getPrototypes();
	}
}
