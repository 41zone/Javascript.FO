package cc.fozone.javascript.handler.history;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import cc.fozone.javascript.HistoryHandlerAdpter;
import cc.fozone.javascript.struct.AnnotateDescription;
import cc.fozone.javascript.struct.At;
import cc.fozone.javascript.struct.History;
import cc.fozone.javascript.struct.Prototype;
import cc.fozone.javascript.utils.Property;
import cc.fozone.javascript.utils.RegexUtils;

/**
 * @author jimmy song
 *
 */
@Component
public class PrototypeHistoryHandler extends HistoryHandlerAdpter {
	@Override
	public void execute(Prototype prototype, List<Property> block) {
		// TODO Auto-generated method stub
		if(prototype == null || block == null) return ;
		List<History> histories = prototype.getHistories();
		if(histories != null) return ;
		histories = new ArrayList<History>();
		prototype.setHistories(histories);
		
		Iterator<Property> it = block.iterator();
		while(it.hasNext()){
			Property property = it.next();
			if(At.HISTORY.equalsIgnoreCase(property.getKey())) {
				this.execute(prototype, property);
			}
		}
	}
	@Override
	public void execute(Prototype prototype,Property property){
		if(prototype == null || property == null) return ;
		List<History> histories = prototype.getHistories();
		if(histories == null) return ;
		
		AnnotateDescription ad = RegexUtils.execHistory(property.getValue());
		if(ad == null) return ;
		History history = new History();
		history.setAnnotate(ad.getDescription());
		history.setAuthor(ad.getAuthor());
		history.setDate(ad.getDate());
		history.setVersion(ad.getVersion());
		histories.add(history);
		ad = null;
	}
}
