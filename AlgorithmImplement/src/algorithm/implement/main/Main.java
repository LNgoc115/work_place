package algorithm.implement.main;

import java.util.Arrays;

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		viewResult("" + 121314 + "," + 31212 + ":" + greatestCommonDivisor(121314, 31212)) ;
//		viewResult("" + 121314 + "," + 31212 + ":" + greatestCommonDivisorRecursive(121314, 31212)) ;
		
//		viewResult("1000: " + greatestIntergerLessThanLogN(1000.7));
                int[] testArray = new int[]{121,41,323,2,23,2,42,4,2,5,325,2};
//		int[] sortedArray = bubleSortArray (testArray);
                int[] sortedArray = selectionSort(testArray);
                for (int i : sortedArray) {
                    viewResult("" + i);
                }
		
	}
        
        /**
         * 
         * @param inputArray
         * @return sorted Array
         */
        public static int[] selectionSort(int[] inputArray) {
            int min;
            for (int i = 0; i< inputArray.length; i++) {
                min = inputArray[i];
                for (int j = i+1; j < inputArray.length; j++) {
                    if (min > inputArray[j]) {
//                        min = inputArray[j];
                        inputArray[i] = inputArray[j];
                        inputArray[j] = min;
                        min = inputArray[i];
                    }
                }
            }
            return inputArray;
        }
        
        /**
         * 
         * @param inputArray
         * @return sorted Array
         */
        static int[] bubleSortArray(int[] inputArray) {
            int[] returnArray;
            if (inputArray == null || inputArray.length == 0) return null;
            returnArray = new int[inputArray.length];
            int temp;
            for (int i = 0; i < inputArray.length - 1; i++) {
                viewResult(toString(inputArray));
                for (int j = 1; j < inputArray.length - i; j++) {
                    if (inputArray[j - 1] > inputArray[j]) {
                        temp = inputArray[j - 1];
                        inputArray[j - 1] = inputArray[j];
                        inputArray[j] = temp;
                    }
                }
            }
            returnArray = Arrays.copyOf(inputArray, inputArray.length);
            return returnArray;
        }
        
        /**
         * 
         * @param arrayInit
         * @return 
         */
        static String toString(int[] arrayInit) {
            StringBuffer strBuffer = new StringBuffer();
            if (arrayInit != null) {
                for (int i : arrayInit) {
                    strBuffer.append("" + i + ";");
                }
            }
            return strBuffer.toString();
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
