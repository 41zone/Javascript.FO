package cc.fozone.javascript.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * 命名空间类
 * @author Jimmy Song
 *
 */
public class Namespace implements IObject {
	public Namespace() {
		// TODO Auto-generated constructor stub
		this.prototypes = new HashMap<String,Map<String,Prototype>>();
	}
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 所拥有的原型映射 , name -> (version -> Prototype)
	 */
	private Map<String,Map<String,Prototype>> prototypes;
	/**
	 * 注释
	 */
	private String annotate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Map<String, Prototype>> getPrototypes() {
		return prototypes;
	}
	public void setPrototypes(Map<String, Map<String, Prototype>> prototypes) {
		this.prototypes = prototypes;
	}
	public String getAnnotate() {
		return annotate;
	}
	public void setAnnotate(String annotate) {
		this.annotate = annotate;
	}
	
}
