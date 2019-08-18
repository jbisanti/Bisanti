/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.collections;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import org.bisanti.util.Util;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * A {@link ListSet} implementation meant to be a sublist reference for any
 * other {@link ListSet} implementation. Modifications to this instance will 
 * also be reflected to the {@link ListSet} it references.
 * 
 * @author Jason Bisanti
 */
public class SubSetList<T> implements ListSet<T>
{
    private final ListSet<T> parent;
    int offset;
    int maxIndex;

    public SubSetList(ListSet<T> parent, int fromIndex, int toIndex)
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
        if (index + this.offset > this.parent.size())
        {
            throw new IndexOutOfBoundsException("Index value is too large");
        } 
        else if (index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean add(T e)
    {
        final int oldSize = this.parent.size();
        this.parent.add(maxIndex, e);
        if (oldSize != this.parent.size())
        {
            maxIndex++;
            return true;
        } 
        else
        {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        boolean changed = false;
        for (T element : c)
        {
            if (this.add(element))
            {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        this.subRangeCheck(index);
        final int oldSize = this.parent.size();
        for (T element : c)
        {
            int existingIndex = this.parent.indexOf(element);
            if (existingIndex >= this.offset && existingIndex <= this.maxIndex)
            {
                this.parent.add(index++, element);
                this.maxIndex++;
            }
        }
        return oldSize != this.parent.size();
    }

    @Override
    public T set(int index, T element)
    {
        this.subRangeCheck(index);
        return this.parent.set(index, element);
    }

    @Override
    public void add(int index, T element)
    {
        this.subRangeCheck(index);
        this.parent.add(index, element);
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
        return new Iterator<T>()
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
                return parent.get(this.currentIndex++ + offset);
            }

            @Override
            public void remove()
            {
                parent.remove(this.currentIndex);
                maxIndex--;
            }
        };
    }

    @Override
    public Object[] toArray()
    {
        Object[] array = new Object[this.maxIndex - this.offset];
        for (int i = this.offset; i < this.maxIndex; i++)
        {
            array[i] = this.parent.get(i);
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        if (a.length < this.maxIndex - this.offset)
        {
            a = (T[]) Array.newInstance(a.getClass(), this.maxIndex - this.offset);
        }

        for (int i = this.offset; i < this.maxIndex; i++)
        {
            a[i] = (T) this.parent.get(i);
        }

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
        final int oldSize = this.size();
        for(Object o: c)
        {
            this.remove(o);
        }
        return oldSize != this.size();
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear()
    {
        this.parent.subList(this.offset, this.maxIndex).clear();
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
        T removed = this.parent.remove(this.offset + index);
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
        final SubSetList<?> other = (SubSetList<?>) obj;
        return !(this.parent != other.parent && (this.parent == null || !this.parent.equals(other.parent)));
    }    
    
}
