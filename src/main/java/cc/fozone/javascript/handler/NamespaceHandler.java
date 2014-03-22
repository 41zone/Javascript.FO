package cc.fozone.javascript.handler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cc.fozone.javascript.IPropertyHandler;
import cc.fozone.javascript.session.service.INamespaceService;
import cc.fozone.javascript.struct.Namespace;
import cc.fozone.javascript.utils.Property;

@Component
public class NamespaceHandler implements IPropertyHandler {
	private static final Logger logger = Logger.getLogger(NamespaceHandler.class);
	@Resource INamespaceService namespaceService;
	@Override
	public void execute(Property property, List<Property> block) {
		// TODO Auto-generated method stub
		Namespace namespace = new Namespace();
		namespace.setName(property.getValue());
		int result = namespaceService.save(namespace);
		if(result == 1) logger.info("namespace "+namespace.getName()+" save success.");
		else {
			if(result == 0) logger.info("namespace "+namespace.getName()+" save failure.");
			else if(result == 1) logger.info("namespace "+namespace.getName()+" has exist.");
			namespace = null;
		}
	}
}
