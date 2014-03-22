package cc.fozone.javascript;

import java.util.List;

import cc.fozone.javascript.utils.Property;

/**
 * 单元注释解析接口
 * @author jimmy song
 *
 */
public interface IPropertyHandler {
	/**
	 * 解析某一个单元注释
	 * @param property 单元注释
	 * @param block 单元注释对应的整体块
	 */
	public void execute(Property property,List<Property> block);
}
