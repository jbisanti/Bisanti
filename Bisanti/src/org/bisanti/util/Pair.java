package org.bisanti.util;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * @author Jason Bisanti
 */
public class Pair<T, V>
{
    private T first;
    
    private V second;

    public Pair(T first, V second)
    {
        this.first = first;
        this.second = second;
    }

    public T getFirst()
    {
        return first;
    }

    public V getSecond()
    {
        return second;
    }
    
}
