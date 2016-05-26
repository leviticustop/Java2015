package Test201605222138;
import java.util.*;

public class Test201605232126 {

	public static void main (String[] args) {
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= i ; j++ ) {
				int sum = i * j ;
				System.out.print(sum + ",");
			}
			System.out.println();
		}
	}	
}
