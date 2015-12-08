/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Jason Bisanti
 */
public class TreeListTest
{
    private TreeList<Integer> instance;
    
    private void setUp(String method)
    {
        System.out.print(method + "...");
        this.setUp();
    }
    
    @Before
    public void setUp()
    {        
        this.instance = new TreeList<Integer>();
    }
    
    @After
    public void tearDown()
    {
        System.out.print("passed\n");
    }

    /**
     * Test of add method, of class TreeList.
     */
    @Test
    public void testAdd_GenericType()
    {
        this.setUp("add");
        assertEquals(true, instance.add(1));
        assertEquals(true, instance.add(0));
        assertEquals(1, instance.indexOf(1));
        assertEquals(0, instance.indexOf(0));
    }

    /**
     * Test of addAll method, of class TreeList.
     */
    @Test
    public void testAddAll_Collection()
    {
        this.setUp("addAll");
        List<Integer> list = Arrays.asList(3, 2, 1);
        assertEquals(true, this.instance.addAll(list));
        assertEquals(false, this.instance.addAll(list));
        Collections.reverse(list);
        for(int i=0; i<list.size(); i++)
        {
            assertEquals(list.get(i), this.instance.get(i));
        }
    }

    /**
     * Test of addAll method, of class TreeList.
     */
    @Test
    public void testAddAll_int_Collection()
    {
        this.setUp("addAll");
        List<Integer> list = Arrays.asList(6, 2, 4, 0);
        this.instance.addAll(list);
        assertEquals(Integer.valueOf(0), this.instance.get(0));
        assertEquals(Integer.valueOf(2), this.instance.get(1));
        assertEquals(Integer.valueOf(4), this.instance.get(2));
        assertEquals(Integer.valueOf(6), this.instance.get(3));
        list = Arrays.asList(1, 3);
        assertEquals(true, this.instance.addAll(1, list));
        assertEquals(Integer.valueOf(1), this.instance.get(1));
        assertEquals(Integer.valueOf(2), this.instance.get(2));
        assertEquals(false, this.instance.addAll(1, list));
    }

    /**
     * Test of set method, of class TreeList.
     */
    @Test
    public void testSet()
    {
        this.setUp("set");
        this.instance.addAll(Arrays.asList(3, 5, 2));
        assertEquals(null, this.instance.set(0, 7));
        assertEquals(Integer.valueOf(2), this.instance.set(0, -1));
    }

    /**
     * Test of add method, of class TreeList.
     */
    @Test
    public void testAdd_int_GenericType()
    {
        this.setUp("add_int");
        this.instance.addAll(Arrays.asList(2, 3, 1));
        this.instance.add(0, 4);
        assertEquals(Integer.valueOf(1), this.instance.get(0));
        this.instance.add(0, -1);
        assertEquals(Integer.valueOf(-1), this.instance.get(0));
    }

    /**
     * Test of lower method, of class TreeList.
     */
    @Test
    public void testLower()
    {
        this.setUp("lower");
        this.instance.addAll(Arrays.asList(1, 2, 3));
        assertEquals(Integer.valueOf(1), this.instance.lower(2));
        assertEquals(Integer.valueOf(2), this.instance.lower(3));
        assertEquals(null, this.instance.lower(1));
    }

    /**
     * Test of floor method, of class TreeList.
     */
    @Test
    public void testFloor()
    {
        this.setUp("floor");
        this.instance.addAll(Arrays.asList(1, 3, 5));
        assertEquals(Integer.valueOf(1), this.instance.floor(1));
        assertEquals(Integer.valueOf(1), this.instance.floor(2));
        assertEquals(Integer.valueOf(3), this.instance.floor(3));
        assertEquals(Integer.valueOf(3), this.instance.floor(4));
        assertEquals(Integer.valueOf(5), this.instance.floor(5));
        assertEquals(Integer.valueOf(5), this.instance.floor(6));
        assertEquals(null, this.instance.floor(0));
    }

    /**
     * Test of ceiling method, of class TreeList.
     */
    @Test
    public void testCeiling()
    {
        this.setUp("ceiling");
        this.instance.addAll(Arrays.asList(1, 3, 5));
        assertEquals(Integer.valueOf(1), this.instance.ceiling(0));
        assertEquals(Integer.valueOf(1), this.instance.ceiling(1));
        assertEquals(Integer.valueOf(3), this.instance.ceiling(2));
        assertEquals(Integer.valueOf(3), this.instance.ceiling(3));
        assertEquals(Integer.valueOf(5), this.instance.ceiling(4));
        assertEquals(Integer.valueOf(5), this.instance.ceiling(5));
        assertEquals(null, this.instance.ceiling(6));
    }

