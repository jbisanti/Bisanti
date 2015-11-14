/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.util.ArrayList;
import java.util.Collection;
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
        this(list);
        this.comparator = comparator;
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
        else if(this.compare(this.first(), e) < 0)
        {
            this.list.add(0, e);
            return true;
        }
        else if(this.compare(this.last(), e) > 0)
        {
            return this.list.add(e);
        }
        
        for(int i=1; i<this.size()-1; i++)
        {
            int compare = this.compare(this.get(i), e);
            
            if(compare == 0)
            {
                return false;
            }
            else if(compare < 0)
            {
                this.list.add(i, e);
                return true;
            }            
        }        
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        return this.addAll(0, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        final int oldSize = super.size();
        Iterator<? extends T> others = null;
        
        if(super.isEmpty())
        {
            if(c.isEmpty())
            {
                return false;
            }
            else if(c.size() == 1)
            {
                return this.list.add(c.iterator().next());
            }
            else
            {
                others = c.iterator();
                this.list.add(others.next());
            }
        }
        else
        {
            others = c.iterator();
        }
        
        while(others.hasNext())
        {
            T next = others.next();
            List<T> subList = super.subList(index, super.size());
            for(int i=1; i<subList.size()-1; i++)
            {
                if(this.compare(subList.get(i), next) == 0)
                {
                    break;
                }
                else if(this.compare(subList.get(i-1), next) > 0 &&
                        this.compare(next, subList.get(i+1)) < 0)
                {
                    this.list.add(index++, next);
                    break;
                }
            }
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
            if(this.compare(element, this.get(1)) < 0)
            {
                valid = true;
            }
        }
        else if(index == super.size()-1)
        {
            if(this.compare(this.get(super.size()-1), element) > 0)
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
        
        if(valid)
        {
            this.list.set(index, element);
        }
        
        return null;
    }

    @Override
    public void add(int index, T element)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T lower(T e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T floor(T e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T ceiling(T e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T higher(T e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T pollFirst()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T pollLast()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NavigableSet<T> descendingSet()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> descendingIterator()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SortedSet<T> headSet(T toElement)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SortedSet<T> tailSet(T fromElement)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
