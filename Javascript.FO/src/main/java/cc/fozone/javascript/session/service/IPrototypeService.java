package cc.fozone.javascript.session.service;

import cc.fozone.javascript.struct.Prototype;

/**
 * 对象服务层
 * @author jimmy song
 *
 */
public interface IPrototypeService {
	/**
	 * 存储已有的对象类型
	 * @param prototype 对象模型
	 * @return 是否成功，1表示成功，-1表示已存在，0表示失败
	 */
	public int save(Prototype prototype);
	
	/**
	 * 根据名称获取原型的名称
	 * @param name 名称
	 * @return 原型对象
	 */
	public Prototype query(String name);
}
