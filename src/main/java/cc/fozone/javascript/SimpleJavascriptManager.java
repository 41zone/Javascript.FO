package cc.fozone.javascript;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;

import cc.fozone.javascript.handler.DisptacherAnnotateHandler;
import cc.fozone.javascript.session.SessionManager;
import cc.fozone.javascript.utils.IOUtils;

/**
 * 简单的Javascript管理器
 * @author jimmy song
 *
 */
public class SimpleJavascriptManager implements IJavascriptManager {
	private static final Logger logger = Logger.getLogger(SimpleJavascriptManager.class);
	@Resource SessionManager sessionManager;
	@Resource DisptacherAnnotateHandler disptacherAnnotateHandler;
	
	public SimpleJavascriptManager() {
		// TODO Auto-generated constructor stub
		logger.info("Welcome , SimpleJavascriptManager ready to initilization.");
	}
	
	@Override
	public void execute() {
		logger.info("start to resolve the application.");
		// TODO Auto-generated method stub
		try {
			String text = IOUtils.readAsText("input.js");
			disptacherAnnotateHandler.execute(text);
			logger.info("end to resolve the application.");
			String json = JSONSerializer.toJSON(sessionManager.getNamespaceMap()).toString();
			FileOutputStream output = new FileOutputStream("output.json");
			output.write(json.getBytes());
			output.close();
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("can not resolve the application , "+e.getMessage());
		}
	}
}
