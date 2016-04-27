/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Jason Bisanti
 */
public class AbstractListSetTest
{
    private AbstractListSet<Number> setList;
    
    private void setUp(String methodName)
    {
        System.out.println(methodName + "...");        
        this.setUp();
    }
    
    @Before
    public void setUp()
    {
        this.setList = new UniqueList<Number>();
    }
    
    @After
    public void tearDown()
    {
        System.out.println("passed");
    }

    /**
     * Test of size method, of class AbstractListSet.
     */
    @Test
    public void testSize()
    {
        this.setUp("size");
        assertEquals(0, this.setList.size());
        for(int i=1; i<5; i++)
        {
            this.setList.add(i);
            assertEquals(i, this.setList.size());
        }
    }

    /**
     * Test of isEmpty method, of class AbstractListSet.
     */
    @Test
    public void testIsEmpty()
    {
        this.setUp("isEmpty");
        assertEquals(true, this.setList.isEmpty());
        this.setList.addAll(Arrays.asList(4, 7, 6));
        assertEquals(false, this.setList.isEmpty());
    }

    /**
     * Test of contains method, of class AbstractListSet.
     */
    @Test
    public void testContains()
    {
        this.setUp("contains");
        double x = 4;
        assertEquals(false, this.setList.contains(x));
        this.setList.add(x);
        assertEquals(true, this.setList.contains(x));
        this.setList.clear();
        this.setList.addAll(Arrays.asList(1, 7, 8, 9, 2, 3));
        assertEquals(false, this.setList.contains(x));
    }

    /**
     * Test of iterator method, of class AbstractListSet.
     */
    @Test
    public void testIterator()
    {
        this.setUp("iterator");
        List<Double> list = Arrays.asList(3d, 1d, 7d, 5d, 9d);
        this.setList.addAll(list);
        int index = 0;
        Iterator<Number> it = this.setList.iterator();
        while(it.hasNext())
        {
            assertEquals(list.get(index++), it.next());
        }
    }

    /**
     * Test of toArray method, of class AbstractListSet.
     */
    @Test
    public void testToArray_0args()
    {
        this.setUp("toArray");
        List<Double> list = Arrays.asList(3d, 1d, 7d, 5d, 9d);
        this.setList.addAll(list);
        Object[] array = this.setList.toArray();
        for(int i=0; i<array.length; i++)
        {
            assertEquals(list.get(i), array[i]);
        }
    }

    /**
     * Test of toArray method, of class AbstractListSet.
     */
    @Test
    public void testToArray_GenericType()
    {
        this.setUp("toArray_1arg");
        List<Double> list = Arrays.asList(3d, 1d, 7d, 5d, 9d);
        this.setList.addAll(list);
        Number[] array = this.setList.toArray(new Number[0]);
        for(int i=0; i<array.length; i++)
        {
            assertEquals(list.get(i), array[i]);
        }
        array = this.setList.toArray(new Number[2]);
        for(int i=0; i<array.length; i++)
        {
            assertEquals(list.get(i), array[i]);
        }
        array = this.setList.toArray(new Number[20]);
        for(int i=0; i<array.length; i++)
        {
            assertEquals(i < list.size() ? list.get(i) : null, array[i]);
        }
    }

    /**
     * Test of remove method, of class AbstractListSet.
     */
    @Test
    public void testRemove_Object()
    {
        this.setUp("remove");
        this.setList.add(100);
        assertEquals(100, this.setList.remove(0));
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        this.setList.addAll(list);
        for(int i=0; i<list.size(); i++)
        {
            Number val = list.get(i);
            assertEquals(val, this.setList.remove(0));
        }
    }

    /**
     * Test of containsAll method, of class AbstractListSet.
     */
    @Test
    public void testContainsAll()
    {
        this.setUp("containsAll");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        assertEquals(false, this.setList.containsAll(list));
        this.setList.addAll(list);
        assertEquals(true, this.setList.containsAll(list));
        this.setList.remove(300d);
        assertEquals(false, this.setList.containsAll(list));
    }

