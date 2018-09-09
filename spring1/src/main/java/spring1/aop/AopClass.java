package spring1.aop;
/**
 * 通过xml配置的方式使用的aop类
 * @author TD
 *
 */
public class AopClass {
	
	/**
	 * 切面前
	 */
	public void beforeCook(){
		System.out.println("切面前：beforeCook");
	}
	
	/**
	 * 切面后
	 */
	public void afterCook(){
		System.out.println("切面后：afterCook");
	}
}
