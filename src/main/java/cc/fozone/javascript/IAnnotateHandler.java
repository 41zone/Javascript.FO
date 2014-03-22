package cc.fozone.javascript;

/**
 * 注释接口处理器
 * @author jimmy song
 *
 */
public interface IAnnotateHandler {
	/**
	 * 按照注释字符串进行处理不同的注释
	 */
	public void execute(String text);
}
