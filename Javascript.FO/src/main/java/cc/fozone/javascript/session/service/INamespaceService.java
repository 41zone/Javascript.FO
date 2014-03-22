package cc.fozone.javascript.session.service;

import cc.fozone.javascript.struct.Namespace;

/**
 * 命名空间服务层
 * @author jimmy song
 *
 */
public interface INamespaceService {
	
	/**
	 * 保存命名空间
	 * @param namespace 命名空间对象
	 * @return 是否成功，1成功，-1表示已存在，0表示失败
	 */
	public int save(Namespace namespace);
	
	/**
	 * 获取或者保存
	 * @param namespace
	 * @return
	 */
	public Namespace queryOrSave(String namespace);
	
	/**
	 * 根据名称查询命名空间
	 * @param name 名称
	 * @return 命名空间
	 */
	public Namespace query(String name);
}
