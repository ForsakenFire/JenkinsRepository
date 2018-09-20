package spring4.aopclass;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring4.aopclass.aspect.impl.DinnerImpl;

@Aspect
@Component
public class AspectClass {
	
	@Autowired
	EatInterface dinnerImpl;
	
	@Pointcut("execution(* *.eat())")
	public void eatPointCut1(){}
	
	@Pointcut("execution(String *.eat(String))")
	public void eatPointCut2(){}
	
	/**
	 * 切点：spring4下所有包的类中的eat方法，并且限定beanid
	 */
	@Pointcut("execution(* spring4..*.eat()) and bean(launchImpl)")
	public void eatPointCut3(){
		
	}
	
	/**
	 * 切点：spring4下所有包的类中的eat方法(String为参数)，并且限定beanid
	 */
	@Pointcut("execution(* spring4..*.eat(String))  "
			+ "&& args(name) && bean(launchImpl)")
	public void eatPointCut4(String name){
		
	}
	
	/**
	 * 切点：spring4下所有包的类中的eat方法，并且限定beanid
	 */
	@Pointcut("execution(* spring4..*.eat()) and bean(breakfastImpl)")
	public void eatPointCut5(){
		
	}
	@Before("eatPointCut1()")
	public void beforeEat(){
		System.out.println("无参before切面执行");
	}
	
	@Before("eatPointCut2()")
	public void beforeEat2(){
		System.out.println("有参before切面");
	}	
	
	@Around("eatPointCut3()")
	public void aroundCut(ProceedingJoinPoint pj) throws Throwable{
		System.out.println("环绕切面开始");
		pj.proceed();
		System.out.println("环绕切面结束");
	}
	@After(value="eatPointCut4(name)")
	public void afterCut(String name) throws Throwable{
		System.out.println("After切面结束:name="+name);
	}
	
	@AfterThrowing(throwing="e",value="eatPointCut5()")
	public void doException(Throwable e){
		System.out.println("doException异常：e="+e.getMessage());
	}
	
}
