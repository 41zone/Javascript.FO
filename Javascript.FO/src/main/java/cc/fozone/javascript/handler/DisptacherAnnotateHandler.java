package cc.fozone.javascript.handler;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cc.fozone.javascript.IAnnotateHandler;
import cc.fozone.javascript.IPropertyHandler;
import cc.fozone.javascript.struct.At;
import cc.fozone.javascript.utils.Property;
import cc.fozone.javascript.utils.RegexUtils;

/**
 * 文件注释解析转发控制器
 * @author jimmy song 
 */
@Component
public class DisptacherAnnotateHandler implements IAnnotateHandler {
	private static final Logger logger = Logger.getLogger(DisptacherAnnotateHandler.class);
	@Resource HandlerRegister handlerRegister;
	private Comparator<Property> atComparator;
	public DisptacherAnnotateHandler() {
		// TODO Auto-generated constructor stub
		atComparator = new AtPropertyComparator();
	}
	@Override
	public void execute(String text) {
		// TODO Auto-generated method stub
		List<String> sections = RegexUtils.execAnnotate(text);
		Iterator<String> it = sections.iterator();
		while(it.hasNext()){
			String section = it.next();
			this.untie(section);
		}
		sections.clear();
		sections = null;
	}
	
	/**
	 * 分解片段
	 * @param section 片段字符串
	 */
	public void untie(String section) {
		//解析注释内容
		String text = RegexUtils.execText(section);
		//解析注释中参数列表
		List<Property> ats = RegexUtils.execAt(section);
		if(ats  == null || ats.isEmpty()) return ;
		//构造比较器
		Collections.sort(ats, atComparator);
		Property tp = new Property();
		tp.setKey(At.TEXT);
		tp.setValue(text);
		ats.add(tp);
		this.dispatch(ats);
	}
	
	/**
	 * 解析片段，执行跳转
	 * @param properties 片段数组
	 */
	public void dispatch(List<Property> properties){
		if(properties == null || properties.isEmpty()) return ;
		Iterator<Property> it = properties.iterator();
		while(it.hasNext()){
			Property property = it.next();
			IPropertyHandler handler = handlerRegister.getHandler(property.getKey());
			if(handler == null) {
				//logger.warn("property "+property.getKey()+" handler missed.");
				continue;
			}
			logger.info("property "+property.getKey()+" handler start to work.");
			handler.execute(property,properties);
		}
 	}
}
