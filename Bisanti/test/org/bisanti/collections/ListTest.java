/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.collections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.bisanti.utility.ClassUtil;
import org.bisanti.utility.NumberPlus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Helper test class to test all {@link List} implementation types in the
 * {@link org.bisanti.collections} {@link Package}.
 * 
 * @author jason
 */
public class ListTest
{
    private static final Collection<List<String>> lists = new LinkedHashSet<>();
    
    public ListTest()
    {
        
    }
    
    @BeforeClass
    public static void beforeClass()
    {
        for(Class<?> c: ClassUtil.getClasses("org.bisanti.collections", true, false))
        {
            if(List.class.isAssignableFrom(c) && !Modifier.isAbstract(c.getModifiers()) && Modifier.isPublic(c.getModifiers()))
            {
                try
                {
                    for(Constructor con: c.getConstructors())
                    {
                        if(con.getParameters().length == 0)
                        {
                            lists.add((List<String>) con.newInstance());
                            break;
                        }
                    }
                }
                catch (Exception ex)
                {
                    fail("Unable to instantiate List type: " + c);
                }
            }
        }
    }
    
    @Before
    public void setUp()
    {
        lists.forEach((l)->l.clear());
    }

    /**
     * Test of size method, of class ConcurrentList.
     */
    @Test
    public void testSize()
    {
        System.out.println("size");
        for(List<String> list: lists)
        {
            if(list.size() != 0)
            {
                fail(list.getClass().getName());
            }            
            list.add("a");            
            if(list.size() != 1)
            {
                fail(list.getClass().getName());
            }
            list.addAll(Arrays.asList("b", "c", "d"));
            if(list.size() != 4)
            {
                fail(list.getClass().getName());
            }
            list.remove("b");
            if(list.size() != 3)
            {
                fail(list.getClass().getName());
            }
            list.remove("c");
            if(list.size() != 2 || list.contains("3"))
            {
                fail(list.getClass().getName());
            }            
        }
    }

