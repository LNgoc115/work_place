package algorithm.implement.main;

import java.util.Arrays;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

//        int[] testArray = new int[]{1, 12, 5, 26, 7, 6, 3, 7, 2};
//        quickSort(testArray, 0, testArray.length - 1);
//        viewResult(toString(testArray));
//            String str = "012345126";
//            System.out.println(str.replace("1", "x"));
//            System.out.println(replaceUseRecursion(str,'1','x'));
//        int index = binarySearch(testArray, 3, 0, testArray.length - 1);
//        System.out.println(index);
        String str = "1321232233223212";
        String pat = "2232";
        int index = searchString(str, pat);
        System.out.println(index);

    }

    /**
     *
     * @param str
     * @param pat
     * @return
     */
    public static int searchString(String str, String pat) {
        if (str == null || pat == null || str.length() < pat.length()) {
            return -1;
        }
        char[] strArr = str.toCharArray();
        char[] patArr = pat.toCharArray();
        int i = 0, j = 0;
        int strLength = str.length();
        int patLength = pat.length();
        while (i < strLength && j < patLength) {
            if (strArr[i] == patArr[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == (patLength)) {
            return (i - j);
        } else {
            return -1;
        }
    }

    /**
     *
     * @param arr
     * @param key
     * @param l
     * @param r
     * @return
     */
    public static int binarySearch(int[] arr, int key, int l, int r) {
        if (null == arr || arr.length == 0) {
            return -1;
        }
        if ((r - l) <= 1 && arr[l] != key && arr[r] != key) {
            return -1;
        }
        if ((r - l) == 1 && arr[l] == key) {
            return l;
        }
        if ((r - l) == 1 && arr[r] == key) {
            return r;
        }
        if ((l - r) == 0 && arr[l] == key) {
            return l;
        }
        int pivot = (l + r) / 2;
        if (key == arr[pivot]) {
            return pivot;
        }
        if (key < arr[pivot]) {
            return binarySearch(arr, key, l, pivot - 1);
        }
        if (key > arr[pivot]) {
            return binarySearch(arr, key, pivot + 1, r);
        }
        return -1;
    }

    /**
     *
     * @param str
     * @param oldCh
     * @param newCh
     * @return
     */
    public static String replaceUseIteration(String str, char oldCh, char newCh) {
        if (null == str || str.isEmpty()) {
            return str;
        }
        char[] carr = str.toCharArray();
        for (int i = 0; i < carr.length; i++) {
            if (carr[i] == oldCh) {
                carr[i] = newCh;
            }
        }
        return new String(carr);
    }

    /**
     *
     * @param str
     * @param oldCh
     * @param newCh
     * @return
     */
    public static String replaceUseRecursion(String str, char oldCh, char newCh) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (oldCh == str.charAt(0)) {
            return newCh + replaceUseRecursion(str.substring(1), oldCh, newCh);
        } else {
            return str.charAt(0) + replaceUseRecursion(str.substring(1), oldCh, newCh);
        }
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r && !isStop(arr, l, r)) {
            int i = partition(arr, l, r);
            quickSort(arr, l, i);
            quickSort(arr, i + 1, r);
        }
    }

    public static boolean isStop(int[] arr, int l, int r) {
        if (arr != null && (r - l == 1) && arr[l] <= arr[r]) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param arr
     * @param l
     * @param r
     * @return pivot position which all right position greater than pivot
     * position and all left position smaller than pivot position
     */
    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[(l + r) / 2];
        int i = l;
        int j = r;
        int tmp;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }

            viewResult("i = " + i);
            viewResult("j = " + j);
            printArray(arr, l, r);

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
    static void viewResult(Object obj) {
        System.out.println(obj.toString());
    }

    static void printArray(int[] arr, int l, int r) {
        StringBuffer str = new StringBuffer("");
        for (int i = l; i <= r; i++) {
            str.append(i + ";");
        }
        System.out.println(str.toString());
    }

}
