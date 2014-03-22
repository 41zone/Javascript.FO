package cc.fozone.javascript.struct;

/**
 * 历史对象
 * @author Jimmy Song
 *
 */
public class History implements IObject {
	/**
	 * 注释 
	 */
	private String annotate;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 更新日期
	 */
	private String date;
	/**
	 * 修改人员
	 */
	private String author;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	
}
