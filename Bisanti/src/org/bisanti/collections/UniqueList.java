/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * 
 * A {@link ListSet} implementation where calls are made to
 * {@link contains(Object)} to ensure no duplicate elements are added. By 
 * default, an {@link ArrayList} is used as the backing data structure. If you
 * want to use a different {@link List} implementation as the backing data
 * structure, use constructor {@link #UniqueList(java.util.List)}.
 *
 * @author Jason Bisanti
 */
public class UniqueList<T> extends AbstractListSet<T>
{    
    /**
     * Instantiates a new instance with an {@link ArrayList} as the backing
     * data structure using the {@link ArrayList} default constructor.
     */
    public UniqueList()
    {
        super(new ArrayList<T>());
    }
    
    /**
     * Instantiates a new instance with an {@link ArrayList} as the backing
     * data structure using the <code>new ArrayList(int)</code> constructor.
     * 
     * @param initialCapacity Initial capacity of {@link ArrayList} 
     */
    public UniqueList(int initialCapacity)
    {
        super(initialCapacity);
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
    public UniqueList(Collection<? extends T> c)
    {
        super(c);
    }
    
    /**
     * Sets the {@link List} implementation to be used as the backing data
     * structure.
     * 
     * @param list {@link List}
     */
    public UniqueList(List<T> list)
    {
        super(list);
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
    
}