    /**
     * Test of removeAll method, of class AbstractListSet.
     */
    @Test
    public void testRemoveAll()
    {
        this.setUp("removeAll");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        assertEquals(false, this.setList.removeAll(list));
        this.setList.addAll(list);
        double val = 900d;
        this.setList.add(val);
        assertEquals(true, this.setList.removeAll(list));
        assertEquals(1, this.setList.size());
        assertEquals(val, this.setList.get(0));
    }

    /**
     * Test of retainAll method, of class AbstractListSet.
     */
    @Test
    public void testRetainAll()
    {
        this.setUp("retainAll");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        this.setList.addAll(list);
        assertEquals(false, this.setList.retainAll(list));
        double val = 1000d;
        this.setList.add(val);
        assertEquals(true, this.setList.retainAll(list));
        assertEquals(4, this.setList.size());
        assertEquals(false, this.setList.contains(val));
    }

    /**
     * Test of clear method, of class AbstractListSet.
     */
    @Test
    public void testClear()
    {
        this.setUp("clear");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        this.setList.addAll(list);
        this.setList.clear();
        assertEquals(true, this.setList.isEmpty());
    }

    /**
     * Test of get method, of class AbstractListSet.
     */
    @Test
    public void testGet()
    {
        this.setUp("get");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        this.setList.addAll(list);
        for(int i=0; i<list.size(); i++)
        {
            assertEquals(list.get(i), this.setList.get(i));
        }
    }

    /**
     * Test of remove method, of class AbstractListSet.
     */
    @Test
    public void testRemove_int()
    {
        this.setUp("remove");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        this.setList.addAll(list);
        for(int i=0; i<list.size(); i++)
        {
            assertEquals(list.get(i), this.setList.remove(0));
        }
    }

    /**
     * Test of indexOf method, of class AbstractListSet.
     */
    @Test
    public void testIndexOf()
    {
        this.setUp("indexOf");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 300d);
        this.setList.addAll(list);
        for(int i=0; i<list.size(); i++)
        {
            Double val = list.get(i);
            assertEquals(list.indexOf(val), this.setList.indexOf(val));
        }
    }

    /**
     * Test of lastIndexOf method, of class AbstractListSet.
     */
    @Test
    public void testLastIndexOf()
    {
        this.setUp("lastIndexOf");
        List<Double> list = Arrays.asList(1d, 200d, 50d, 200d);
        this.setList.addAll(list);
        for(int i=0; i<list.size(); i++)
        {
            Double val = list.get(i);
            assertEquals(list.indexOf(val), this.setList.lastIndexOf(val));
        }
    }

    /**
     * Test of listIterator method, of class AbstractListSet.
     */
    @Test
    public void testListIterator_0args()
    {
        this.setUp("listIterator");
        List<Double> list = Arrays.asList(3d, 1d, 7d, 5d, 9d);
        this.setList.addAll(list);
        ListIterator<Number> it = this.setList.listIterator();
        while(it.hasNext())
        {      
            int nextIndex = it.nextIndex();
            Number next = it.next();
            assertEquals(list.get(nextIndex), next);
            int prevIndex = it.previousIndex();
            Number prev = it.previous();
            assertEquals(list.get(prevIndex), prev);
            it.next();            
        }
    }

    /**
     * Test of listIterator method, of class AbstractListSet.
     */
    @Test
    public void testListIterator_int()
    {
        this.setUp("listIterator_int");
        List<Double> list = Arrays.asList(3d, 1d, 7d, 5d, 9d);
        this.setList.addAll(list);
        ListIterator<Number> it = this.setList.listIterator(this.setList.size());
        while(it.hasPrevious())
        {
            int prevIndex = it.previousIndex();
            assertEquals(list.get(prevIndex), it.previous());
        }
    }

    /**
     * Test of subList method, of class AbstractListSet.
     */
    @Test
    public void testSubList()
    {
        this.setUp("subList");
        List<Double> list = Arrays.asList(0d, 1d, 2d, 3d, 4d, 5d);
        this.setList.addAll(list);
        List<Number> sub = this.setList.subList(2, 5);
        System.out.println(sub.toString());
        double val = 2;
        for(Number num: sub)
        {
            assertEquals(val++, num);
        }
        assertEquals(1, sub.indexOf(3d));
        assertEquals(true, sub.remove(3d));
        assertEquals(false, this.setList.contains(3d));
    }
    
}
