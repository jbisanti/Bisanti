package org.bisanti.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * A class of utility methods for operations such as equality, collection 
 * manipulation, and hash code generation.
 * @author Jason Bisanti
 */
public final class Util
{
    /** Private constructor to prevent instantiation */
    private Util(){};
    
    /**
     * Determines if any of the parameters are null
     * 
     * @param obj Initial parameter to test for null
     * @param others Subsequent values to test for null
     * @return true if any parameter is null, false if all are non-null
     */
    public static boolean containsNull(final Object obj, final Object... others)
    {
        if(obj == null)
        {
            return true;
        }

        for (Object other : others)
        {
            if (other == null)
            {
                return true;
            }
        }        
        
        return false;
    }
    
    /**
     * Performs a null-safe equality check. This method guarantees that if 
     * either parameter is null, a {@link NullPointerException} WILL NOT be
     * thrown before <code>obj1.equals(obj2)</code> is called.
     * 
     * @param obj1 Any {@link Object}
     * @param obj2 Any {@link Object}
     * @return true if parameters are equal, false if not
     */
    public static boolean equal(final Object obj1, final Object obj2)
    {
        if(obj1 == null || obj2 == null)
        {
            return obj1 == obj2;
        }
        
        return obj1.equals(obj2);
    }
    
    /**
     * Determines if the {@link Number} parameters are numerically equivalent; 
     * the <code>doubleValue()</code> method is used to test equality. This 
     * method is also null-safe; if one parameter is null, no {@link Exception}s
     * are thrown and false will be returned.
     * @param num1 Any {@link Number}
     * @param num2 Any {@link Number}
     * @return true if the double values are equivalent, false if not
     */
    public static boolean equalValues(final Number num1, final Number num2)
    {
        if(containsNull(num1, num2))
        {
            return num1 == num2;
        }
        else
        {
            return num1.doubleValue() == num2.doubleValue();
        }
    }
    
    /**
     * Determines if the {@link Collection} is null or empty.
     * @param collection Any {@link Collection}
     * @return true if the {@link Collection} is null or <code>isEmpty()</code>
     * returns true, false otherwise
     */
    public static boolean isNullOrEmpty(final Collection collection)
    {
        return collection == null || collection.isEmpty();
    }
    
    /**
     * Determines if the {@link Map} is null or empty.
     * @param map Any {@link Map}
     * @return true if the {@link Map} is null or <code>isEmpty()</code>
     * returns true, false otherwise
     */
    public static boolean isNullOrEmpty(final Map map)
    {
        return map == null || map.isEmpty();
    }
    
    /**
     * Determines if an array is null or empty
     * @param array Any {@link Object}-based array
     * @return true if the array is null or has a length of 0, false otherwise
     */
    public static boolean isNullOrEmpty(final Object[] array)
    {
        return array == null || array.length == 0;
    }
    
    /**
     * Convenience method to transfer the contents of an {@link Iterator} to a
     * {@link List}. Note that elements are added to parameter list, so no 
     * values are overridden.
     * @param <T> Class of {@link Object}s contained in {@link Iterator} and 
     * {@link List}
     * @param iterator {@link Iterator} to transfer elements from
     * @param list {@link List} to add elements to
     */
    public static <T> void toList(final Iterator<T> iterator, final List<T> list)
    {
        while(iterator.hasNext())
        {
            list.add(iterator.next());
        }
    }
    
    /**
     * Creates an {@link ArrayList} from the given {@link Iterator}. 
     * {@link ArrayList} was chosen because it typically has the best 
     * performance of any {@link List} implementation. If you're looking to 
     * create a different type of {@link List} instance, see method
     * {@link toList(Iterator, List)}.
     * @param <T>  Class of {@link Object}s contained in {@link Iterator}
     * @param iterator {@link Iterator} to transfer elements from
     * @return {@link List} with all {@link Iterator} elements
     */
    public static <T> ArrayList<T> asList(final Iterator<T> iterator)
    {
        ArrayList<T> list = new ArrayList<T>();
        toList(iterator, list);
        return list;
    }
    
    /**
     * Convenience method to transfer the contents of an {@link Enumeration} to
     * a {@link List}. Note that elements are added to parameter list, so no 
     * values are overridden.
     * @param <T> Class of {@link Object}s contained in {@link Enumeration} and 
     * {@link List}
     * @param enumeration {@link Enumeration} to transfer elements from
     * @param list {@link List} to add elements to
     */
    public static <T> void toList(final Enumeration<T> enumeration, final List<T> list)
    {
        while(enumeration.hasMoreElements())
        {
            list.add(enumeration.nextElement());
        }
    }
    
    /**
     * Creates an {@link ArrayList} from the given {@link Iterator}. 
     * {@link ArrayList} was chosen because it typically has the best 
     * performance of any {@link List} implementation. If you're looking to 
     * create a different type of {@link List} instance, see method
     * {@link toList(Iterator, List)}.
     * @param <T>  Class of {@link Object}s contained in {@link Iterator}
     * @param enumeration {@link Enumeration} to transfer elements from
     * @return {@link List} with all {@link Iterator} elements
     */
    public static <T> ArrayList<T> asList(final Enumeration<T> enumeration)
    {
        ArrayList<T> list = new ArrayList<T>();
        toList(enumeration, list);
        return list;
    }
    
