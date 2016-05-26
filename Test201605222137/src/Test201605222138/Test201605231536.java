package Test201605222138;

import java.util.*;

public class Test201605231536 {

	private static Scanner sc;

	public static void main (String[] args) {
		
		//shuixianhuashu();	
		
		sc = new Scanner(System.in);
 
		int	x = getarraylength();

		/*int[] arr = getWord(x);
		//System.out.println(arr[0]);
		
		read(arr);
		
		compare(arr);
		
		reverseArray(arr);
		
		print(arr);*/
		
		String[] a = getWeek(x);
		
		print(a);
	}
	
	public static String[] getWeek ( int a ) {
		String[] arr = new String[a];
		
		for ( int i = 0 ; i < a ; i++ ) {
			System.out.println("开始录入第" + i + "个数据（字符）：");
			arr[i] = sc.nextLine();
		}
		
		return arr;
	}
	
	public static void print(String[] a) { 
		for ( int i = 0 ; i < a.length ; i++ ) {
			System.out.println("所需的日期为：" + a[i]);
		}
	}
	
	public static void print(char[] a) { 
		
		for ( int i = 0 ; i < a.length ; i++ ) {
			System.out.println("反转后的第"+ i + "个元素：" + a[i]);
		}
	}
	
	public static void print(int[] a) { 
		
		for ( int i = 0 ; i < a.length ; i++ ) {
			System.out.println("反转后的第"+ i + "个元素：" + a[i]);
		}
	}
	
	public static void reverseArray( int[] a ) {
		
		for ( int i = 0 ; i < a.length / 2 ; i++ ) {
			int temp = 0 ;
			temp = a[i];
			a[i] = a[a.length-1-i];
			a[a.length-1-i] = temp;
		}
		
	}
	
	public static int getarraylength(){
		System.out.println("请输入数组的大小：");
		int x = sc.nextInt();			
		if ( x == 0 ) {
			System.out.println("请重新输入，数组大小不能为零。");
		}
		return x;
	}
	
	public static void compare(int[] a) {
		int[] max = new int[a.length] ;
		for ( int i = 0 ; i < a.length ; i++ ) 
			for ( int j = 0 ; j < a.length ; j++) {
				if ( a[i] >= a[j]) {
					max[i] = a[i];
				} else {
					max[i] = a[j];
				}
		}
		System.out.println("数组中最大的元素为：" + max[0] );
		for (int i = 0 ; i < max.length ; i++ ) {
			System.out.println("Max数组中的元素为：" + max[i] );
		}
	}
	
	public static int[] getWord (int a) {
		int[] arr = new int[a];
		for ( int i = 0 ; i < a ; i++ ){
			System.out.println("开始录入第" + i + "个数据（整数）：");
			arr[i] = sc.nextInt();
		}
		return arr;
	}
	
	public static void read (int[] a) {
		
		for ( int i = 0 ; i < a.length ; i++ ) {
			System.out.println("数组的第" + i + "个元素为：" + a[i] );
		}
	}
	
	public static void shuixianhuashu () {
		for ( int i = 100 ; i < 1000 ; i++ ) {
			
			int a = i % 10 ;
			int b = i / 10 % 10 ;
			int c = i / 100 % 10 ;
			int x = a * a * a + b * b * b + c * c * c; 
			if ( x == i ) {
				 System.out.println(x);
			}
		}
	}
}