    /**
     * Test of higher method, of class TreeList.
     */
    @Test
    public void testHigher()
    {
        this.setUp("higher");
        this.instance.addAll(Arrays.asList(1, 3));
        assertEquals(Integer.valueOf(1), this.instance.higher(0));
        assertEquals(Integer.valueOf(3), this.instance.higher(1));
        assertEquals(null, this.instance.higher(3));
    }

    /**
     * Test of pollFirst method, of class TreeList.
     */
    @Test
    public void testPollFirst()
    {
        this.setUp("pollFirst");
        assertEquals(null, this.instance.pollFirst());
        this.instance.addAll(Arrays.asList(1, 3));
        assertEquals(Integer.valueOf(1), this.instance.pollFirst());
        assertEquals(1, this.instance.size());
        assertEquals(Integer.valueOf(3), this.instance.pollFirst());
        assertEquals(true, this.instance.isEmpty());
    }

    /**
     * Test of pollLast method, of class TreeList.
     */
    @Test
    public void testPollLast()
    {
        this.setUp("pollLast");
        assertEquals(null, this.instance.pollLast());
        this.instance.addAll(Arrays.asList(1, 3));
        assertEquals(Integer.valueOf(3), this.instance.pollLast());
        assertEquals(1, this.instance.size());
        assertEquals(Integer.valueOf(1), this.instance.pollLast());
        assertEquals(true, this.instance.isEmpty());
    }

    /**
     * Test of descendingSet method, of class TreeList.
     */
    @Test
    public void testDescendingSet()
    {
        this.setUp("descendingSet");
        this.instance.addAll(Arrays.asList(1, 2, 3, 4, 5));
        Integer max = this.instance.last();
        NavigableSet<Integer> set = this.instance.descendingSet();
        assertEquals(Integer.valueOf(5), set.pollFirst());
        assertEquals(Integer.valueOf(4), set.pollFirst());
        assertEquals(Integer.valueOf(3), set.pollFirst());
        assertEquals(Integer.valueOf(2), set.pollFirst());
        assertEquals(Integer.valueOf(1), set.pollFirst());
    }

    /**
     * Test of descendingIterator method, of class TreeList.
     */
    @Test
    public void testDescendingIterator()
    {
        this.setUp("descendingIterator");
        this.instance.addAll(Arrays.asList(1, 2, 3, 4, 5));
        Integer top = 5;
        Iterator<Integer> it = this.instance.descendingIterator();
        assertEquals(Integer.valueOf(5), it.next());
        assertEquals(Integer.valueOf(4), it.next());
        assertEquals(Integer.valueOf(3), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(1), it.next());
    }

