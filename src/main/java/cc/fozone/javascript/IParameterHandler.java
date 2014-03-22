package cc.fozone.javascript;

import java.util.List;

import cc.fozone.javascript.struct.Method;
import cc.fozone.javascript.struct.Prototype;
import cc.fozone.javascript.utils.Property;

/**
 * 参数控制器接口，该结构
 * @author sunspot
 *
 */
public interface IParameterHandler {
	/**
	 * 解析类中的参数
	 * @param prototype 类体
	 * @param block 注释块
	 */
	public void execute(Prototype prototype,List<Property> block);
	
	/**
	 * 解析类中的参数
	 * @param prototype
	 * @param property
	 */
	public void execute(Prototype prototype,Property property);
	
	/**
	 * 解析方法中的参数
	 * @param method 方法
	 * @param block 注释块
	 */
	public void execute(Method method,List<Property> block);
	
	/**
	 * 解析方法中的某一个参数
	 * @param method
	 * @param property
	 */
	public void execute(Method method , Property property);
}
