/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdb.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * A concurrent list implementation that uses a {@link ReentrantLock} for
 * synchronization.To ensure the fastest synchronization, the 
 {@link ReentrantLock} uses unfair synchronization by default (locks are not
 necessarily granted in the order they are received) and an {@link ArrayList}
 is used as the default backing {@link List} implementation. However, any 
 {@link List} can be used through the various constructors.
 * <br><br>
 * Unlike {@link Collections#synchronizedList(List)}, this implementation uses a
 * snapshot-based iterator; meaning any iteration over the list using an 
 * {@link Iterator} or the for-each (<code>for(Object o: list) </code>)construct
 * provides the contents of this {@link List} at that point in time; it is not
 * guaranteed to be an exact match for our backing {@link List}. While this 
 * allows for convenient removal of elements using the for-each construct, the
 * {@link Iterator#remove()} method is not implemented.
 * <br><br>
 * Also note that the {@link #execute(Runnable)} and {@link #execute(Supplier)}
 * methods can be used to execute code that synchronizes with all the other
 * methods in this class.
 * 
 * @author jason
 * @param <E> Elements type
 */
public class ConcurrentList<E> implements List<E>
{
    /** {@link ReentrantLock} for read/write operations on {@link #list */
    protected final ReentrantLock lock;
    
    /** The backing {@link List} for operations */
    protected final List<E> list;
    
    /**
     * Instance with an {@link ReentrantLock#ReentrantLock()} and
     * {@link ArrayList#ArrayList()} instantiation.
     */
    public ConcurrentList()
    {
        this(false, new ArrayList<>());
    }
    
    /**
     * Instance with an {@link ReentrantLock#ReentrantLock()} 
     * {@link ArrayList#ArrayList(int)} instantiation.
     * 
     * @param size Initial size of backing array in {@link ArrayList}
     */
    public ConcurrentList(int size)
    {
        this(false, size);
    }
    
    /**
     * Instance with an {@link ReentrantLock#ReentrantLock(boolean)}
     * and {@link ArrayList#ArrayList()} instantiation.
     * 
     * @param fair true for fair synchronization, false for unfair 
     * synchronization. Note that fair synchronization can significantly
     * decrease performance if numerous simultaneous operations will constantly
     * occur.
     */
    public ConcurrentList(boolean fair)
    {
        this(fair, new ArrayList<>());
    }
    
    /**
     * Instance with an {@link ReentrantLock#ReentrantLock(boolean)}
     * and {@link ArrayList#ArrayList(int)} instantiation.
     * 
     * @param fair true for fair synchronization, false for unfair 
     * synchronization. Note that fair synchronization can significantly
     * decrease performance if numerous simultaneous operations will constantly
     * occur.
     * @param size  Initial size of backing array in {@link ArrayList}
     */
    public ConcurrentList(boolean fair, int size)
    {
        this(fair, new ArrayList<>(size));
    }
    
    /**
     * Instance with an {@link ReentrantLock#ReentrantLock()} instantiation
     * used to synchronize on the passed in {@link List}
     * 
     * @param list Backing {@link List}
     */
    public ConcurrentList(List<E> list)
    {
        this(false, list);
    }
    /**
     * Instance with an {@link ReentrantLock#ReentrantLock(boolean)}
     * instantiation used to synchronize on the passed in {@link List}
     * 
     * @param fair true for fair synchronization, false for unfair 
     * synchronization. Note that fair synchronization can significantly
     * decrease performance if numerous simultaneous operations will constantly
     * occur.
     * @param list Backing {@link List}
     */
    public ConcurrentList(boolean fair, List<E> list)
    {
        this.lock = new ReentrantLock(fair);
        this.list = list;
    }
    
    /**
     * Executes the {@link Runnable} using {@link ReentrantLock#lock()} for 
     * synchronization. Useful to execute an operation you want to ensure is
     * synchronized with the current state of this {@link List}.
     * 
     * @param runnable {@link Runnable}
     */
    public void execute(Runnable runnable)
    {
        try
        {
            this.lock.lock();
            runnable.run();
        }
        finally
        {
            this.lock.unlock();
        }
    }
    
    /**
     * Executes and returns the {@link Supplier} value using
     * {@link ReentrantLock#lock()} for synchronization. Useful to execute an
     * operation you want to ensure is synchronized with the current state of
     * this {@link List}.
     * 
     * @param <T> Type to return
     * @param supplier {@link Supplier}
     * @return Return value of {@link Supplier}
     */
    public <T extends Object> T execute(Supplier<T> supplier)
    {
        try
        {
            this.lock.lock();
            return supplier.get();
        }
        finally
        {
            this.lock.unlock();
        }
    }

    @Override
    public int size()
    {
        return this.execute(()->this.list.size());
    }

    @Override
    public boolean isEmpty()
    {
        return this.execute(()->this.list.isEmpty());
    }

    @Override
    public boolean contains(Object o)
    {
        return this.execute(()->this.list.contains(o));
    }

    @Override
    public Object[] toArray()
    {
        return this.execute(()->this.list.toArray());
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return this.execute(()->this.list.toArray(a));
    }

    @Override
    public boolean add(E e)
    {
        return this.execute(()->this.list.add(e));
    }

    @Override
    public boolean remove(Object o)
    {
        return this.execute(()->this.list.remove(o));
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return this.execute(()->this.list.containsAll(c));
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        return this.execute(()->this.list.addAll(c));
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        return this.execute(()->this.list.addAll(index, c));
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return this.execute(()->this.list.removeAll(c));
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return this.execute(()->this.list.retainAll(c));
    }

    @Override
    public void clear()
    {
        this.execute(()->this.list.clear());
    }

    @Override
    public E get(int index)
    {
        return this.execute(()->this.list.get(index));
    }

    @Override
    public E set(int index, E element)
    {
        return this.execute(()->this.list.set(index, element));
    }

    @Override
    public void add(int index, E element)
    {
        this.execute(()->this.list.add(index, element));
    }

    @Override
    public E remove(int index)
    {
        return this.execute(()->this.list.remove(index));
    }

    @Override
    public int indexOf(Object o)
    {
        return this.execute(()->this.list.indexOf(o));
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return this.execute(()->this.list.lastIndexOf(o));
    }
    
    /**
     * {@inheritDoc}<br><br>
     * This is a read-only snapshot of the current elements in this 
     * {@link List}; the {@link Iterator#remove()} method throws a 
     * {@link UnsupportedOperationException}.
     * 
     * @return {@link Iterator}
     */
    @Override
    public Iterator<E> iterator()
    {
        return new ConcurrentListIterator(0);
    }

    /**
     * {@inheritDoc}<br><br>
     * This is a read-only snapshot of the current elements in this 
     * {@link List}; the {@link ListIterator#remove()}, 
     * {@link ListIterator#add(Object)} and {@link ListIterator#set(Object)}
     * methods throw a {@link UnsupportedOperationException}.
     * 
     * @return {@link ListIterator}
     */
    @Override
    public ListIterator<E> listIterator()
    {
        return new ConcurrentListIterator(0);
    }

    /**
     * {@inheritDoc}<br><br>
     * This is a read-only snapshot of the current elements in this 
     * {@link List}; the {@link ListIterator#remove()}, 
     * {@link ListIterator#add(Object)} and {@link ListIterator#set(Object)}
     * methods throw a {@link UnsupportedOperationException}.
     * 
     * @return {@link ListIterator}
     */
    @Override
    public ListIterator<E> listIterator(int index)
    {
        return this.execute(()->new ArrayList<>(this.list).listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        return new SubList(this, fromIndex, toIndex);
    }
    
    /**
     * {@link ListIterator} implementation that provides a snapshot of the
     * current state of the {@link List}; Methods {@link ListIterator#remove()}, 
     * {@link ListIterator#add(Object)} and {@link ListIterator#set(Object)}
     * are not implemented.
     * 
     * @param <T> Type of elements
     */
    protected class ConcurrentListIterator<T extends E> implements ListIterator<T>
    {
        /** Backing array snapshot */
        protected final T[] array;
        
        /** Index of current element */
        protected int index = -1;
        
        public ConcurrentListIterator(int index)
        {
            final int size = size();
            if(index < 0 || index > size)
            {
                throw new IndexOutOfBoundsException(index);
            }
            else if(index == 0)
            {
                this.array = (T[]) toArray();
            }
            else
            {
                this.array = (T[]) new SubList(ConcurrentList.this, index, size()).toArray();
            }
        }
        
        @Override
        public boolean hasNext()
        {
            return this.index + 1 < this.array.length;
        }

        @Override
        public T next()
        {
            if(this.hasNext())
            {
                return this.array[++this.index];
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious()
        {
            return index > 0;
        }

        @Override
        public T previous()
        {
            if(this.hasPrevious())
            {
                return this.array[--this.index];
            }
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex()
        {
            return Math.min(this.index + 1, this.array.length);
        }

        @Override
        public int previousIndex()
        {
            return Math.max(index - 1, -1);
        }

        /**
         * Not implemented; will throw {@link UnsupportedOperationException}!
         * @deprecated
         */
        @Deprecated
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported!");
        }

        /**
         * Not implemented; will throw {@link UnsupportedOperationException}!
         * @deprecated
         */
        @Deprecated
        @Override
        public void set(T e)
        {
            throw new UnsupportedOperationException("Not supported!");
        }

        /**
         * Not implemented; will throw {@link UnsupportedOperationException}!
         * @deprecated
         */
        @Deprecated
        @Override
        public void add(T e)
        {
            throw new UnsupportedOperationException("Not supported!");
        }
        
    }
    
}
