/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdb.collections;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import com.jdb.utility.Util;

/**
 * <i>
 * Written and authored by Jason Bisanti.Free to use and reproduce, but please
 keep my name as the original author!
 <br><br></i>
 A {@link ListSet} implementation meant to be a sublist reference for any
 other {@link ListSet} implementation. However, it can be used for any 
 {@link List} implementation in general. Modifications to this instance will 
 * also be reflected to the {@link List} it references.
 * 
 * @author Jason Bisanti
 * @param <T> Type of elements
 */
public class SubList<T> implements ListSet<T>
{
    protected final List<T> parent;
    protected int offset;
    protected int maxIndex;

    public SubList(List<T> parent, int fromIndex, int toIndex)
    {
        this.parent = parent;
        rangeCheck(fromIndex);
        rangeCheck(toIndex);
        this.offset = fromIndex;
        this.maxIndex = toIndex;
    }

    protected final void rangeCheck(int index)
    {
        if (index < 0 || index > this.parent.size())
        {
            throw new IndexOutOfBoundsException();
        }
    }

    private void subRangeCheck(int index)
    {
        if (this.isEmpty() || index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean add(T e)
    {
        boolean added;
        if (added = this.parent.add(e))
        {
            maxIndex++;
        } 
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        return this.addAll(this.maxIndex, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        this.subRangeCheck(index);
        final int oldSize = this.parent.size();
        boolean added;
        if(added = this.parent.addAll(index, c))
        {
            this.maxIndex += this.parent.size() - oldSize;
        }
        return added;
    }

    @Override
    public T set(int index, T element)
    {
        this.subRangeCheck(index);
        return this.parent.set(index + this.offset, element);
    }

    @Override
    public void add(int index, T element)
    {
        this.subRangeCheck(index);
        this.subRangeCheck(index);
        final int oldSize = this.parent.size();
        this.parent.add(index + this.offset, element);
        final int newSize = this.parent.size();
        if(newSize != oldSize)
        {
            this.maxIndex += newSize - oldSize;
        }
    }

    @Override
    public int size()
    {
        return this.maxIndex - this.offset;
    }

    @Override
    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        for (int i = this.offset; i < this.maxIndex; i++)
        {
            if (Util.equal(o, this.parent.get(i)))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new SubListIterator();
    }

    @Override
    public Object[] toArray()
    {
        return this.toArray(new Object[this.size()]);
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        if (a.length < this.maxIndex - this.offset)
        {
            a = (T[]) Array.newInstance(a.getClass(), this.maxIndex - this.offset);
        }
        T[] me = (T[]) parent.subList(this.offset, this.maxIndex).toArray();
        System.arraycopy(me, 0, a, 0, me.length);
        return a;
    }

    @Override
    public boolean remove(Object o)
    {
        for (int i = this.offset; i <= this.maxIndex; i++)
        {
            if (Util.equal(o, this.parent.get(i)))
            {
                this.parent.remove(i);
                this.maxIndex--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        for (Object o: c)
        {
            if(!this.contains(o))
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        boolean changed = false;
        for (int i = this.maxIndex; i >= this.offset; i--)
        {
            if(c.contains(this.parent.get(i)) && this.parent.remove(i) != null)
            {
                changed = true;
            }            
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        boolean changed = false;
        for (int i = this.maxIndex; i >= this.offset; i--)
        {
            if(!c.contains(this.parent.get(i)) && this.parent.remove(i) != null)
            {
                changed = true;
            }            
        }
        return changed;
    }

    @Override
    public void clear()
    {
        while(this.maxIndex > this.offset)
        {
            this.remove(this.maxIndex - this.offset - 1);
        }
        this.offset = this.maxIndex = 0;
    }

    @Override
    public T get(int index)
    {
        return this.parent.get(this.offset + index);
    }

    @Override
    public T remove(int index)
    {
        T removed = this.parent.remove((int)(this.offset + index));
        if(removed != null)
        {
            this.maxIndex--;
        }
        return removed;
    }

    @Override
    public int indexOf(Object o)
    {
        for(int i=this.offset; i<=this.maxIndex; i++)
        {
            if(Util.equal(o, this.parent.get(i)))
            {
                return i - this.offset;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        for(int i=this.maxIndex; i>=this.offset; i--)
        {
            if(Util.equal(o, this.parent.get(i)))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator()
    {
        return this.parent.subList(this.offset, this.maxIndex).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index)
    {
        return this.parent.subList(this.offset, this.maxIndex).listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex)
    {
        return this.parent.subList(this.offset + fromIndex, this.maxIndex - toIndex);
    }
    
    @Override
    public Spliterator<T> spliterator() 
    {
        return this.parent.spliterator();
    }

    @Override
    public String toString()
    {        
        if(this.isEmpty())
        {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> it = this.iterator();
        sb.append(it.next());
        while(it.hasNext())
        {
            sb.append(", ").append(it.next());
        }
        return sb.toString();
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + (this.parent != null ? this.parent.hashCode() : 0);
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
        final SubList<?> other = (SubList<?>) obj;
        return !(this.parent != other.parent && (this.parent == null || !this.parent.equals(other.parent)));
    }    
    
    public class SubListIterator<T> implements Iterator<T>
    {
        private int currentIndex = 0;

        @Override
        public boolean hasNext()
        {
            return this.currentIndex + offset < maxIndex;
        }

        @Override
        public T next()
        {
            return (T) parent.get(this.currentIndex++ + offset);
        }

        @Override
        public void remove()
        {
            parent.remove(this.currentIndex + offset - 1);
            maxIndex--;
        }
    }
    
}
