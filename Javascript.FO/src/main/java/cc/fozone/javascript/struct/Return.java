package cc.fozone.javascript.struct;

/**
 * 返回值类型
 * @author Jimmy Song
 *
 */
public class Return implements IObject {
	/**
	 * 类型
	 */
	private Type type;
	/**
	 * 注释
	 */
	private String annotate;
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getAnnotate() {
		return annotate;
	}
	public void setAnnotate(String annotate) {
		this.annotate = annotate;
	}
	
}