    /**
     * Test of subSet method, of class TreeList.
     */
    @Test
    public void testSubSet_4args()
    {
        this.setUp("subSet, 4args");
        this.instance.addAll(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        NavigableSet<Integer> set = this.instance.subSet(2, true, 6, true);
        assertEquals(5, set.size());
        assertEquals(Integer.valueOf(2), set.pollFirst());
        assertEquals(Integer.valueOf(3), set.pollFirst());
        assertEquals(Integer.valueOf(4), set.pollFirst());
        assertEquals(Integer.valueOf(5), set.pollFirst());
        assertEquals(Integer.valueOf(6), set.pollFirst());
        
        set = this.instance.subSet(2, false, 6, false);
        assertEquals(3, set.size());
        assertEquals(Integer.valueOf(3), set.pollFirst());
        assertEquals(Integer.valueOf(4), set.pollFirst());
        assertEquals(Integer.valueOf(5), set.pollFirst());
        
        set = this.instance.subSet(2, true, 6, false);
        assertEquals(4, set.size());
        assertEquals(Integer.valueOf(2), set.pollFirst());
        assertEquals(Integer.valueOf(3), set.pollFirst());
        assertEquals(Integer.valueOf(4), set.pollFirst());
        assertEquals(Integer.valueOf(5), set.pollFirst());
        
        set = this.instance.subSet(2, false, 6, true);
        assertEquals(4, set.size());
        assertEquals(Integer.valueOf(3), set.pollFirst());
        assertEquals(Integer.valueOf(4), set.pollFirst());
        assertEquals(Integer.valueOf(5), set.pollFirst());
        assertEquals(Integer.valueOf(6), set.pollFirst());
    }

    /**
     * Test of headSet method, of class TreeList.
     */
    @Test
    public void testHeadSet_GenericType_boolean()
    {
        this.setUp("headSet,boolean");
        this.instance.addAll(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        NavigableSet<Integer> set = this.instance.headSet(4, true);
        assertEquals(4, set.size());
        assertEquals(Integer.valueOf(1), set.pollFirst());
        assertEquals(Integer.valueOf(2), set.pollFirst());
        assertEquals(Integer.valueOf(3), set.pollFirst());
        assertEquals(Integer.valueOf(4), set.pollFirst());
        
        set = this.instance.headSet(4, false);
        assertEquals(3, set.size());
        assertEquals(Integer.valueOf(1), set.pollFirst());
        assertEquals(Integer.valueOf(2), set.pollFirst());
        assertEquals(Integer.valueOf(3), set.pollFirst());
    }

    /**
     * Test of tailSet method, of class TreeList.
     */
    @Test
    public void testTailSet_GenericType_boolean()
    {
        this.setUp("headSet,boolean");
        this.instance.addAll(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        NavigableSet<Integer> set = this.instance.tailSet(5, true);
        assertEquals(6, set.size());
        assertEquals(Integer.valueOf(5), set.pollFirst());
        assertEquals(Integer.valueOf(6), set.pollFirst());
        assertEquals(Integer.valueOf(7), set.pollFirst());
        assertEquals(Integer.valueOf(8), set.pollFirst());
        assertEquals(Integer.valueOf(9), set.pollFirst());
        assertEquals(Integer.valueOf(10), set.pollFirst());
        
        set = this.instance.tailSet(5, false);
        assertEquals(5, set.size());
        assertEquals(Integer.valueOf(6), set.pollFirst());
        assertEquals(Integer.valueOf(7), set.pollFirst());
        assertEquals(Integer.valueOf(8), set.pollFirst());
        assertEquals(Integer.valueOf(9), set.pollFirst());
        assertEquals(Integer.valueOf(10), set.pollFirst());
    }

    /**
     * Test of subSet method, of class TreeList.
     */
    @Test
    public void testSubSet_GenericType_GenericType()
    {
        this.setUp("subSet");
        this.instance.addAll(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        SortedSet<Integer> set = this.instance.subSet(2, 5);
        assertEquals(3, set.size());
        Integer val = 2;
        assertEquals(val, set.first());
        set.remove(val);
        val = 3;
        assertEquals(val, set.first());
        set.remove(val);
        val = 4;
        assertEquals(val, set.first());
        set.remove(val);
    }

    /**
     * Test of headSet method, of class TreeList.
     */
    @Test
    public void testHeadSet_GenericType()
    {
        this.setUp("headSet");
        this.instance.addAll(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        SortedSet<Integer> set = this.instance.headSet(6);
        assertEquals(5, set.size());
        Integer val = 1;
        assertEquals(val, set.first());
        set.remove(val);
        val = 2;
        assertEquals(val, set.first());
        set.remove(val);
        val = 3;
        assertEquals(val, set.first());
        set.remove(val);
        val = 4;
        assertEquals(val, set.first());
        set.remove(val);
        
        set = this.instance.headSet(1);
        assertEquals(true, set.isEmpty());
    }

    /**
     * Test of tailSet method, of class TreeList.
     */
    @Test
    public void testTailSet_GenericType()
    {
        this.setUp("tailSet");
        this.instance.addAll(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        SortedSet<Integer> set = this.instance.tailSet(6);
        assertEquals(4, set.size());
        Integer val = 7;
        assertEquals(val, set.first());
        set.remove(val);
        val = 8;
        assertEquals(val, set.first());
        set.remove(val);
        val = 9;
        assertEquals(val, set.first());
        set.remove(val);
        val = 10;
        assertEquals(val, set.first());
        set.remove(val);
        
        set = this.instance.tailSet(10);
        assertEquals(true, set.isEmpty());
    }

    /**
     * Test of comparator method, of class TreeList.
     */
    @Test
    public void testComparator()
    {
        this.setUp("comparator");
        assertEquals(null, this.instance.comparator());
        TreeList<String> tl = new TreeList<String>(String.CASE_INSENSITIVE_ORDER);
        assertEquals(String.CASE_INSENSITIVE_ORDER, tl.comparator());
    }

    /**
     * Test of first method, of class TreeList.
     */
    @Test
    public void testFirst()
    {
        this.setUp("first");
        this.instance.addAll(Arrays.asList(10, 411, 94, -1, Integer.MIN_VALUE));
        assertEquals(Integer.valueOf(Integer.MIN_VALUE), this.instance.first());
    }

    /**
     * Test of last method, of class TreeList.
     */
    @Test
    public void testLast()
    {
        this.setUp("last");
        this.instance.addAll(Arrays.asList(Integer.MAX_VALUE, 10, 411, 94, -1));
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), this.instance.last());
    }
    
}
