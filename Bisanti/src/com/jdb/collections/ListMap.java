/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdb.collections;

import java.util.Map;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * 
 * An extension of the {@link Map} interface that keeps keys in the order that
 * they were added. Methods that allow retrievals, additions, removals, and 
 * indexes are included.
 *
 * @author Jason Bisanti
 * @param <T>
 * @param <V>
 */
public interface ListMap<T, V> extends Map<T, V>
{
    /**
     * Returns the {@link Entry} at the given index.
     * 
     * @param index Index to retrieve
     * @return {@link Entry}
     */
    public Entry<T, V> get(int index);
    
    /**
     * Removes the {@link Entry} at the given index.
     * 
     * @param index Index to remove
     * @return {@link Entry}
     */
    public Entry<T, V> remove(int index);
    
    /**
     * Sets the key and value at the given index if the key parameter is not
     * already present.
     * 
     * @param index Index to set key and value
     * @param key Key to set at index
     * @param value Value to set at index
     * @return {@link Entry} or null if parameter key is already present in this
     * {@link ListMap}
     */
    public Entry<T, V> set(int index, T key, V value);
    
    /**
     * Adds the key and value at the given index if the key parameter is not
     * already present.
     * 
     * @param index Index to add key and value
     * @param key Key to set at index
     * @param value Value to set at index
     * @return true if the key and value were set or false if parameter key is
     * already present in this {@link ListMap} 
     */
    public boolean add(int index, T key, V value);
    
    /**
     * Returns this index of parameter key.
     * 
     * @param key Key to retrieve index of
     * @return index or -1 if key is not present in this {@link ListMap}
     */
    public int indexOfKey(T key);   
    
    public int indexOfValue(V value);

}
