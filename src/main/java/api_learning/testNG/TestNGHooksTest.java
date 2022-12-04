package api_learning.testNG;

import org.testng.annotations.Test;

public class TestNGHooksTest {

    @Test(dependsOnMethods = {"testA2"})
    public void testA1(){

    }

    @Test()
    public void testA2(){

    }
}
