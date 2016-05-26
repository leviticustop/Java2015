package Test201605222138;

class DemoStudent {

	private String bander;  // 品牌
	private int price;  // 价格
	
	public void call() {
		System.out.println("打电话");  // 打电话
	}
	
	public void sendMessage() {
		System.out.println("发消息");  // 发消息
	}
	
	public void playGame() {
		System.out.println("玩游戏");  // 玩游戏
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
	
}

public class Phone {

	public static void main ( String[] args ) {
		System.out.println("hello");
		DemoStudent s = new DemoStudent();
		s.setBander("苹果");
		System.out.println(s.getBander());
		
		Test201605231206 t = new Test201605231206();
		System.out.println(t.add(1, 2));
		
	}
	
	
}
