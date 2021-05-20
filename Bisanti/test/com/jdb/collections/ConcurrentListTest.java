/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdb.collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jason
 */
public class ConcurrentListTest
{
    
    public ConcurrentListTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of execute method, of class ConcurrentList.
     */
    @Test
    public void testExecute_Runnable()
    {
        System.out.println("execute");
        StringBuilder sb = new StringBuilder("12");
        ConcurrentList instance = new ConcurrentList();    
        instance.execute(()->sb.append(('3')));
        assertEquals("123", sb.toString());

    }

    /**
     * Test of execute method, of class ConcurrentList.
     */
    @Test
    public void testExecute_Supplier()
    {
        System.out.println("execute");
        ConcurrentList instance = new ConcurrentList();
        assertEquals(3, instance.execute(()->{return 3;}));
    }

    
    
}
