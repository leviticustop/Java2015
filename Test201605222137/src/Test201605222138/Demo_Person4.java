package Test201605222138;

public class Demo_Person4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Per p1 = new Per("zhangshan",23) ;
		p1.show();
	}

}

class Per {

	private String name ;
	private int age ;
	
	public Per () {
		
	}
	
	public Per (String name ,int age) {
		this.name = name ;
		this.age = age ;
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
	
	public void show() {
		System.out.print(name + age);
	}
	
}