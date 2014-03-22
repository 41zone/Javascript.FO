package cc.fozone.javascript.struct;

/**
 * @author jimmy song
 *
 */
public class AnnotateDescription {
	/**
	 * 修饰权限
	 */
	private String purview;
	/**
	 * 是否静态
	 */
	private boolean staticd;
	/**
	 * 是否终结
	 */
	private boolean finald;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 版本
	 */
	private String version;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 时间
	 */
	private String date;
	
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
