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
        int[] testArray = new int[]{121, 41, 323, 2, 23, 2, 42, 4, 2, 5, 325, 2};
//		int[] sortedArray = bubleSortArray (testArray);
        quickSort(testArray, 0, testArray.length);

//        int flagP = partition(testArray, 0, testArray.length);
        for (int i : testArray) {
            viewResult("" + i);
        }

    }
    
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int i = partition (arr, l, r);
            quickSort (arr, l, i - 1);
            quickSort(arr, i+1, r);
        }
    }

    /**
     *
     * @param arr
     * @param f
     * @param r
     * @return flag position which all right position greater than flag position
     * and all left position smaller than flag position
     */
    public static int partition(int[] arr, int f, int r) {
        int flag = arr[r - 1];
        int i = f;
        int j = r - 2;
        int tmp;
        while (i < j) {
            while (arr[i] < flag) {
                i++;
            }
            while (arr[j] > flag) {
                j--;
            }
            if (i < j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }

        }
        //dao arr[j] voi arr[r]
        try {
            tmp = arr[j];
            arr[j] = arr[r - 1];
            arr[r - 1] = tmp;
        } catch (Exception e) {
            viewResult("j:" + j);
            viewResult("i:" + i);
            e.printStackTrace();
        }
        return j;
    }

    /**
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        int newValue;
        int j;
        for (int i = 1; i < arr.length; i++) {
            viewResult(toString(arr));
            newValue = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > newValue) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = newValue;
        }
    }

    /**
     *
     * @param inputArray
     * @return sorted Array
     */
    public static int[] selectionSort(int[] inputArray) {
        int min;
        for (int i = 0; i < inputArray.length; i++) {
            min = inputArray[i];
            for (int j = i + 1; j < inputArray.length; j++) {
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
        if (inputArray == null || inputArray.length == 0) {
            return null;
        }
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
        if (checkInteger(n)) {
            return (int) n;
        } else {
            return greatestIntergerLessThanLogN(n / 2);
        }
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
        viewResult("U: " + u + "; V: " + v);
        if (v == 0) {
            return u;
        } else {
            return greatestCommonDivisorRecursive(v, u % v);
        }
    }

    /**
     *
     * @param str
     */
    static void viewResult(String str) {
        System.out.println(str);
    }

}
