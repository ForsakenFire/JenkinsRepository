package spring4.aopclass;


public class XmlAopClass {
	public void xmlAopAspect(){
		System.out.println("from xmlAOPClass");
	}
	
	public void xmlAopAspect2(String name){
		System.out.println("xmlAOPClass:name="+name);
	}
	
	public void xmlAopAspectException(Throwable e){
		System.out.println("xmlAOPAspectException2:e="+e.getMessage());
	}
	
}
