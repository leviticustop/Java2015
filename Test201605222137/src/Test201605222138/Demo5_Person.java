package Test201605222138;

public class Demo5_Person {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student1 s1 = new Student1();
		s1.eat();
		s1.setName("张三");
		s1.setAge(23);
		s1.show();
		s1.study();
		
		System.out.println("----------------------------");
		Student1 s2 = new Student1("李四",24);
		s2.eat();
		s2.show();
	}

}

class Student1 extends Person {
	
	public Student1 () {}
	
	public Student1 ( String name , int age ) {
		super (name,age);
	}
	
	public void study () {
		System.out.println(this.getName() + "学习");
	}
}

class Teacher extends Person {
	
	public Teacher () {}
	
	public Teacher ( String name , int age ) {
		super (name,age);
	}
	
	public void Teach () {
		System.out.println(this.getName() + "讲课");
	}
}