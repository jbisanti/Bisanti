package org.bisanti.util;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 *
 * @author Jason Bisanti
 */
public class NumberPlus extends Number implements Comparable<Number>
{
    private final Number number;
    
    private final int hashCode;
    
    public NumberPlus(Number number)
    {
        this.number = number;
        this.hashCode = Double.valueOf(this.doubleValue()).hashCode();
    }
    
    public NumberPlus add(Number number)
    {
        return new NumberPlus(this.doubleValue() + number.doubleValue());
    }
    
    public NumberPlus subtract(Number number)
    {
        return new NumberPlus(this.doubleValue() - number.doubleValue());
    }
    
    public NumberPlus multiply(Number number)
    {
        return new NumberPlus(this.doubleValue() * number.doubleValue());
    }
    
    public NumberPlus divide(Number number)
    {
        return new NumberPlus(this.doubleValue() / number.doubleValue());
    }
    
    public NumberPlus modulus(Number number)
    {
        return new NumberPlus(this.doubleValue() % number.doubleValue());
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
        return this.hashCode;
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
        return this.number.toString();
    }

}
