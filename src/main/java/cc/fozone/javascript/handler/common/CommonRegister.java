package cc.fozone.javascript.handler.common;

import java.util.Map;

import org.springframework.stereotype.Component;

import cc.fozone.javascript.ICommonHandler;

/**
 * 控制器注册器
 * @author jimmy song
 *
 */
@Component
public class CommonRegister {
	/**
	 * 获取控制器
	 * @param name 控制器名称
	 * @return 注释控制器
	 */
	public ICommonHandler getHandler(String name){
		if(handlerMap == null) return null;
		return handlerMap.get(name);
	}
	
	private Map<String,ICommonHandler> handlerMap;

	public Map<String, ICommonHandler> getHandlerMap() {
		return handlerMap;
	}

	public void setHandlerMap(Map<String, ICommonHandler> handlerMap) {
		this.handlerMap = handlerMap;
	}
	
}
