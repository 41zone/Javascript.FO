package cc.fozone.javascript.struct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原型模式对象
 * @author Jimmy Song
 *
 */
public class Prototype implements IObject {
	/**
	 * 命名空间
	 */
	private String namespace;
	/**
	 * 简单名称
	 */
	private String simpleName;
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 修饰权限
	 */
	private String purview;
	
	/**
	 * 是否静态的
	 */
	private boolean staticd;
	
	/**
	 * 是否是终止的
	 */
	private boolean finald;
	
	/**
	 * 属性mapper，name -> (version -> Attribute)
	 */
	private Map<String,Map<String,Attribute>> attributes;
	
	/**
	 * 方法mapper , name -> (version -> Method)
	 */
	private Map<String,Map<String,Method>> methods;
	
	/**
	 * 参数列表
	 */
	private List<Parameter> parameters;

	/**
	 * 通用属性
	 */
	private Common common;
	
	/**
	 * 版本历史
	 */
	private List<History> histories;

	public Prototype() {
		// TODO Auto-generated constructor stub
		this.attributes = new HashMap<String,Map<String,Attribute>>();
		this.methods = new HashMap<String,Map<String,Method>>();
	}
	
	public String getPurview() {
		return purview;
	}

	public void setPurview(String purview) {
		this.purview = purview;
	}

	public boolean isStaticd() {
		return staticd;
	}

	public void setStaticd(boolean staticd) {
		this.staticd = staticd;
	}

	public boolean isFinald() {
		return finald;
	}

	public void setFinald(boolean finald) {
		this.finald = finald;
	}

	public Map<String, Map<String, Attribute>> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Map<String, Attribute>> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Map<String, Method>> getMethods() {
		return methods;
	}

	public void setMethods(Map<String, Map<String, Method>> methods) {
		this.methods = methods;
	}

	public Common getCommon() {
		return common;
	}

	public void setCommon(Common common) {
		this.common = common;
	}


	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
}
