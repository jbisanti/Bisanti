/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdb.collections;

import java.util.List;
import java.util.Set;
import java.util.Spliterator;


/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 * <br><br>
 * A {@link Collection} that implements both the {@link List} and {@link Set}
 * interfaces. Implementing classes should guarantee that elements are ordered
 * in the order they were added and that there will be no more than one 
 * occurrence of each element.
 * 
 * @author Jason Bisanti
 * @param <T>
 * @see UniqueList
 * @see HashList
 */
public interface ListSet<T> extends List<T>, Set<T>
{
    @Override
    Spliterator<T> spliterator();    
}
