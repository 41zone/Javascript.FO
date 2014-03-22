package cc.fozone.javascript.handler.common;

import java.util.List;

import org.springframework.stereotype.Component;

import cc.fozone.javascript.ICommonHandler;
import cc.fozone.javascript.struct.Common;
import cc.fozone.javascript.utils.Property;

@Component
public class DeprecatedHandler implements ICommonHandler {

	@Override
	public void execute(Common common, List<Property> block) {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public void execute(Common common, Property property) {
		// TODO Auto-generated method stub
		if(common == null || property == null) return ;
		common.setDeprecated(property.getValue());
	}

}
