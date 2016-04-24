/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jason Bisanti
 */
public class UniqueListTest
{
    /**
     * Test of add method, of class UniqueList.
     */
    @Test
    public void testAdd_GenericType()
    {
        TreeSet<Integer> test = new TreeSet<Integer>();
        test.addAll(Arrays.asList(10, 9, 8, 7, 6));
        SortedSet<Integer> sub = test.subSet(7, 9);
        assertEquals(true, sub.add(11));
        System.out.println(sub);
        System.out.println(test);
        
        
        System.out.println("add");
        UniqueList instance = new UniqueList();
        assertEquals(true, instance.add(1));
        assertEquals(false, instance.add(1));
        assertEquals(true, instance.add(2));
        assertEquals(false, instance.add(2));        
    }

    /**
     * Test of containsAll method, of class UniqueList.
     */
    @Test
    public void testContainsAll()
    {
        System.out.println("containsAll");
        UniqueList instance = new UniqueList();
        List list = new ArrayList(Arrays.asList(1, 3, 5, 7));
        assertEquals(true, instance.addAll(list));
        assertEquals(true, instance.containsAll(list));
        list.add(9);
        assertEquals(false, instance.containsAll(list));
    }

    /**
     * Test of addAll method, of class UniqueList.
     */
    @Test
    public void testAddAll_Collection()
    {
        System.out.println("addAll");
        UniqueList instance = new UniqueList();
        List list = Arrays.asList(1, 3, 5, 7);
        assertEquals(true, instance.addAll(list));
        assertEquals(false, instance.addAll(list));
    }

    /**
     * Test of addAll method, of class UniqueList.
     */
    @Test
    public void testAddAll_int_Collection()
    {
        System.out.println("addAll");
        UniqueList<Integer> instance = new UniqueList<Integer>();
        instance.add(9);
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7));
        instance.addAll(1, list);
        for(int i=0; i<list.size(); i++)
        {
            assertEquals(i+1, instance.indexOf(list.get(i)));
        }
    }

    /**
     * Test of set method, of class UniqueList.
     */
    @Test
    public void testSet()
    {
        System.out.println("set");
        UniqueList<Integer> instance = new UniqueList<Integer>();
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7));
        instance.addAll(list);
        assertEquals(Integer.valueOf(5), instance.set(2, 9));
        assertEquals(2, instance.indexOf(9));
        assertEquals(null, instance.set(0, 7));
    }

    /**
     * Test of add method, of class UniqueList.
     */
    @Test
    public void testAdd_int_GenericType()
    {
        System.out.println("add");
        UniqueList instance = new UniqueList();
        List list = Arrays.asList(1, 3, 5, 7);
        instance.addAll(list);
        instance.add(1, 9);
        assertEquals(5, instance.size());
        assertEquals(1, instance.indexOf(9));
        instance.add(0, 7);
        assertEquals(5, instance.size());
        assertEquals(4, instance.indexOf(7));
    }
    
}
