/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 *
 * @author Jason Bisanti
 */
public class ListMap<K, V> implements Map<K, V>
{
    private List<K> keys;
    
    private List<V> values;
    
    public ListMap()
    {
        this(new ArrayList<K>(), new ArrayList<V>());
    }
    
    public ListMap(List<K> keys, List<V> values)
    {
        this.keys = keys;
        this.values = values;
    }
    
    public ListMap(Map<? extends K, ? extends V> map)
    {
        this(map.size());
        this.putAll(map);
    }
    
    public ListMap(int initialCapacity)
    {
        this(new ArrayList<K>(initialCapacity), new ArrayList<V>(initialCapacity));
    }
    
    private void rangeCheck(int index)
    {
        if(index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException();
        }
    }
    
    public Entry<K, V> getEntry(int index)
    {
        this.rangeCheck(index);
        return new EntryImpl(index);
    }
    
    public Entry<K, V> removeEntry(int index)
    {
        this.rangeCheck(index);
        final K key = this.keys.remove(index);
        final V value = this.values.remove(index);
        return new Entry<K, V>()
        {
            @Override
            public K getKey()
            {
                return key;
            }

            @Override
            public V getValue()
            {
                return value;
            }

            @Override
            public V setValue(V value)
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public int size()
    {
        return this.keys.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.keys.isEmpty();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return this.keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return this.values.contains(value);
    }

    @Override
    public V get(Object key)
    {
        int index = this.keys.indexOf(key);
        return index < 0 ? null : this.values.get(index);
    }

    @Override
    public V put(K key, V value)
    {
        int index = this.keys.indexOf(key);
        if(index < 0)
        {
            this.keys.add(key);
            this.values.add(value);
            return null;
        }
        else
        {
            return this.values.set(index, value);
        }
    }

    @Override
    public V remove(Object key)
    {
        int index = this.keys.indexOf(key);
        if(index < 0)
        {
            return null;
        }
        this.keys.remove(index);
        return this.values.remove(index);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for(Entry<? extends K, ? extends V> entry: m.entrySet())
        {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear()
    {
        this.keys.clear();
        this.values.clear();
    }

    @Override
    public Set<K> keySet()
    {
        return new SetList<K>(this.keys);
    }

    @Override
    public Collection<V> values()
    {
        return new ArrayList<V>(this.values);
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        Set<Entry<K, V>> entries = new SetList<Entry<K, V>>(this.size());
        for(int i=0; i<this.size(); i++)
        {
            entries.add(new EntryImpl(i));
        }        
        return entries;
    }
    
    private class EntryImpl implements Entry<K, V>
    {
        private final int index;
        
        private EntryImpl(int index)
        {
            this.index = index;
        }

        @Override
        public K getKey()
        {
            return keys.get(this.index);
        }

        @Override
        public V getValue()
        {
            return values.get(this.index);
        }

        @Override
        public V setValue(V value)
        {
            return values.set(this.index, value);
        }        
    }

}
