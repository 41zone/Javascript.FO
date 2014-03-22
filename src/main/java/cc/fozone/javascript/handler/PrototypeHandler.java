package cc.fozone.javascript.handler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cc.fozone.javascript.IPropertyHandler;
import cc.fozone.javascript.handler.common.CommonHandler;
import cc.fozone.javascript.handler.history.PrototypeHistoryHandler;
import cc.fozone.javascript.handler.parameter.PrototypeParameterHandler;
import cc.fozone.javascript.session.service.IPrototypeService;
import cc.fozone.javascript.struct.AnnotateDescription;
import cc.fozone.javascript.struct.Common;
import cc.fozone.javascript.struct.Prototype;
import cc.fozone.javascript.utils.Property;
import cc.fozone.javascript.utils.RegexUtils;

/**
 * 对象类型解析控制器
 * @author jimmy song
 *
 */
@Component
public class PrototypeHandler implements IPropertyHandler {
	private static final Logger logger = Logger.getLogger(PrototypeHandler.class);
	@Resource IPrototypeService prototypeService;
	@Resource CommonHandler commonHandler;
	@Resource PrototypeParameterHandler prototypeParameterHandler;
	@Resource PrototypeHistoryHandler prototypeHistoryHandler;
	
	@Override
	public void execute(Property property, List<Property> block) {
		// TODO Auto-generated method stub
		String value = property.getValue();
		//解析原子对象中的自定义属性
		AnnotateDescription ad = RegexUtils.execPrototype(value);
		if(ad == null) return ;
		Prototype prototype = new Prototype();
		prototype.setName(ad.getDescription());
		prototype.setPurview(ad.getPurview());
		prototype.setStaticd(ad.isStaticd());
		
		//解析通用属性
		Common common = new Common();
		prototype.setCommon(common);
		commonHandler.execute(common,block);
		
		//解析参数
		prototypeParameterHandler.execute(prototype, block);
		
		//解析历史记录
		prototypeHistoryHandler.execute(prototype, block);
		
		int result = prototypeService.save(prototype);
		if(result == 1) logger.info("prototype "+prototype.getName()+" save success.");
		else {
			if(result == 0) logger.info("prototype "+prototype.getName()+" save failure.");
			else if(result == 1) logger.info("prototype "+prototype.getName()+" has exist.");
			prototype = null;
			return ;
		}
		
	}
}
