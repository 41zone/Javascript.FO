package cc.fozone.javascript.struct;

/**
 * 参数类
 * @author Jimmy Song
 *
 */
public class Parameter implements IObject {
	/**
	 * 参数类型
	 */
	private Type type;
	/**
	 * 参数名称
	 */
	private String name;
	
	/**
	 * 注释信息
	 */
	private String annotate;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnnotate() {
		return annotate;
	}

	public void setAnnotate(String annotate) {
		this.annotate = annotate;
	}
	
	
}