    /**
     * Performs a null-safe check to see if each {@link Collection} is the same
     * size and method containsAll is true for both parameters.
     * 
     * @param considerOrder true if order of elements is necessary for equality
     * @param col1 Any {@link Collection}
     * @param col2 Any {@link Collection}
     * @return true if the {@link Collection}s are both the same size and one
     * {@link Collection} contains all the elements of the other 
     * {@link Collection}
     */
    public static boolean equalCollections(final boolean considerOrder, final Collection col1, final Collection col2)
    {
        if(containsNull(col1, col2))
        {
            return col1 == col2;
        }
        else if(considerOrder)
        {
            if(col1.size() == col2.size())
            {
                Iterator it1 = col1.iterator();
                Iterator it2 = col2.iterator();
                while(it1.hasNext())
                {
                    if(!equal(it1.next(), it2.next()))
                    {
                        return false;
                    }
                }
                
                return true;
            }
            else
            {            
                return false;
            }
        }
        else
        {
            return col1 == col2 || (col1.size() == col2.size() && col1.containsAll(col2) && col2.containsAll(col1));
        }
    }
    
    /**
     * Performs a null-safe equality check to determine if the {@link Map} 
     * parameters are equal based on the key-value pairs.
     * @param considerOrder true if order of elements is necessary for equality
     * @param map1 Any {@link Map}
     * @param map2 Any {@link Map}
     * @return if the {@link Map}s are both the same size and one {@link Map}
     * contains all the keys-value pairs of the other {@link Map}
     */
    public static boolean equalMaps(final boolean considerOrder, final Map map1, final Map map2)
    {
        if(containsNull(map1, map2))
        {
            return map1 == map2;
        }
        else if(considerOrder)
        {
            if(map1.size() == map2.size())
            {
                Iterator<Map.Entry> it1 = map1.entrySet().iterator();
                Iterator<Map.Entry> it2 = map2.entrySet().iterator();
                while(it1.hasNext())
                {
                    Map.Entry entry1 = it1.next();
                    Map.Entry entry2 = it2.next();
                    if(!equal(entry1.getKey(), entry2.getKey()) ||
                       !equal(entry1.getValue(), entry2.getValue()))
                    {
                        return false;
                    }
                }
                
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            Set<Map.Entry> entries = map1.entrySet();
            for(Map.Entry entry: entries)
            {
                if(!equal(entry.getValue(), map2.get(entry.getKey())))
                {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    /**
     * Creates a hash code based upon {@link Object}s passed in as parameters 
     * with seed1 as the initial hash value and seed2 as the multiplier for each
     * attribute. Note that for mathematically efficient hash code generation, 
     * each seed parameter should be a prime number. The algorithm for the hash
     * generation is:<code><br><br>
     * int hash = seed1;<br>
     * for(Object a: attributes) { <br>
     * hash = seed2 * hash + (a == null ? 0 : a.hashCode());<br>
     * }<br></code>
     * 
     * @param seed1 Initial hash value, ideally a prime number
     * @param seed2 Multiplier for each attribute, also ideally a prime number
     * @param attributes Attributes whose hashCode() value will be added to the
     * generated hash code.
     * @return Hash code based upon seed values and attributes hashCode() values
     */
    public static int createHashCode(final int seed1, final int seed2, final Object... attributes)
    {
        int hash = seed1;
        for(Object attribute: attributes)
        {
            hash *= seed2;
            if(attribute != null)
            {
                hash += attribute.hashCode();
            }
        }
        return hash;
    }
    
    /**
     * A reflection-based method that automatically generates a hash code for 
     * parameter toHash based upon all its accessible fields. Note that this 
     * method will include ALL accessible fields, including public and/or static
     * fields, which are not typically included in most hash code generating
     * algorithms.
     * <br><br>
     * The intended usage of this method is for it to be called 
     * within an Object's own <code>hashCode()</code> method where 'this' can be
     * accessed and passed in as the first parameter. E.g. (<code>
     * Util.createHashCode(this, 13, 23);</code>). Note that calling this method
     * on an external Object will most likely not result in private fields being
     * accessible and may not result in a uniquely accurate hash code value.
     * <br><br>
     * For a more controlled hash code generation method and to get
     * more information on how the hash code is generated, see method 
     * {@link createHashCode(int, int, Object...)}.
     * 
     * @param toHash {@link Object} to generate a hash code for
     * @param seed1 Initial hash value, ideally a prime number
     * @param seed2 Multiplier for each accessible field in parameter toHash, 
     * also ideally a prime number.
     * @return Hash code based upon all of toHash parameter's accessible fields 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public static int createHashCode(final Object toHash, final int seed1, final int seed2) throws IllegalArgumentException, IllegalAccessException
    {
        int hash = seed1;
        for(Field field: toHash.getClass().getDeclaredFields())
        {
            hash *= seed2;
            Object value = field.get(toHash);
            if(value != null)
            {
                hash += value.hashCode();
            }
        }
        return hash;
    }
    
    public static int createHashcode(final Object toHash, final int seed1, final int seed2, final int accessModifiers) throws IllegalArgumentException, IllegalAccessException
    {
        int hash = seed1;
        for(Field field: toHash.getClass().getDeclaredFields())
        {            
            if(accessModifiers >= field.getModifiers())
            {
                hash *= seed2;
                Object value = field.get(toHash);
                if(value != null)
                {
                    hash += value.hashCode();
                }
            }
        }
        return hash;
    }
    
}
