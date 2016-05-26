package Test201605222138;

import java.util.*;

public class Test1_GuessNum {

	public static void main ( String[] args ) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个整数，在1-100之间：");
		int GuessNum = (int)((Math.random() * 100) + 1);
		while (true) {
			int result = sc.nextInt();
			if (result > GuessNum) {
				System.out.println("大了");
			} else if (result < GuessNum) {
				System.out.println("小了");
			} else {
				System.out.println("中了");
				break;
			}
		}
	}
	
}
