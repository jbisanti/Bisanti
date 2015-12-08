/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.bisanti.util.Util;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * A {@link Collection} that implements both the {@link List} and {@link Set}
 * interfaces. This {@link Collection} guarantees that elements will be ordered
 * in the order they were added and that there will be no more than one 
 * occurrence of each element.
 * <br><br>
 * A {@link List} is used as the backing data structure and calls are made to
 * {@link contains(Object)} to ensure no duplicate elements are added. By 
 * default, an {@link ArrayList} is used as the backing data structure. If you
 * want to use a different {@link List} implementation as the backing data
 * structure, use constructor {@link SetList(List)}.
 *
 * @author Jason Bisanti
 */
public class SetList<T> extends AbstractSetList<T>
{    
    /**
     * Instantiates a new instance with an {@link ArrayList} as the backing
     * data structure using the {@link ArrayList} default constructor.
     */
    public SetList()
    {
        this(new ArrayList<T>());
    }
    
    /**
     * Instantiates a new instance with an {@link ArrayList} as the backing
     * data structure using the <code>new ArrayList(int)</code> constructor.
     * 
     * @param initialCapacity Initial capacity of {@link ArrayList} 
     */
    public SetList(int initialCapacity)
    {
        this(new ArrayList<T>(initialCapacity));
    }
    
    /**
     * Instantiates a new instance with an {@link ArrayList} as the backing
     * data structure. The {@link ArrayList} is not instantiated as <code>
     * new ArrayList(c)</code>, but rather as <code>new ArrayList(c.size())
     * </code>. Each element from the passed in {@link Collection} is then added
     * assuming it is not already contained in our {@link ArrayList}.
     * 
     * @param c {@link Collection}; any duplicate elements will only be added
     * once to this {@link List}.
     */
    public SetList(Collection<? extends T> c)
    {
        this(c.size());
        this.addAll(c);
    }
    
    /**
     * Sets the {@link List} implementation to be used as the backing data
     * structure.
     * 
     * @param list {@link List}
     */
    public SetList(List<T> list)
    {
        this.list = list;
    }

    /**
     * Adds this element to the set <b>if not</b> already contained.
     * 
     * @param e element to add
     * @return true if added, false if not
     */
    @Override
    public boolean add(T e)
    {
        return this.list.contains(e) ? false : this.list.add(e);
    }

    /**
     * Returns true if <b>all</b> elements are contained in this {@link List}.
     * Bear in mind that any duplicate elements from the passed in
     * {@link Collection} will guarantee a false return.
     * 
     * @param c {@link Collection}
     * @return true if all elements are contained, false if not.
     */
    @Override
    public boolean containsAll(Collection<?> c)
    {
        return this.list.containsAll(c);
    }

    /**
     * Potentially adds all elements from the passed in {@link Collection} to 
     * this list. If any element is already contained or duplicate elements are
     * present in the {@link Collection}, they will be skipped and not be added.
     * 
     * @param c {@link Collection} 
     * @return true if one or more elements were added, false if not
     */
    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        return this.addAll(this.size(), c);
    }

    /**
     * Potentially adds all elements from the passed in {@link Collection} to 
     * this {@link List} starting at the given index. Existing elements past
     * this index will have their indices adjusted. If any element is already 
     * contained or duplicate elements are present in the {@link Collection}, 
     * they will be skipped and not be added.
     * 
     * @param index 
     * @param c {@link Collection} 
     * @return true if one or more elements were added, false if not
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
        this.rangeCheck(index);
        
        final int oldSize = this.size();
        
        for(T element: c)
        {
            if(!this.contains(element))
            {
                if(index == this.size())
                {
                    this.list.add(element);
                }
                else
                {
                    this.list.add(index, element);
                }
                
                index++;
            }
        }
        
        return oldSize != this.size();
    }

    /**
     * If the given element is not already in this {@link List}, it will replace
     * the current element at the given index.
     * 
     * @param index index to insert element
     * @param element element to insert.
     * @return Previous element at index or null if the element is already in
     * this {@link List}.
     */
    @Override
    public T set(int index, T element)
    {
        if(this.contains(element))
        {
            return null;
        }
        
        return this.list.set(index, element);
    }

    /**
     * If the given element is not already in this {@link List}, it will be 
     * added at the given index and all elements past this index will have their
     * indices adjusted accordingly.
     * 
     * @param index index to insert element
     * @param element element to add
     */
    @Override
    public void add(int index, T element)
    {
        this.rangeCheck(index);
        
        if(!this.contains(element))
        {        
            if(index == this.size())
            {
                this.list.add(element);
            }
            else
            {
                this.list.add(index, element);
            }
        }
    }
    
    private class SubList<T> implements List<T>, Set<T>
    {
        private SetList<T> parent;
        private int offset;
        private int maxIndex;
        
        private SubList(SetList<T> parent, int fromIndex, int toIndex)
        {
            rangeCheck(fromIndex);
            rangeCheck(toIndex);
            this.parent = parent;
            this.offset = fromIndex;
            this.maxIndex = toIndex + this.offset;
        }
        
        private void subRangeCheck(int index)
        {
            if(index + this.offset > this.parent.size())
            {
                throw new IndexOutOfBoundsException("Index value is too large");
            }
            else if(index < 0)
            {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public boolean add(T e)
        {
            final int oldSize = this.parent.size();
            this.parent.add(maxIndex, e);
            if(oldSize != this.parent.size())
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
           for(T element: c)
           {
               if(this.add(element))
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
                if(existingIndex >= this.offset && existingIndex <= this.maxIndex)
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
            for(int i=this.offset; i<this.maxIndex; i++)
            {
                if(Util.equal(o, this.parent.get(i)))
                {
                    return true;
                }
            }
            
            return false;
        }

        @Override
        public Iterator<T> iterator()
        {
            ListIterator<T> it = this.parent.listIterator(this.offset);
            while(it.hasNext())
            {
                it.next();
                if(it.nextIndex() > this.offset)
                {
                    it.remove();
                }
            }
            
            return it;
        }

        @Override
        public Object[] toArray()
        {
            Object[] array = new Object[this.maxIndex - this.offset];
            for(int i=this.offset; i<this.maxIndex; i++)
            {
                array[i] = this.parent.get(i);
            }
            return array;
        }

        @Override
        public <T> T[] toArray(T[] a)
        {
            if(a.length < this.maxIndex - this.offset)
            {
                a = (T[]) Array.newInstance(a.getClass(), this.maxIndex-this.offset);
            }
            
            for(int i=this.offset; i<this.maxIndex; i++)
            {
                a[i] = (T) this.parent.get(i);
            }
            
            return a;
        }

        @Override
        public boolean remove(Object o)
        {
            for(int i=this.offset; i<=this.maxIndex; i++)
            {
                if(Util.equal(o, this.parent.get(i)))
                {
                    this.parent.remove(i);
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c)
        {
            for(int i=this.offset; i<this.maxIndex; i++)
            {
                
            }
            
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean retainAll(Collection<?> c)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void clear()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public T get(int index)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public T remove(int index)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int indexOf(Object o)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int lastIndexOf(Object o)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ListIterator<T> listIterator()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ListIterator<T> listIterator(int index)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<T> subList(int fromIndex, int toIndex)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
