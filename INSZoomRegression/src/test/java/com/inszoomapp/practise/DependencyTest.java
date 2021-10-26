package com.inszoomapp.practise;

import org.junit.Assert;
import org.testng.annotations.Test;

public class DependencyTest {
    
    @Test(groups = { "init" })
    public void test1(){
	Assert.assertTrue(5==5);
    }
    
    @Test(groups = { "init" })
    public void test2(){
	Assert.assertTrue(6==2);
    }
    
    @Test(groups = { "init" })
    public void test3(){
	Assert.assertTrue(7==7);
    }
    
    @Test(dependsOnGroups = { "init.*" })
    public void test4(){
	System.out.println("In Method 4");
    }
    
    @Test(dependsOnMethods= {"test1", "test2"})
    public void test5(){
	System.out.println("In Test 5");
    }

}
