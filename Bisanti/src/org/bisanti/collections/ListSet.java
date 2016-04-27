/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.collections;

import java.util.List;
import java.util.Set;


/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 * <br><br>
 * A {@link Collection} that implements both the {@link List} and {@link Set}
 * interfaces. This {@link Collection} guarantees that elements will be ordered
 * in the order they were added and that there will be no more than one 
 * occurrence of each element.
 * 
 * @author Jason Bisanti
 * @param <T>
 */
public interface ListSet<T> extends List<T>, Set<T>{}
