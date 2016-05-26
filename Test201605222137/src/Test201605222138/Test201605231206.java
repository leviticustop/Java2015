package Test201605222138;
import java.util.*;

public class Test201605231206 {

	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入第一个整数：");
		int x = sc.nextInt();
		System.out.println("请输入第二个整数：");
		int y = sc.nextInt();
		int sum = add(x,y);
		System.out.println("Sum = " + sum);
		boolean equal = equal(x,y);
		System.out.println("两个整数是否相等：" + equal);
		int z = bigger(x,y);
		System.out.println("两个整数中比较大的数：" + z);
	}
	
	public static int add ( int x , int y ) {
		
		int sum = x + y ;
		
		return sum ;
	}
	
	public static boolean equal( int x , int y ) {
		if ( x == y ) {
			boolean equal = true;
			return equal;
		} else {
			boolean equal = false;
			return equal;
		}
	}
	
	public static int bigger ( int x , int y ) {
		if ( x > y ) {
			return x;
		} else {
			return y;
		}
	}
	
}
