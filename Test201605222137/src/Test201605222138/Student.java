package Test201605222138;

public class Student {

	public static void main ( String[] args ) {
		StudentT s = new StudentT();
		s.age = 23 ;
		System.out.println(s.age);
	
	}
}

class StudentT {
	
	String name ;
	int age ;
	String gender;
	
	public void study() {
		System.out.println("学习");
	}
	
	public void sleep() {
		System.out.println("睡觉");
	}
}
