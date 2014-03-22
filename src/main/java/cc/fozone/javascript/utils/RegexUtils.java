package cc.fozone.javascript.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cc.fozone.javascript.struct.AnnotateDescription;
import cc.fozone.javascript.struct.Purview;

/**
 * 正则工具包
 * @author jimmy song
 *
 */
public abstract class RegexUtils {
	/**
	 * 注释正则表达
	 */
	public static final String REGEX_ANNOTATE = "(?sm)/\\*\\*+((.*?)\\*/)?";
	
	/**
	 * AT规则正则表达
	 */
	public static final String REGEX_AT = "(?sm)\\*\\s*((@\\w+)(.*?)\n)";
	
	/**
	 * 注释内容正则表达
	 */
	public static final String REGEX_TEXT = "(?sm)\\s\\*+\\s*(.*?)(?=\n)";
	
	/**
	 * 对象类型中的类型注释
	 */
	public static final String REGEX_PROTOTYPE_TYPE = "(?sm)(\\{\\s*(public|private)?\\s*(static)?\\s*\\})?\\s*(.*)";
	
	/**
	 * 参数类型注释
	 */
	public static final String REGEX_PARAMETER = "(?sm)(\\{\\s*(.*?)\\s*\\})?\\s+(.*?)($|\\s+)(.*)";
	
	/**
	 * 历史记录类型注释
	 */
	public static final String REGEX_HISTORY = "(?sm)(\\{\\s*(.*?)(\\s+(.*?)?(\\s+(.*?)?)?)\\s*\\})($|\\s+)(.*)";
	
	/**
	 * 执行注释解析
	 * @param text 注释文本
	 * @return 返回分段数据
	 */
	public static List<String> execAnnotate(String text) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(REGEX_ANNOTATE);
		Matcher match = pattern.matcher(text);
		if(match == null) return list;
		while(match.find()) {
			list.add(match.group());
		}
		match = null;
		pattern = null;
		return list;
	}
	
	/**
	 * 执行解析参数单元列表
	 * @param text 注释片段
	 * @return 返回参数列表数据
	 */
	public static List<Property> execAt(String text){
		List<Property> list = new ArrayList<Property>();
		Pattern pattern = Pattern.compile(REGEX_AT);
		Matcher match = pattern.matcher(text);
		if(match == null) return list;
		while(match.find()){
			Property p = new Property();
			p.setKey(match.group(2).trim());
			p.setValue(match.group(3).trim());
			list.add(p);
		}
		match = null;
		pattern = null;
		return list;
	}
	
	/**
	 * @param text
	 * @return
	 */
	public static String execText(String text){
		text = text.replaceAll(REGEX_AT, "");
		Pattern pattern = Pattern.compile(REGEX_TEXT);
		Matcher match = pattern.matcher(text);
		if(match == null) return "";
		StringBuffer buf = new StringBuffer();
		while(match.find()){
			buf.append(match.group(1));
		}
		return buf.toString().trim();
	}
	
	/**
	 * 解析对象类型数据类型
	 * @param text 原型对象
	 * @return 数据类型包含的类型
	 */
	public static AnnotateDescription execPrototype(String text){
		Pattern pattern = Pattern.compile(REGEX_PROTOTYPE_TYPE);
		Matcher match = pattern.matcher(text);
		if(match == null) return null;
		AnnotateDescription ad = new AnnotateDescription();
		if(match.find()){
			String p = match.group(2);
			ad.setPurview(p==null?Purview.PUBLIC:p);
			String s = match.group(3);
			ad.setStaticd(s == null?false:true);
			ad.setDescription(match.group(4));
		}
		return ad;
	}
	
	/**
	 * 解析参数类型对象
	 * @param text 文本
	 * @return 数据类型
	 */
	public static AnnotateDescription execParameter(String text){
		Pattern pattern = Pattern.compile(REGEX_PARAMETER);
		Matcher match = pattern.matcher(text);
		if(match == null) return null;
		AnnotateDescription ad = new AnnotateDescription();
		if(match.find()){
			ad.setType(match.group(2));
			ad.setName(match.group(3));
			ad.setDescription(match.group(5));
		}
		return ad;
	}
	
	/**
	 * 解析历史记录
	 * @param text 文本
	 * @return 数据
	 */
	public static AnnotateDescription execHistory(String text){
		Pattern pattern = Pattern.compile(REGEX_HISTORY);
		Matcher match = pattern.matcher(text);
		if(match == null) return null;
		AnnotateDescription ad = new AnnotateDescription();
		if(match.find()){
//			int count = match.groupCount();
//			System.out.println("+++++++++");
//			for(int i = 1;i<=count;i++){
//				System.out.println(match.group(i));
//			}
			ad.setVersion(match.group(2));
			ad.setDate(match.group(4));
			ad.setAuthor(match.group(6));
			ad.setDescription(match.group(8));
		}
		return ad;
	}
}
