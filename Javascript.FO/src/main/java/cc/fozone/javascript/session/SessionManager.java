package cc.fozone.javascript.session;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import cc.fozone.javascript.struct.Namespace;

/**
 * 会话控制器
 * @author jimmy song
 *
 */
@Component
public class SessionManager {
	/**
	 * 命名空间映射
	 */
	private Map<String,Namespace> namespaceMap;
	public SessionManager() {
		// TODO Auto-generated constructor stub
		this.namespaceMap = new HashMap<String,Namespace>();
	}
	public Map<String,Namespace> getNamespaceMap() {
		return this.namespaceMap;
	}
}
