/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.SortedSet;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 *
 * @author Jason Bisanti
 * @param <T>
 */
public class TreeList<T extends Comparable> extends AbstractSetList<T> implements NavigableSet<T>
{
    private Comparator<T> comparator;

    public TreeList()
    {
        super();
    }

    public TreeList(int initialCapacity)
    {
        super(initialCapacity);
    }

    public TreeList(Collection<? extends T> c)
    {
        super(c);
    }

    public TreeList(List<T> list)
    {
        super(list);
    }
    
    public TreeList(Comparator<T> comparator)
    {
        this();
        this.comparator = comparator;
    }
    
    public TreeList(Comparator<T> comparator, int initialCapacity)
    {
        this(initialCapacity);
        this.comparator = comparator;
    }
    
    public TreeList(Comparator<T> comparator, Collection<? extends T> c)
    {
        this(c);
        this.comparator = comparator;
    }
    
    public TreeList(Comparator<T> comparator, List<T> list)
    {
        this(list.size());
        this.comparator = comparator;
        for(T element: list)
        {
            this.add(element);
        }
    }
    
    private int compare(T element1, T element2)
    {
        if(this.comparator == null)
        {
            return element1.compareTo(element2);
        }
        
        return this.comparator.compare(element1, element2);
    }    

    @Override
    public boolean add(T e)
    {
        if(this.isEmpty())
        {
            return this.list.add(e);
        }
        
        for(int i=0; i<super.size(); i++)
        {
            int compare = this.compare(e, super.get(i));
            if(compare < 0)
            {
                super.list.add(i, e);
                return true;
            }
            else if(compare == 0)
            {
                return false;
            }
        }
        
        return super.list.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        final int oldSize = super.size();
        
        for(T element: c)
        {
            this.add(element);
        }
        
        return oldSize != super.size();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        final int oldSize = super.size();
        
        for(T element: c)
        {
            this.add(index, element);
        }
        
        return oldSize != super.size();
    }

    @Override
    public T set(int index, T element)
    {
        super.rangeCheck(index);
        
        boolean valid = false;
        if(index == 0)
        {
            if(this.compare(element, this.first()) < 0)
            {
                valid = true;
            }
        }
        else if(index == super.size()-1)
        {
            if(this.compare(this.last(), element) > 0)
            {
                valid = true;
            }
        }
        else
        {
            if(this.compare(this.get(index-1), element) > 0 &&
                    this.compare(element, this.get(index+1)) < 0)
            {
                valid = true;
            }
        }
        
        return valid ? this.list.set(index, element) : null;
    }

    @Override
    public void add(int index, T element)
    {
        super.rangeCheck(index);
        
        if(index == 0)
        {
            if(super.isEmpty() || this.compare(element, this.first()) < 0)
            {
                super.list.add(0, element);
            }
        }
        else if(index == super.size())
        {
            if(this.compare(element, this.last()) > 0)
            {
                super.list.add(element);
            }
        }
        else
        {
            int compare1 = this.compare(element, super.get(index - 1));
            int compare2 = this.compare(element, super.get(index));
            if (compare1 > 0 && compare2 < 0)
            {
                super.list.add(index++, element);
            }     
        }
    }

    @Override
    public T lower(T e)
    {        
        int index = super.indexOf(e);
        return index <= 0 ? null : this.get(index-1);
    }

    @Override
    public T floor(T e)
    {
        int index = super.indexOf(e);
        if(index > -1)
        {
            return this.get(index);
        }
        else
        {
            ListIterator<T> it = this.listIterator(super.size());
            while(it.hasPrevious())
            {
                T current = it.previous();
                if(current.compareTo(e) <= 0)
                {
                    return current;
                }
            }
            return null;            
        }
        
    }

    @Override
    public T ceiling(T e)
    {
        int index = super.indexOf(e);
        if(index > -1)
        {
            return this.get(index);
        }
        else
        {
            Iterator<T> it = this.iterator();
            while(it.hasNext())
            {
                T current = it.next();
                if(current.compareTo(e) >= 0)
                {
                    return current;
                }
            }
            return null;            
        }
    }

    @Override
    public T higher(T e)
    {
        Iterator<T> it = this.iterator();
        while (it.hasNext())
        {
            T current = it.next();
            if (current.compareTo(e) > 0)
            {
                return current;
            }
        }
        return null;
    }

    @Override
    public T pollFirst()
    {
        return super.isEmpty() ? null : super.remove(0);
    }

    @Override
    public T pollLast()
    {
        return super.isEmpty() ? null : super.remove(super.size()-1);
    }

    @Override
    public NavigableSet<T> descendingSet()
    {
        return new TreeList<T>(Collections.reverseOrder(this.comparator), super.list);
    }

    @Override
    public Iterator<T> descendingIterator()
    {
        return this.descendingSet().iterator();
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive)
    {
        int startIndex = this.indexOf(fromElement);
        if(!fromInclusive)
        {
            startIndex++;
        }
        int endIndex = this.indexOf(toElement);
        if(!toInclusive)
        {
            endIndex--;
        }

        return new TreeList<T>(this.comparator, this.subList(startIndex, endIndex+1));
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive)
    {
        int index = super.indexOf(toElement);
        if(index < 0)
        {
            throw new IllegalArgumentException("Set does not contain 'fromElement'");
        }
        
        NavigableSet<T> headSet = new TreeList<T>(this.comparator, index);
        ListIterator<T> it = super.listIterator(index);
        if(inclusive && it.hasNext())
        {
            it.next();
        }
        
        while(it.hasPrevious())
        {
            headSet.add(it.previous());
        }
        
        return headSet;
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive)
    {
        int index = super.indexOf(fromElement);
        if(index < 0)
        {
            throw new IllegalArgumentException("Set does not contain 'fromElement'");
        }
        
        NavigableSet<T> headSet = new TreeList<T>(this.comparator, index);
        ListIterator<T> it = super.listIterator(index);
        if(!inclusive && it.hasNext())
        {
            it.next();
        }
        
        while(it.hasNext())
        {
            headSet.add(it.next());
        }
        
        return headSet;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement)
    {
        int start = super.indexOf(fromElement);
        if(start < 0)
        {
            throw new IllegalArgumentException("Set does not contain 'fromElement'");
        }
        
        int end = super.indexOf(toElement);
        if(end < 0)
        {
            throw new IllegalArgumentException("Set does not contain 'toElement'");
        }
        
        if(end <= start)
        {
            throw new IllegalArgumentException("'fromElement' is located after 'toElement'");
        }
        
        return new TreeList<T>(this.comparator, super.subList(start, end));
    }

    @Override
    public SortedSet<T> headSet(T toElement)
    {
        return this.headSet(toElement, false);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement)
    {
        return this.tailSet(fromElement, false);
    }

    @Override
    public Comparator<? super T> comparator()
    {
        return this.comparator;
    }

    @Override
    public T first()
    {
        return this.isEmpty() ? null : this.list.get(0);
    }

    @Override
    public T last()
    {
        return this.isEmpty() ? null : this.list.get(super.size()-1);
    }
    
}
