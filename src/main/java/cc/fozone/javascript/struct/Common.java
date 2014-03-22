package cc.fozone.javascript.struct;

/**
 * 共用参数
 * @author Jimmy Song
 *
 */
public class Common implements IObject {
	/**
	 * 注释内容
	 */
	private String annotate;
	/**
	 * 版本内容
	 */
	private String version = "0";
	/**
	 * 创建日期
	 */
	private String date;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 自从具体版本开始
	 */
	private String since;
	/**
	 * 是否过期
	 */
	private String deprecated;
	/**
	 * 链接
	 */
	private String see;
	/**
	 * 联系邮箱
	 */
	private String email;
	
	public String getSee() {
		return see;
	}
	public void setSee(String see) {
		this.see = see;
	}
	public String getAnnotate() {
		return annotate;
	}
	public void setAnnotate(String annotate) {
		this.annotate = annotate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSince() {
		return since;
	}
	public void setSince(String since) {
		this.since = since;
	}
	public String getDeprecated() {
		return deprecated;
	}
	public void setDeprecated(String deprecated) {
		this.deprecated = deprecated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
