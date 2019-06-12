package aura.javaSE.proxy;

public class ProxyWangBaoQqiang implements Actor{
	WangBaoQiang wbq;
	public ProxyWangBaoQqiang(){
		this.wbq=new WangBaoQiang();
	}

	@Override
	public void paixi() {
		System.out.println("拍戏");
		wbq.paixi();
		
	}

	@Override
	public void paiguanggao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paizongyi() {
		// TODO Auto-generated method stub
		
	}

}
