package algorithm.implement.main;

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		viewResult("" + 121314 + "," + 31212 + ":" + greatestCommonDivisor(121314, 31212)) ;
//		viewResult("" + 121314 + "," + 31212 + ":" + greatestCommonDivisorRecursive(121314, 31212)) ;
		
		viewResult("1000: " + greatestIntergerLessThanLogN(1000.7));
		
		
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	static int greatestIntergerLessThanLogN(double n) {
		if (checkInteger(n)) return (int)n;
		else return greatestIntergerLessThanLogN (n / 2);
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	static boolean checkInteger(double n) {
		return (n % 1) == 0;
	}
	
	/**
	 * 
	 * @param u
	 * @param v
	 * @return the greatest common divisor of u and v
	 */
	static int greatestCommonDivisor(int u, int v) {
		int t = u < v ? u : v;
		while (u % t != 0 || v % t != 0) {
			viewResult("t:" + t);
			t--;
		}
		return t;
	}
	
	/**
	 * 
	 * @param u
	 * @param v
	 * @return
	 */
	static int greatestCommonDivisorRecursive(int u, int v) {
		viewResult ("U: " + u + "; V: " + v);
		if (v == 0) return u;
		else return greatestCommonDivisorRecursive (v, u % v);
	}
	
	/**
	 * 
	 * @param str
	 */
	static void viewResult(String str) {
		System.out.println(str);
	}

}
