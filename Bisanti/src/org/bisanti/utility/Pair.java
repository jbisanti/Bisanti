package org.bisanti.utility;

import java.util.Map;

/**
 * <i>
 * Written and authored by Jason Bisanti.Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * @author Jason Bisanti
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> implements Map.Entry<K, V>
{
    protected K key;
    
    protected V value;
    
    public Pair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
    
    public void setKey(K key)
    {
        this.key = key;
    }
    
    @Override
    public K getKey()
    {
        return this.key;
    }
    
    @Override
    public V setValue(V value) 
    {
        if(Util.equal(this.value, value))
        {
            return this.value;
        }
        V old = this.value;
        this.value = value;
        return old;
    }
    
    @Override
    public V getValue()
    {
        return this.value;
    }

    /**
     * Use {@link #getKey()}
     * @return 
     * @deprecated 
     */
    @Deprecated
    public K getFirst()
    {
        return this.getKey();
    }

    /**
     * Use {@link #getValue()}
     * @return 
     * @deprecated 
     */
    @Deprecated
    public V getSecond()
    {
        return this.getValue();
    }
    
}
