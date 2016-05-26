package Test201605222138;

public class Demo_Animal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat c1 = new Cat("花",4,"Mary",5);
		System.out.println(c1.getColor() + "..." + c1.getLeg());
		c1.eat();
		c1.catchMouse();
		
		Dog d1 = new Dog("黑",2,"Peter",10);
		System.out.println(d1.getColor() + "..." + d1.getLeg());
		d1.eat();
		d1.lookHome();
	}

}

abstract class Animal {
	
	private String color ;
	private int leg ;
	private String name ;
	private int age ;
	
	public Animal () {}
	
	public Animal ( String color , int leg , String name , int age ) {
		this.color = color ;
		this.leg = leg ;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getLeg() {
		return leg;
	}
	public void setLeg(int leg) {
		this.leg = leg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public abstract void eat () ;
	
}

class Cat extends Animal {
	
	public Cat () {}
	
	public Cat ( String color ,int leg ,String name ,int age ) {
		super (color,leg,name,age) ;
	}
	
	public void eat () {
		System.out.println("猫吃鱼");
	}
	
	public void catchMouse () {
		System.out.println("捉老鼠");
	}
}

class Dog extends Animal {
	
	public Dog () {}
	
	public Dog ( String color , int leg , String name , int age ) {
		super (color,leg,name,age) ;
	}
	
	public void eat () {
		System.out.println("狗吃肉");
	}
	
	public void lookHome () {
		System.out.println("看门");
	}
}