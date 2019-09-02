/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.utility;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import static org.bisanti.utility.Util.containsNull;

/**
 *
 * @author jason
 */
public class MapUtil 
{
    private MapUtil(){}
    
    /**
     * Determines if the {@link Map} is null or empty.
     * @param map Any {@link Map}
     * @return true if the {@link Map} is null or <code>isEmpty()</code>
     * returns true, false otherwise
     */
    public static boolean isNullOrEmpty(final Map map)
    {
        return map == null || map.isEmpty();
    }
    
    /**
     * Performs a null-safe equality check to determine if the {@link Map} 
     * parameters are equal based on the key-value pairs.
     * @param considerOrder true if order of elements is necessary for equality
     * @param map1 Any {@link Map}
     * @param map2 Any {@link Map}
     * @return if the {@link Map}s are both the same size and one {@link Map}
     * contains all the keys-value pairs of the other {@link Map}
     */
    public static boolean equal(final boolean considerOrder, final Map map1, final Map map2)
    {
        if(containsNull(map1, map2))
        {
            return map1 == map2;
        }
        else if(considerOrder)
        {
            if(map1.size() == map2.size())
            {
                Iterator<Map.Entry> it1 = map1.entrySet().iterator();
                Iterator<Map.Entry> it2 = map2.entrySet().iterator();
                while(it1.hasNext())
                {
                    Map.Entry entry1 = it1.next();
                    Map.Entry entry2 = it2.next();
                    if(!Util.equal(entry1.getKey(), entry2.getKey()) ||
                       !Util.equal(entry1.getValue(), entry2.getValue()))
                    {
                        return false;
                    }
                }
                
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            Set<Entry> entries = map1.entrySet();
            for(Entry entry: entries)
            {
                if(!Util.equal(entry.getValue(), map2.get(entry.getKey())))
                {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    public static Set<Entry<?, ?>> removeAll(boolean keyValuesMatch, 
            Map<?, ?> container, Map<?, ?> toRemove)
    {
        Set<Entry<?, ?>> entries = new LinkedHashSet<>();
        for(Entry<?, ?> entry: toRemove.entrySet())
        {
            final Object key = entry.getKey();
            if(keyValuesMatch)
            {              
                final Object value = container.get(key);
                if(Util.equal(value, key))
                {                    
                    entries.add(new Pair<>(key, container.remove(key)));
                }
            }
            else
            {
                Object removed = container.remove(key);
                if(removed != null)
                {
                    entries.add(new Pair<>(key, removed));
                }
            }
        }
        return entries;
    }
    
}
