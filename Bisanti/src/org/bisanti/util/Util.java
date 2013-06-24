/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.util;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Jason
 */
public final class Util
{
    private Util(){};
    
    public static boolean equal(Object obj1, Object obj2)
    {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }
    
    public static boolean equalValues(Number num1, Number num2)
    {
        if(num1 == null || num2 == null)
        {
            return num1 == num2;
        }
        else
        {
            return num1.doubleValue() == num2.doubleValue();
        }
    }
    
    public static boolean isNullOrEmpty(Collection collection)
    {
        return collection == null || collection.isEmpty();
    }
    
    public static boolean isNullOrEmpty(Map map)
    {
        return map == null || map.isEmpty();
    }
    
}
