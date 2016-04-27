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
 * A {@link ListMap} implementation that uses 2 separate {@link List}s to hold
 * the keys and values. By default, {@link ArrayList} is used as the default
 * for the backing {@link List}s, though constructor 
 * {@link #DualListMap(java.util.List, java.util.List)} can be used to specify
 * which {@link List} implementation to use.
 *
 * @author Jason Bisanti
 * @param <K>
 * @param <V>
 */
public class DualListMap<K, V> implements ListMap<K, V>
{
    private List<K> keys;
    
    private List<V> values;
    
    public DualListMap()
    {
        this(new ArrayList<K>(), new ArrayList<V>());
    }
    
    public DualListMap(List<K> keys, List<V> values)
    {
        if(keys == null || values == null)
        {
            throw new NullPointerException("Parameters 'key' and 'values' cannot be null");
        }
        else if(keys.size() != values.size())
        {
            throw new RuntimeException("Keys and values Lists must be the same size");
        }
        this.keys = keys;
        this.values = values;
    }
    
    public DualListMap(Map<? extends K, ? extends V> map)
    {
        this(map.size());
        this.putAll(map);
    }
    
    public DualListMap(int initialCapacity)
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
        return new UniqueList<K>(this.keys);
    }

    @Override
    public Collection<V> values()
    {
        return new ArrayList<V>(this.values);
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        Set<Entry<K, V>> entries = new UniqueList<Entry<K, V>>(this.size());
        for(int i=0; i<this.size(); i++)
        {
            entries.add(new EntryImpl(i));
        }        
        return entries;
    }

    @Override
    public Entry<K, V> get(int index)
    {
        return new EntryImpl(index);
    }

    @Override
    public Entry<K, V> remove(int index)
    {
        return new EntryImpl(index);
    }

    @Override
    public Entry<K, V> set(int index, K key, V value)
    {
        if(this.containsKey(key))
        {
            return null;
        }
        
        return new EntryOutdated(this.keys.set(index, key), this.values.set(index, value));
    }

    @Override
    public boolean add(int index, K key, V value)
    {
        if(this.containsKey(key))
        {
            return false;
        }
        
        this.keys.add(index, key);
        this.values.add(index, value);
        return true;
    }

    @Override
    public int indexOf(K key)
    {
        return this.keys.indexOf(key);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        for(int i=0; i<this.size(); i++)
        {
            hash = 97 * hash + this.keys.get(i).hashCode();
            hash = 97 * hash + this.values.get(i).hashCode();
        }
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
        final DualListMap<?, ?> other = (DualListMap<?, ?>) obj;
        if (this.keys != other.keys && (this.keys == null || !this.keys.equals(other.keys)))
        {
            return false;
        }
        if (this.values != other.values && (this.values == null || !this.values.equals(other.values)))
        {
            return false;
        }
        return true;
    }

    /**
     * {@link Entry} implementation that provides a live reference to our
     * {@link ListMap}. That is, the {@link #setValue(java.lang.Object)} method
     * will change the value in our {@link ListMap} referent.
     */
    private class EntryImpl implements Entry<K, V>
    {
        /** Index our {@link Entry} refers to */
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

        @Override
        public int hashCode()
        {
            int hash = 5;
            hash = 61 * hash + keys.get(index).hashCode();
            hash = 61 * hash + values.get(index).hashCode();
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
            final EntryImpl other = (EntryImpl) obj;
            K key = this.getKey();
            if (key == null ? other.getKey() != null : !key.equals(other.getKey()))
            {
                return false;
            }
            V value = this.getValue();
            if(value == null ? other.getValue() != null : !value.equals(other.getValue()))
            {
                return false;
            }
            return true;
        }
    }
    
    /**
     * Returns a stale {@link Entry}. That is, this reference is not guaranteed
     * to be in sync with our {@link ListMap} referent and the
     * {@link #setValue(java.lang.Object)} method throws an 
     * {@link UnsupportedOperationException}.
     */
    private class EntryOutdated implements Entry<K, V>
    {
        private final K key;
        
        private final V value;
        
        private EntryOutdated(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey()
        {
            return this.key;
        }

        @Override
        public V getValue()
        {
            return this.value;
        }

        @Override
        public V setValue(V value)
        {
            throw new UnsupportedOperationException("Entry reference is no longer valid");
        }

        @Override
        public int hashCode()
        {
            int hash = 7;
            hash = 29 * hash + (this.key != null ? this.key.hashCode() : 0);
            hash = 29 * hash + (this.value != null ? this.value.hashCode() : 0);
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
            final EntryOutdated other = (EntryOutdated) obj;
            if (this.key != other.key && (this.key == null || !this.key.equals(other.key)))
            {
                return false;
            }
            if (this.value != other.value && (this.value == null || !this.value.equals(other.value)))
            {
                return false;
            }
            return true;
        }
    }

}
