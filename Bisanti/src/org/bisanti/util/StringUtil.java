/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.util;

import java.util.Collection;

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
    
    public static StringBuilder toStringBuilder(Collection c, String delimiter)
    {
        StringBuilder builder = new StringBuilder();
        if(!Util.isNullOrEmpty(c))
        {
            int count = 0;
            for(Object obj: c)
            {
                builder.append(obj);
                if(++count < c.size())
                {
                    builder.append(delimiter);
                }
            }
            
        }
        return builder;
    }
    
}
