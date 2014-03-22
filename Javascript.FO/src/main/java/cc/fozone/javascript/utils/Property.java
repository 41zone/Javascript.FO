package cc.fozone.javascript.utils;

/**
 * 属性对象
 * @author jimmy song
 *
 */
public class Property {
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ "+key+" , "+value+" ]";
	}
}
