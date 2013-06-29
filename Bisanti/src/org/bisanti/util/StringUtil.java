/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 * 
 * @author Jason Bisanti
 */
public final class StringUtil
{
    private StringUtil(){};
    
    public static boolean isNullOrEmpty(String s)
    {
        return s == null || s.isEmpty();
    }
    
    public static StringBuilder toString(Collection c, String delimiter)
    {
        StringBuilder builder = new StringBuilder();
        if(!Util.isNullOrEmpty(c))
        {
            Iterator it = c.iterator();
            builder.append(it.next());
            while(it.hasNext())
            {
                builder.append(delimiter);
                builder.append(it.next());
            }
            
        }
        return builder;
    }
    
    public static StringBuilder toString(Map m, String keyValueDelimiter, String entryDelimiter)
    {
        StringBuilder builder = new StringBuilder();
        if(!Util.isNullOrEmpty(m))
        {
            Iterator<Map.Entry> it = m.entrySet().iterator();
            Map.Entry entry = it.next();
            builder.append(entry.getKey()).append(keyValueDelimiter);
            builder.append(entry.getValue());
            while(it.hasNext())
            {
                builder.append(entryDelimiter);
                entry = it.next();
                builder.append(entry.getKey()).append(keyValueDelimiter);
                builder.append(entry.getValue());
            }            
        }
        return builder;
    }
    
    public static StringBuilder toString(String delimiter, Object... objects)
    {
        StringBuilder builder = new StringBuilder();
        if(!Util.isNullOrEmpty(objects))
        {
            for(int i=0; i<objects.length; i++)
            {
                builder.append(objects[i]);
                if(i+1 < objects.length)
                {
                    builder.append(delimiter);
                }
            }
        }
        return builder;
    }
    
}
