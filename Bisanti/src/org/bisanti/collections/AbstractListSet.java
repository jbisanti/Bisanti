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
import java.util.Spliterator;
import org.bisanti.utility.StringUtil;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * An implementation of the {@link ListSet} interface where a {@link List} is
 * used as the backing data structure. The {@link Object#equals(Object)} is used
 * to determine equality when adding elements. It is the responsibility of 
 * incorporating classes to implement the various add methods in accordance with the
 * {@link ListSet} interface.
 * 
 * @author Jason Bisanti
 * @param <T>
 */
public abstract class AbstractListSet<T> implements ListSet<T>
{
    protected List<T> list;
    
    public AbstractListSet()
    {
        this.list = new ArrayList<T>();
    }
    
    public AbstractListSet(int initialCapacity)
    {
       this.list = new ArrayList<T>(initialCapacity); 
    }
    
    public AbstractListSet(Collection<? extends T> c)
    {
        this(c.size());
        this.addAll(c);
    }
    
    public AbstractListSet(List<T> list)
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
        return new SubSetList<T>(this, fromIndex, toIndex);
    }
    
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + '[' + StringUtil.toString(", ", this) + ']';
    }

    @Override
    public Spliterator<T> spliterator()
    {
        return this.list.spliterator();
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 41 * hash + (this.list != null ? this.list.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final AbstractListSet<?> other = (AbstractListSet<?>) obj;
        return !(this.list != other.list && (this.list == null || !this.list.equals(other.list)));
    }
    
}
