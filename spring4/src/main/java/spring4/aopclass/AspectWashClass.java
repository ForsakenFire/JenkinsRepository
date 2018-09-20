package spring4.aopclass;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 可以引入接口的切面类
 * @author TD
 *
 */
@Aspect
@Component
public class AspectWashClass {
	
	@DeclareParents(value="spring4.aopclass.EatInterface+",defaultImpl=DefaultWashInterfaceImpl.class)
	public static WashInterface washInterface;
	
}
