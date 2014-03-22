package cc.fozone.javascript.handler.parameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import cc.fozone.javascript.ParameterHandlerAdpter;
import cc.fozone.javascript.struct.AnnotateDescription;
import cc.fozone.javascript.struct.At;
import cc.fozone.javascript.struct.Parameter;
import cc.fozone.javascript.struct.Prototype;
import cc.fozone.javascript.struct.Type;
import cc.fozone.javascript.utils.Property;
import cc.fozone.javascript.utils.RegexUtils;

/**
 * 类体参数解析器
 * @author jimmy song
 *
 */
@Component
public class PrototypeParameterHandler extends ParameterHandlerAdpter {
	@Override
	public void execute(Prototype prototype, List<Property> block) {
		// TODO Auto-generated method stub
		if(prototype == null || block == null) return ;
		List<Parameter> parameters = prototype.getParameters();
		if(parameters != null) return ;
		parameters = new ArrayList<Parameter>();
		prototype.setParameters(parameters);
		
		Iterator<Property> it = block.iterator();
		while(it.hasNext()) {
			Property p = it.next();
			if(At.PARAM.equals(p.getKey())) {
				this.execute(prototype, p);
			}
		}
	}
	@Override
	public void execute(Prototype prototype, Property property) {
		// TODO Auto-generated method stub
		if(prototype == null || property == null) return ;
		List<Parameter> parameters = prototype.getParameters();
		if(parameters == null) {
			parameters = new ArrayList<Parameter>();
			prototype.setParameters(parameters);
		}
		AnnotateDescription ad = RegexUtils.execParameter(property.getValue());
		if(ad == null) return ;
		Parameter param = new Parameter();
		param.setAnnotate(ad.getDescription());
		param.setName(ad.getName());
		Type type = new Type();
		type.setName(ad.getType());
		param.setType(type);
		parameters.add(param);
		
		ad = null;
	}
}
