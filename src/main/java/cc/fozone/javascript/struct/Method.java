package cc.fozone.javascript.struct;

import java.util.List;

/**
 * 原型对象中的方法类
 * @author Jimmy Song
 *
 */
public class Method implements IObject {
	/**
	 * 共有属性
	 */
	private Common common;
	/**
	 * 修饰权限
	 */
	private String purview;
	/**
	 * 是否静态 
	 */
	private boolean staticd;
	/**
	 * 是否最终
	 */
	private boolean finald;
	/**
	 * 参数列表
	 */
	private List<Parameter> parameters;
	
	/**
	 * 所属对象
	 */
	private Prototype prototype;
	
	/**
	 * 返回类型
	 */
	private Return returnType;
	
	public Return getReturnType() {
		return returnType;
	}
	public void setReturnType(Return returnType) {
		this.returnType = returnType;
	}
	public Prototype getPrototype() {
		return prototype;
	}
	public void setPrototype(Prototype prototype) {
		this.prototype = prototype;
	}
	public Common getCommon() {
		return common;
	}
	public void setCommon(Common common) {
		this.common = common;
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
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
}
