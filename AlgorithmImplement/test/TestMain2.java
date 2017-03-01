
import algorithm.implement.main.Main;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NgocLM6
 */
public class TestMain2 {
    
    int[] arr;
    int key;
    
    @Before
    public void setUp() {
        arr = new int[]{1,2,3,4,1,3,34,45,2,3,3,54,2,4,32,3};
        key = 54;
        System.out.println("Running setUp() in Testmain2");
    }
    
    @Test
    public void testBinarySearch() {
        int result = Main.binarySearch(arr, key, 0, arr.length - 1);
        assertEquals(result,11);
    }
    
}
