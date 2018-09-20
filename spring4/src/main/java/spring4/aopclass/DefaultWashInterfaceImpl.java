package spring4.aopclass;



public class DefaultWashInterfaceImpl implements WashInterface{

	@Override
	public void wash() {
		// TODO Auto-generated method stub
		System.out.println("无参wash方法invoke");
	}

	@Override
	public void washAgain(String note) {
		System.out.println("有参构造方法invoke");
	}

}
