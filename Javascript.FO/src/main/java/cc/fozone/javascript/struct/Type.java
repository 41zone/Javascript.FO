package cc.fozone.javascript.struct;

/**
 * 类型对象
 * @author Jimmy Song
 *
 */
public class Type implements IObject {
	/**
	 * 整型
	 */
	public static final String INTEGER = "Integer";
	/**
	 * 整型
	 */
	public static final String INT = "int";
	/**
	 * 数值类型，包括整型、浮点
	 */
	public static final String NUMBER = "Number";
	/**
	 * 布尔类型
	 */
	public static final String BOOLEAN ="Boolean";
	/**
	 * 布尔类型
	 */
	public static final String BOOL = "bool";
	/**
	 * 浮点类型
	 */
	public static final String DOUBLE="Double";
	/**
	 * 对象类型
	 */
	public static final String OBJECT="Object";
	/**
	 * 字符串类型
	 */
	public static final String STRING ="String";
	/**
	 * 数组类型
	 */
	public static final String ARRAY ="Array";
	
	/**
	 * 方法类型
	 */
	public static final String FUNCTION = "Function";
	
	/**
	 * JSON数据类型
	 */
	public static final String JSON = "JSON";
	
	/**
	 * 参数类型
	 */
	public static final String OPTIONS = "Options";
	
	/**
	 * 空类型
	 */
	public static final String VOID = "Void";
	
	/**
	 * 类型集合
	 */
	private static final String[] TYPES = new String[]{
		INTEGER,INT,ARRAY,BOOL,BOOLEAN,DOUBLE,FUNCTION,JSON,NUMBER,OPTIONS,STRING,OBJECT
	};
	
	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 基本数据类型
	 */
	private boolean basic = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null) {
			this.name = VOID;
			return ;
		}
		String n = name;
		boolean b = false;
		for(int i = 0;i<TYPES.length;i++){
			if(TYPES[i].equalsIgnoreCase(n.trim())) {
				b = true;
				n = TYPES[i];
				break;
			}
		}
		this.basic = b;
		this.name = n;
	}

	public boolean getBasic() {
		return basic;
	}

	public void setBasic(boolean basic) {
		this.basic = basic;
	}
	
	
}
