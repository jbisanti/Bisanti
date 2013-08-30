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
    public static final String NEW_LINE = System.getProperty("line.separator");
    
    private StringUtil(){};
    
    public static boolean isNullOrEmpty(String s)
    {
        return s == null || s.isEmpty();
    }
    
    public static String nonNull(Object obj, boolean trim)
    {
        if(obj == null)
        {
            return "";
        }
        
        String s = String.valueOf(obj);
        if(trim)
        {
            return s.trim();
        }
        else
        {
            return s;
        }
    }
    
    public static String toString(Collection c, String delimiter)
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
        return builder.toString();
    }
    
    public static String toString(Map m, String keyValueDelimiter, String entryDelimiter)
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
        return builder.toString();
    }
    
    public static String toString(String delimiter, Object... objects)
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
        return builder.toString();
    }
    
    public static boolean equals(String string, CharSequence sequence, CharSequence... others)
    {
        if(string.equals(sequence == null ? null : sequence.toString()))
        {
            return true;
        }
        
        if(!Util.isNullOrEmpty(others))
        {
            for(CharSequence cs: others)
            {
                if(string.equals(cs == null ? null : cs.toString()))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean contains(String string, CharSequence sequence, CharSequence... others)
    {
        if(string.contains(sequence))
        {
            return true;
        }
        
        if(!Util.isNullOrEmpty(others))
        {
            for(CharSequence cs: others)
            {
                if(string.contains(cs))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean containsIgnoreCase(String string, CharSequence sequence, CharSequence... others)
    {
        string = string.toUpperCase();
        
        if(string.contains(sequence.toString().toUpperCase()))
        {
            return true;
        }
        
        if(!Util.isNullOrEmpty(others))
        {
            for(CharSequence cs: others)
            {
                if(string.contains(cs.toString().toUpperCase()))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static String replaceLast(String toModify, CharSequence replace, CharSequence replaceWith)
    {
        int index = toModify.lastIndexOf(replace.toString());
        if(index < 0)
        {
            return toModify;
        }
        
        StringBuilder sb = new StringBuilder(toModify);
        sb.delete(index, replace.length());
        sb.insert(index, replaceWith);
        return sb.toString();
    }
    
    
    
}
