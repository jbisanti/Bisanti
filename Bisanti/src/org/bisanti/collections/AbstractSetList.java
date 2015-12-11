/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.bisanti.util.StringUtil;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 *
 * @author Jason Bisanti
 * @param <T>
 */
public abstract class AbstractSetList<T> implements List<T>, Set<T> 
{
    protected List<T> list;
    
    public AbstractSetList()
    {
        this.list = new ArrayList<T>();
    }
    
    public AbstractSetList(int initialCapacity)
    {
       this.list = new ArrayList<T>(initialCapacity); 
    }
    
    public AbstractSetList(Collection<? extends T> c)
    {
        this(c.size());
        this.addAll(c);
    }
    
    public AbstractSetList(List<T> list)
    {
        this.list = list;
    }
    
    /**
     * Helper method to ensure index is greater than -1 and less than our
     * {@link List} size. If not, an {@link IndexOutOfBoundsException} will be
     * thrown.
     * 
     * @param index index to check
     */
    protected void rangeCheck(int index)
    {
        if(index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size()
    {
        return this.list.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return this.list.contains(o);
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return this.list.toArray(a);
    }

    @Override
    public boolean remove(Object o)
    {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return this.list.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return this.list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return this.list.retainAll(c);
    }

    @Override
    public void clear()
    {
        this.list.clear();
    }

    @Override
    public T get(int index)
    {
        return this.list.get(index);
    }

    @Override
    public T remove(int index)
    {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o)
    {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        return this.list.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex)
    {
        return new SubSetList<T>(this, fromIndex, toIndex-1);
    }
    
    @Override
    public String toString()
    {
        return '[' + StringUtil.toString(", ", this) + ']';
    }

}
