package org.bisanti.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        return obj == null || Arrays.stream(others).anyMatch((o) -> o == null);
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
     * Performs a null-safe equality check based on their
     * {@link Object#hashCode()}s. This method guarantees that if either 
     * parameter is null, a {@link NullPointerException} WILL NOT be
     * thrown before {@link Object#hashCode()} is called on each paramter.
     * 
     * @param obj1 Any {@link Object}
     * @param obj2 Any {@link Object}
     * @return true if parameters are have equal hash code values, false if not
     */
    public static boolean equalHash(final Object obj1, final Object obj2)
    {
        if(obj1 == null || obj2 == null)
        {
            return obj1 == obj2;
        }
        
        return obj1.hashCode() == obj2.hashCode();
    }
    
    /**
     * Replaced by {@link NumberPlus#equal(Number, Number)}
     * @param num1 Any {@link Number}
     * @param num2 Any {@link Number}
     * @return true if the double values are equivalent, false if not
     * @deprecated 
     */
    @Deprecated
    public static boolean equalValues(final Number num1, final Number num2)
    {
        return NumberPlus.equal(num1, num2);
    }
    
    /**
     * Replaced by {@link CollectionUtil#isNullOrEmpty(Collection)}
     * @param collection Any {@link Collection}
     * @return true if the {@link Collection} is null or <code>isEmpty()</code>
     * returns true, false otherwise
     * @deprecated 
     */
    @Deprecated
    public static boolean isNullOrEmpty(final Collection collection)
    {
        return CollectionUtil.isNullOrEmpty(collection);
    }
    
    /**
     * DReplaced by {@link MapUtil#isNullOrEmpty(Map)} 
     * @param map Any {@link Map}
     * @return true if the {@link Map} is null or <code>isEmpty()</code>
     * returns true, false otherwise
     * @deprecated
     */
    @Deprecated
    public static boolean isNullOrEmpty(final Map map)
    {
        return MapUtil.isNullOrEmpty(map);
    }
    
    /**
     * Replaced by {@link ArrayUtil#isNullOrEmpty(Object[]) 
     * @param array Any {@link Object}-based array
     * @return true if the array is null or has a length of 0, false otherwise
     * @deprecated
     */
    @Deprecated
    public static boolean isNullOrEmpty(final Object[] array)
    {
        return ArrayUtil.isNullOrEmpty(array);
    }
    
    /**
     * Replaced by {@link CollectionUtil#toList(Iterator, List)} 
     * @param <T> Class of {@link Object}s contained in {@link Iterator} and 
     * {@link List}
     * @param iterator {@link Iterator} to transfer elements from
     * @param list {@link List} to add elements to
     * @deprecated
     */
    public static <T> void toList(final Iterator<T> iterator, final List<T> list)
    {
        CollectionUtil.toList(iterator, list);
    }
    
    /**
     * Replaced by {@link CollectionUtil#asList(Iterator)}
     * @param <T>  Class of {@link Object}s contained in {@link Iterator}
     * @param iterator {@link Iterator} to transfer elements from
     * @return {@link List} with all {@link Iterator} elements
     * @deprecated 
     */
    @Deprecated
    public static <T> ArrayList<T> asList(final Iterator<T> iterator)
    {
        return CollectionUtil.asList(iterator);
    }
    
    /**
     * Replaced by {@link CollectionUtil#toList(Enumeration, List)}
     * @param <T> Class of {@link Object}s contained in {@link Enumeration} and 
     * {@link List}
     * @param enumeration {@link Enumeration} to transfer elements from
     * @param list {@link List} to add elements to
     * @deprecated
     */
    @Deprecated
    public static <T> void toList(final Enumeration<T> enumeration, final List<T> list)
    {
        CollectionUtil.toList(enumeration, list);
    }
    
    /**
     * Replaced by {@link CollectionUtil#asList(Enumeration)}
     * @param <T>  Class of {@link Object}s contained in {@link Iterator}
     * @param enumeration {@link Enumeration} to transfer elements from
     * @return {@link List} with all {@link Iterator} elements
     * @deprecated 
     */
    @Deprecated
    public static <T> ArrayList<T> asList(final Enumeration<T> enumeration)
    {
        return CollectionUtil.asList(enumeration);
    }
    
    /**
     * Replaced by {@link CollectionUtil#equal(boolean, Collection, Collection)}
     * 
     * @param considerOrder true if order of elements is necessary for equality
     * @param col1 Any {@link Collection}
     * @param col2 Any {@link Collection}
     * @return true if the {@link Collection}s are both the same size and one
     * {@link Collection} contains all the elements of the other 
     * {@link Collection}
     * @deprecated 
     */
    @Deprecated
    public static boolean equalCollections(final boolean considerOrder, final Collection col1, final Collection col2)
    {
        return CollectionUtil.equal(considerOrder, col1, col2);
    }
    
    /**
     * Replaced by {@link MapUtil#equal(boolean, Map, Map)}
     * @param considerOrder true if order of elements is necessary for equality
     * @param map1 Any {@link Map}
     * @param map2 Any {@link Map}
     * @return if the {@link Map}s are both the same size and one {@link Map}
     * contains all the keys-value pairs of the other {@link Map}
     * @deprecated
     */
    @Deprecated
    public static boolean equalMaps(final boolean considerOrder, final Map map1, final Map map2)
    {
        return MapUtil.equal(considerOrder, map1, map2);
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
