/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.util;

import java.util.Map.Entry;
import java.util.Set;

/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 *
 * @author Jason Bisanti
 */
public class Demo 
{
    public static void main(String[] args)
    {
        Set<Entry<Object, Object>> set = System.getProperties().entrySet();
        for(Entry<Object, Object> e: set)
        {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

}