    /**
     * Test of isEmpty method, of class ConcurrentList.
     */
    @Test
    public void testIsEmpty()
    {
        System.out.println("isEmpty");
        for(List<String> list: lists)
        {
            if(!list.isEmpty())
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of contains method, of class ConcurrentList.
     */
    @Test
    public void testContains()
    {
        System.out.println("contains");
        for(List<String> list: lists)
        {
            if(list.contains("a"))
            {
                fail(list.getClass().getName());
            }
            list.add("a");
            if(!list.contains("a"))
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of toArray method, of class ConcurrentList.
     */
    @Test
    public void testToArray_0args()
    {
        System.out.println("toArray");
        for(List<String> list: this.lists)
        {
            list.addAll(Arrays.asList("1", "2", "3"));
            Object[] array = list.toArray();
            if(array.length != list.size())
            {
                fail(list.getClass().getName());
            }
            NumberPlus count = new NumberPlus(1);
            for(Object o: array)
            {
                if(!o.equals(count.getAndIncrement().toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
    }

    /**
     * Test of toArray method, of class ConcurrentList.
     */
    @Test
    public void testToArray_GenericType()
    {
        System.out.println("toArray");
        for(List<String> list: this.lists)
        {
            list.addAll(Arrays.asList("1", "2", "3"));
            String[] array = list.toArray(new String[0]);
            if(array.length != list.size())
            {
                fail(list.getClass().getName());
            }
            NumberPlus count = new NumberPlus(1);
            for(String o: array)
            {
                if(!o.equals(count.getAndIncrement().toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
    }

    /**
     * Test of add method, of class ConcurrentList.
     */
    @Test
    public void testAdd_GenericType()
    {
        System.out.println("add");
        for(List<String> list: this.lists)
        {
            if(list.add("a") && list.size() != 1)
            {
                fail(list.getClass().getName());
            }
            
            if(list.add("a") && list.size() != 2)
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of remove method, of class ConcurrentList.
     */
    @Test
    public void testRemove_Object()
    {
        System.out.println("remove");
        for(List<String> list: lists)
        {
            list.add("a");
            if(!list.remove("a") || !list.isEmpty())
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of containsAll method, of class ConcurrentList.
     */
    @Test
    public void testContainsAll()
    {
        System.out.println("containsAll");
        Set<String> set = new LinkedHashSet<>(Arrays.asList("a", "c", "e"));
        for(List<String> list: this.lists)
        {
            list.addAll(Arrays.asList("a", "b", "c", "d", "e"));
            if(!list.containsAll(set))
            {
                fail(list.getClass().getName());
            }
            list.remove(2);
            if(list.containsAll(set))
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of addAll method, of class ConcurrentList.
     */
    @Test
    public void testAddAll_Collection()
    {
        System.out.println("addAll");
        for(List<String> list: this.lists)
        {
            list.addAll(Arrays.asList("0", "1", "2"));
            if(list.size() != 3)
            {
                fail(list.getClass().getName());
            }
            
            for(Integer i=0; i<3; i++)
            {
                if(!list.get(i).equals(i.toString()))
                {
                    fail(list.getClass().getName());
                }
            }
            
            list.addAll(Arrays.asList("3", "4", "5"));
            if(list.size() != 6)
            {
                fail(list.getClass().getName());
            }
            
            for(Integer i=0; i<6; i++)
            {
                if(!list.get(i).equals(i.toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
        
    }

    /**
     * Test of addAll method, of class ConcurrentList.
     */
    @Test
    public void testAddAll_int_Collection()
    {
        System.out.println("addAll");
        for(List<String> list: this.lists)
        {
            list.addAll(Arrays.asList("0", "1", "5"));
            if(!list.addAll(2, Arrays.asList("2", "3", "4")) || list.size() != 6)
            {
                fail(list.getClass().getName());
            }
            for(Integer i=0; i<list.size(); i++)
            {
                if(!list.get(i).equals(i.toString()))
                {
                    fail(list.getClass().getName());
                }
            }
                
        }
    }

    /**
     * Test of removeAll method, of class ConcurrentList.
     */
    @Test
    public void testRemoveAll()
    {
        System.out.println("removeAll");
        for(List<String> list: this.lists)
        {
            list.addAll(Arrays.asList("1", "2", "3", "4"));
            list.removeAll(Arrays.asList("2", "4"));
            if(list.size() != 2)
            {
                fail(list.getClass().getName());
            }
            if(list.get(0) != "1")
            {
                fail(list.getClass().getName());
            }
            if(list.get(1) != "3")
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of retainAll method, of class ConcurrentList.
     */
    @Test
    public void testRetainAll()
    {
        System.out.println("retainAll");
        for(List<String> list: this.lists)
        {
            list.addAll(Arrays.asList("1", "2", "3", "4"));
            list.retainAll(Arrays.asList("2", "4"));
            if(list.size() != 2)
            {
                fail(list.getClass().getName());
            }
            if(list.get(0) != "2")
            {
                fail(list.getClass().getName());
            }
            if(list.get(1) != "4")
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of clear method, of class ConcurrentList.
     */
    @Test
    public void testClear()
    {
        System.out.println("clear");
        for(List<String> list: this.lists)
        {
            list.add("fdsfadsa");
            list.clear();
            assertTrue(list.isEmpty());
        }
    }

    /**
     * Test of get method, of class ConcurrentList.
     */
    @Test
    public void testGet()
    {
        System.out.println("get");
        for(List<String> list: this.lists)
        {
            list.add("x");
            assertTrue("x".equals(list.get(0)));
            list.add("y");
            assertTrue("y".equals(list.get(1)));
        }
    }

    /**
     * Test of set method, of class ConcurrentList.
     */
    @Test
    public void testSet()
    {
        System.out.println("set");
        for(List<String> list: this.lists)
        {
            list.add("2");
            Object prev = list.set(0, "-2");
            if(prev == null)
            {
                if(list.contains("2"))
                {
                    fail(list.getClass().getName());
                }
            }
            else
            {
                if(!"-2".equals(list.get(0)))
                {
                    fail(list.getClass().getName());
                }
            }
        }
    }

    /**
     * Test of add method, of class ConcurrentList.
     */
    @Test
    public void testAdd_int_GenericType()
    {
        System.out.println("add");
        for(List<String> list: this.lists)
        {
            for(Integer i=0; i<3; i++)
            {
                list.add(i.toString());
                if(!list.get(i).equals(i.toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
    }

    /**
     * Test of remove method, of class ConcurrentList.
     */
    @Test
    public void testRemove_int()
    {
        System.out.println("remove");
        for(List<String> list: this.lists)
        {
            for(Integer i=0; i<3; i++)
            {
                list.add(i.toString());
            }
            list.remove(1);
            
            if(!list.get(1).equals("2"))
            {
                fail(list.getClass().getName());
            }
            
            if(!list.get(0).equals("0"))
            {
                fail(list.getClass().getName());
            }
        }
    }

    /**
     * Test of indexOf method, of class ConcurrentList.
     */
    @Test
    public void testIndexOf()
    {
        System.out.println("indexOf");
        for(List<String> list: this.lists)
        {
            for(Integer i=0; i<3; i++)
            {
                list.add(i.toString());
            }
            
            if(list.indexOf("0") != 0)
            {
                fail(list.getClass().getName());
            }
            
            if(list.indexOf("1") != 1)
            {
                fail(list.getClass().getName());
            }
            
            if(list.indexOf("2") != 2)
            {
                fail(list.getClass().getName());
            }            
        }
    }

    /**
     * Test of lastIndexOf method, of class ConcurrentList.
     */
    @Test
    public void testLastIndexOf()
    {
        System.out.println("lastIndexOf");
        for(List<String> list: this.lists)
        {
            for(int i=0; i<3; i++)
            {
                list.add("index");
            }
            
            if(list.lastIndexOf("index") != list.size()-1)
            {
                fail(list.getClass().getName());
            }         
        }
    }

    /**
     * Test of iterator method, of class ConcurrentList.
     */
    @Test
    public void testIterator()
    {
        System.out.println("iterator");
        for(List<String> list: this.lists)
        {
            for(Integer i=0; i<3; i++)
            {
                list.add(i.toString());
            }   
        }
        
        for(List<String> list: this.lists)
        {
            Iterator<String> it = list.iterator();
            NumberPlus count = new NumberPlus(0);
            while(it.hasNext())
            {
                if(!it.next().equals(count.getAndIncrement().toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
    }

    /**
     * Test of listIterator method, of class ConcurrentList.
     */
    @Test
    public void testListIterator_0args()
    {
        System.out.println("listIterator");
        for(List<String> list: this.lists)
        {
            for(Integer i=0; i<3; i++)
            {
                list.add(i.toString());
            }   
        }
        
        for(List<String> list: this.lists)
        {
            ListIterator<String> it = list.listIterator();
            NumberPlus count = new NumberPlus(0);
            while(it.hasNext())
            {
                if(!it.next().equals(count.getAndIncrement().toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
    }

    /**
     * Test of listIterator method, of class ConcurrentList.
     */
    @Test
    public void testListIterator_int()
    {
        System.out.println("listIterator");
        for(List<String> list: this.lists)
        {
            for(Integer i=0; i<3; i++)
            {
                list.add(i.toString());
            }   
        }
        
        NumberPlus count = new NumberPlus(0);
        for(List<String> list: this.lists)
        {            
            ListIterator<String> it = list.listIterator(count.intValue());            
            while(it.hasNext())
            {
                if(!it.next().equals(count.getAndIncrement().toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
        
        count.setValue(1);
        for(List<String> list: this.lists)
        {            
            ListIterator<String> it = list.listIterator(count.intValue());            
            while(it.hasNext())
            {
                if(!it.next().equals(count.getAndIncrement().toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
        
        count.setValue(2);
        for(List<String> list: this.lists)
        {            
            ListIterator<String> it = list.listIterator(count.intValue());            
            while(it.hasNext())
            {
                if(!it.next().equals(count.getAndIncrement().toString()))
                {
                    fail(list.getClass().getName());
                }
            }
        }
    }

    /**
     * Test of subList method, of class ConcurrentList.
     */
    @Test
    public void testSubList()
    {
        System.out.println("subList");
        for(List<String> list: this.lists)
        {
            for(Integer i=0; i<5; i++)
            {
                list.add(i.toString());
            }   
        }
        
        for(List<String> list: this.lists)
        {            
            List<String> sub = list.subList(0, 3);
            sub.clear();
            if (list.size() != 2)
            {
                fail(list.getClass().getName());
            }

            if (!list.get(0).equals("3"))
            {
                fail(list.getClass().getName());
            }

            if (!list.get(1).equals("4"))
            {
                fail(list.getClass().getName());
            }
        }
    }
    
}
