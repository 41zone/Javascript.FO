package cc.fozone.javascript.session.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cc.fozone.javascript.session.service.AbstractService;
import cc.fozone.javascript.session.service.INamespaceService;
import cc.fozone.javascript.struct.Namespace;

@Service
public class NamespaceServiceImpl extends AbstractService implements INamespaceService {
	@Override
	public int save(Namespace namespace) {
		// TODO Auto-generated method stub
		Namespace ns = this.query(namespace.getName());
		if(ns == null) { 
			this.getNamespaceMap().put(namespace.getName(), namespace);
			return 1;
		} else return -1;
	}

	@Override
	public Namespace queryOrSave(String namespace) {
		// TODO Auto-generated method stub
		Namespace ns = this.query(namespace);
		if(ns == null) {
			ns = new Namespace();
			ns.setName(namespace);
			this.save(ns);
		}
		return ns;
	}
	
	@Override
	public Namespace query(String name) {
		// TODO Auto-generated method stub
		Map<String,Namespace> map = this.getNamespaceMap();
		if(map == null) return null;
		return map.get(name);
	}

}
