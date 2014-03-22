package cc.fozone.javascript;

import java.util.List;

import cc.fozone.javascript.struct.Common;
import cc.fozone.javascript.utils.Property;

/**
 * 通用处理器
 * @author jimmy song
 *
 */
public interface ICommonHandler {
	/**
	 * 解析某一个单元注释
	 * @param prototype 单元注释
	 * @param block 单元注释对应的整体块
	 */
	public void execute(Common common,List<Property> block);
	
	/**
	 * @param common
	 * @param property
	 */
	public void execute(Common common, Property property);
}
