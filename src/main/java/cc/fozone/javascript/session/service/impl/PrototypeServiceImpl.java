package cc.fozone.javascript.session.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.fozone.javascript.session.service.AbstractService;
import cc.fozone.javascript.session.service.INamespaceService;
import cc.fozone.javascript.session.service.IPrototypeService;
import cc.fozone.javascript.struct.Common;
import cc.fozone.javascript.struct.Prototype;
import cc.fozone.javascript.utils.APIUtils;
import cc.fozone.javascript.utils.Property;

/**
 * @author jimmy song
 *
 */
@Service
public class PrototypeServiceImpl extends AbstractService implements IPrototypeService {
	@Resource INamespaceService namespaceService;
	@Override
	public int save(Prototype prototype) {
		// TODO Auto-generated method stub
		if(prototype == null) return 0;
		Property nn = APIUtils.execPrototypeName(prototype.getName());
		if(nn == null) return 0;
		int result = 0;
		prototype.setSimpleName(nn.getValue());
		prototype.setNamespace(nn.getKey());
		namespaceService.queryOrSave(prototype.getNamespace());
		Map<String,Map<String,Prototype>> prototypies = this.getPrototypeMap(prototype.getNamespace());
		if(prototypies == null) {
			result = 0;
		} else {
			Map<String,Prototype> pset = prototypies.get(prototype.getName());
			if(pset == null) {
				pset = new HashMap<String,Prototype>();
				prototypies.put(prototype.getName(), pset);
			}
			Common common = prototype.getCommon();
			if(common == null) {
				common = new Common();
			}
			String version = common.getVersion();
			if(pset.containsKey(version)) {
				//如果已经存在了版本，那么就应该对他进行更新，但是需要确定的是，该更新什么？，而又不改更新什么？
				result = -1;
			} else {
				pset.put(version,prototype);
				result = 1;
			}
		}
		nn = null;
		return result;
	}

	@Override
	public Prototype query(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
