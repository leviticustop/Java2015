package Test201605222138;

import java.util.Scanner;

public class Test201605231431 {
	
	private static Scanner sc;

	public static void main (String[] args) {
		
		sc = new Scanner(System.in);
		System.out.println("请输入第一个行数：");
		int x = sc.nextInt();
		System.out.println("请输入第二个列数：");
		int y = sc.nextInt();
		
		int sum = add(x,y);
		System.out.println("Sum = " + sum);
		boolean equal = equal(x,y);
		System.out.println("两个整数是否相等：" + equal);
		int z = bigger(x,y);
		System.out.println("两个整数中比较大的数：" + z);
		System.out.println("图形如下：");
		map( x, y );
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
	
	public static void map ( int x ,int y ) {
		for ( int i = 0 ; i < x ; i++ ) {	
			for ( int j = 0 ; j < y ; j++ ) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
