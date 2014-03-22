package cc.fozone.javascript.struct;

/**
 * 属性类
 * @author Jimmy Song
 *
 */
public class Attribute implements IObject {
	/**
	 * 共有属性
	 */
	private Common common;
	/**
	 * 当前权限
	 */
	private String purview;
	/**
	 * 是否是static的
	 */
	private boolean staticd;
	/**
	 * 是否是final的
	 */
	private boolean finald;
	
	/**
	 * 所属对象 
	 */
	private Prototype prototype;
	
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
	public boolean getStaticd() {
		return staticd;
	}
	public void setStaticd(boolean staticd) {
		this.staticd = staticd;
	}
	public boolean getFinald() {
		return finald;
	}
	public void setFinald(boolean finald) {
		this.finald = finald;
	}
	
}
