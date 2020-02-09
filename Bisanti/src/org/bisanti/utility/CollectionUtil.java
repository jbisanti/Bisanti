/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author jason
 */
public class CollectionUtil 
{
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
     * If the {@link Collection} has a size of 10,000 elements or greater, 
     * {@link Collection#parallelStream()} is returned. Otherwise, 
     * {@link Collection#stream()} is returned.
     * @param <T> Type
     * @param collection {@link Collection}
     * @return {@link Stream} of T
     */
    public static <T> Stream<T> getStream(Collection<T> collection)
    {
        return getStream(collection, 10_000);
    }
    
    /**
     * If the {@link Collection#size()} is greater than or equal to threshold, 
     * {@link Collection#parallelStream()} is returned. Otherwise, 
     * {@link Collection#stream()} is returned.
     * @param <T> Type
     * @param collection {@link Collection}
     * @param threshold Threshold to return {@link Collection#parallelStream()} 
     * @return {@link Collection#stream()}
     */
    public static <T> Stream<T> getStream(Collection<T> collection, int threshold)
    {
        return  collection.size() >= threshold ? 
                collection.parallelStream() : 
                collection.stream();
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
    public static boolean equal(final boolean considerOrder, final Collection col1, final Collection col2)
    {
        if(Util.containsNull(col1, col2))
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
                    if(!Util.equal(it1.next(), it2.next()))
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
        ArrayList<T> list = new ArrayList<>();
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
        ArrayList<T> list = new ArrayList<>();
        toList(enumeration, list);
        return list;
    }
    
    /**
     * Adds the value to the collection if it is a non-null value. 
     * 
     * @param <T>
     * @param collection {@link Collection}
     * @param value Potential {@link Object} to add
     * @return true if added, false if not
     */
    public static <T> boolean addIfNonNull(Collection<T> collection, T value)
    {
        return value == null ? false : collection.add(value);
    }
    
}
