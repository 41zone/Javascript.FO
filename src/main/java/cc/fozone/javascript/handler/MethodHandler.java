package cc.fozone.javascript.handler;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cc.fozone.javascript.IPropertyHandler;
import cc.fozone.javascript.utils.Property;

/**
 * 对象类型解析控制器
 * @author jimmy song
 *
 */
@Component
public class MethodHandler implements IPropertyHandler {
	private static final Logger logger = Logger.getLogger(MethodHandler.class);
	@Override
	public void execute(Property property, List<Property> block) {
		// TODO Auto-generated method stub
		
	}
}
