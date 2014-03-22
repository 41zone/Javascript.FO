package cc.fozone.javascript.session.service;

import cc.fozone.javascript.struct.Method;

/**
 * 方法服务层
 * @author jimmy song
 *
 */
public interface IMethodService {
	/**
	 * 保存方法
	 * @param method 方法
	 * @return 是否成功，1表示成功，-1表示已存在，0表示失败
	 */
	public int save(Method method);
}
