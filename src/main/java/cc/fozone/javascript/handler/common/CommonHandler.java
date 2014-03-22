package cc.fozone.javascript.handler.common;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cc.fozone.javascript.ICommonHandler;
import cc.fozone.javascript.session.service.IPrototypeService;
import cc.fozone.javascript.struct.Common;
import cc.fozone.javascript.utils.Property;

/**
 * 通用解释控制器
 * @author jimmy song
 *
 */
@Component
public class CommonHandler implements ICommonHandler  {
	private static final Logger logger = Logger.getLogger(CommonHandler.class);
	@Resource IPrototypeService prototypeService;
	@Resource CommonRegister commonRegister;
	@Override
	public void execute(Common common, List<Property> block) {
		// TODO Auto-generated method stub
		if(common == null || block == null || block.isEmpty()) return ;
		Iterator<Property> it = block.iterator();
		while(it.hasNext()){
			Property property = it.next();
			ICommonHandler handler = commonRegister.getHandler(property.getKey());
			if(handler == null) {
				//logger.warn("common property "+property.getKey()+" handler missed.");
				continue;
			}
			logger.info("common property "+property.getKey()+" handler start to work.");
			handler.execute(common,property);
		}
	}
	
	@Override
	public void execute(Common common, Property property) {
		// TODO Auto-generated method stub
		ICommonHandler handler = commonRegister.getHandler(property.getKey());
		if(handler == null) {
			logger.warn("common property "+property.getKey()+" handler missed.");
			return ;
		}
		logger.info("common property "+property.getKey()+" handler start to work.");
		handler.execute(common,property);
	}
}
