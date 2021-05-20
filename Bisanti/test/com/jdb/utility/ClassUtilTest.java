/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdb.utility;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jason
 */
public class ClassUtilTest
{
    private static Collection<Class<?>> lists;
    
    private static Set<Class<?>> getTypes(String... names)
    {
        Set<Class<?>> classes = new LinkedHashSet<>();
        try 
        {
            for(String name: names)
            {
                classes.add(Class.forName("com.jdb.collections." + name));
            }
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ClassUtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }
    
    @BeforeClass
    public static void beforeClass()
    {
        String p = "com.jdb.colllections.";
        String c = ".class";
        lists = getTypes("AbstractListSet", "ConcurrentList", "HashList", 
                "ListSet", "SubList", "TreeList", "UniqueList");
    }
    
    @Test
    public void testGetClasses_()
    {        
        Set<Class<List>> collections = ClassUtil.findClasses(List.class, false);
        Assert.assertEquals(false, collections.isEmpty());
        Assert.assertEquals(true, collections.containsAll(lists));
    }
}
