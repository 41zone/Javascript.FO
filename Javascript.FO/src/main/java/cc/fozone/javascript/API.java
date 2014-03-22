package cc.fozone.javascript;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class API {
	private static final Logger logger = Logger.getLogger(API.class);
	private static final String CONTEXT_PATH = "context.xml";
	private static ApplicationContext context;
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		logger.info("load spring context");
		context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
		if(context == null) {
			logger.error("spring context load fail.");
			return ;
		}
		logger.info("spring context load success.");
		IJavascriptManager manager = (IJavascriptManager) context.getBean("javascriptManager");
		if(manager == null) {
			logger.error("can not initilization manager.");
			return ;
		}
		logger.info("ready to resolve the application.");
		manager.execute();
		long end = System.currentTimeMillis();
		logger.info("use time "+(end-start)+" milliseconds");
	}
}
