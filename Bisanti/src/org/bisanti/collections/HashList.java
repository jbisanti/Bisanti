/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.util.Collection;
import java.util.List;
import org.bisanti.util.Util;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * A {@link ListSet} implementation where equality of elements is determined by
 * the {@link Object#hashCode()} method. By default, an {@link ArrayList} is 
 * used as the backing data structure. If you want to use a different 
 * {@link List} implementation as the backing data structure, use constructor 
 * {@link #HashList(List)}.
 * 
 * @author Jason Bisanti
 * @param <T>
 */
public class HashList<T> extends UniqueList<T>
{
    public HashList()
    {
        super();
    }

    public HashList(int initialCapacity)
    {
        super(initialCapacity);
    }

    public HashList(Collection<? extends T> c)
    {
        super(c);
    }

    public HashList(List<T> list)
    {
        super(list);
    }
    
    @Override
    public boolean contains(Object o)
    {
        for(T value: this)
        {
            if(Util.equalHash(value, o))
            {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {        
        if(this.isEmpty())
        {
            return false;
        }
        
        for(Object value: c)
        {
            if(!this.contains(value))
            {
                return false;
            }
        }
        
        return true;
    }

}
