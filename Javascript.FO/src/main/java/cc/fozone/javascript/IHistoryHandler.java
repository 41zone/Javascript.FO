package cc.fozone.javascript;

import java.util.List;

import cc.fozone.javascript.struct.Prototype;
import cc.fozone.javascript.utils.Property;

/**
 * 历史记录解析接口
 * @author jimmy song
 *
 */
public interface IHistoryHandler {
	/**
	 * 为原型构建历史记录
	 * @param prototype 原型
	 * @param block 原型块
	 */
	public void execute(Prototype prototype,List<Property> block);

	/**
	 * 为原型构建某一个历史记录
	 * @param prototype
	 * @param property
	 */
	public void execute(Prototype prototype, Property property);
}
