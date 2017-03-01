
import algorithm.implement.main.Main;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NgocLM6
 */
public class TestMain {

    String str, pat;

    @Before
    public void setUp() {
        str = "1232141fasdgasba";
        pat = "32";
        System.out.println("Running the setUp");
    }

    @Test (expected = IOException.class)
    public void testSearchString() {

        int result = Main.searchString(str, pat);
        assertEquals(2, result);
    }
    
    @Ignore
    public void testSearchString2() {
        int result = Main.searchString(str, pat);
        assertEquals(3, result);
    }

}
