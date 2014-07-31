/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.util;

/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 * <br><br>
 *
 * @author Jason Bisanti
 */
public class NumberPlus extends Number implements Comparable<Number>
{
    private final Number number;
    
    public NumberPlus()
    {
        this.number = 0;
    }
    
    public NumberPlus(Number number)
    {
        this.number = number;
    }

    @Override
    public int intValue()
    {
        return this.number.intValue();
    }

    @Override
    public long longValue()
    {
        return this.number.longValue();
    }

    @Override
    public float floatValue()
    {
        return this.number.floatValue();
    }

    @Override
    public double doubleValue()
    {
        return this.number.doubleValue();
    }

    @Override
    public byte byteValue()
    {
        return this.number.byteValue();
    }

    @Override
    public short shortValue()
    {
        return this.number.shortValue();
    }

    @Override
    public int compareTo(Number o)
    {
        return Double.compare(this.doubleValue(), o.doubleValue());
    }

    @Override
    public int hashCode()
    {
        return Double.valueOf(this.doubleValue()).hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Number && 
                this.doubleValue() == ((Number)obj).doubleValue();
    }

    @Override
    public String toString()
    {
        return Double.toString(this.doubleValue());
    }

}
