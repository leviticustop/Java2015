package Test201605222138;

public class Demo_Phone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pho p1 = new Pho() ;
		p1.setBander("三星");
		p1.setPrice(5889);
		p1.show();
		
	}
	

}

class Pho {
	private String bander ;
	private int price ;
	
	public Pho () {
		
	}
	
	public Pho ( String bander , int price ) {
		this.bander = bander ;
		this.price = price ;
	}
	
	public void call() {
		
	}
	
	public void sendMessage () {
		
	}
	
	public void playGame () {
		
	}
	
	public String getBander() {
		return bander;
	}
	public void setBander(String bander) {
		this.bander = bander;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void show () {
		System.out.println(bander + price);
	}

}
