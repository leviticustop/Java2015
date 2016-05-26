package Test201605222138;

public class Demo2_Person {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person() ;
		Person p2 = new Person("张三",23) ;
		p1.eat();
		System.out.println("----------------------------");
		p2.eat();

	}

}

class Person {
	
	private String name ;  // 姓名
	private int age ;  // 年龄
	
	public Person () {
		System.out.println("空参的构造");
	}
	
	public Person ( String name , int age ) {
		this.setName(name) ;
		this.setAge(age) ;
	}
	
	public void eat () {
		System.out.println("吃饭");
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
	
	public void show () {
		System.out.println(getName() + "..." + getAge());
	}
}
