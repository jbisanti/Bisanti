package com.jdb.utility;

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
    private Number number;
    
    public NumberPlus(Number number)
    {
        this.number = number;
    }
    
    public static Number valueOf(Object o)
    {
        try
        {
            final String s = String.valueOf(o).trim();
            if(StringUtil.equal(false, s, "true"))
            {
                return 1;
            }
            else if(StringUtil.equal(false, s, "false"))
            {
                return -1;
            }
                   
            return Double.valueOf(s);
        }
        catch(Exception ex)
        {
            return Double.NaN;
        }
    }
    
    /**
     * Determines if the {@link Number} parameters are numerically equivalent; 
     * the <code>doubleValue()</code> method is used to test equality. This 
     * method is also null-safe; if one parameter is null, no {@link Exception}s
     * are thrown and false will be returned.
     * @param num1 Any {@link Number}
     * @param num2 Any {@link Number}
     * @return true if the double values are equivalent, false if not
     */
    public static boolean equal(final Number num1, final Number num2)
    {
        if(Util.containsNull(num1, num2))
        {
            return num1 == num2;
        }
        else
        {
            return num1.doubleValue() == num2.doubleValue();
        }
    }
    
    public NumberPlus setValue(Number value)
    {
        this.number = value;
        return this;
    }
    
    public NumberPlus getAndIncrement()
    {
        NumberPlus old = new NumberPlus(this.number);
        Class<? extends Number> c = this.number.getClass();
        if(c == Integer.class)
        {
            this.number = this.number.intValue() + 1;
        }
        else if(c == Long.class)
        {
            this.number = this.number.longValue() + 1;
        }
        else if(c == Double.class)
        {
            this.number = this.number.doubleValue() + 1;
        }
        else if(c == Float.class)
        {
            this.number = this.number.floatValue() + 1;
        }
        else if(c == Byte.class)
        {
            this.number = this.number.byteValue() + 1;
        }
        else if(c == Short.class)
        {
            this.number = this.number.shortValue() + 1;
        }
        else
        {
            throw new RuntimeException(this.number.getClass() + " is not a supported Number type");
        }
        return old;
    }
    
    public NumberPlus incrementAndGet()
    {
        Class<? extends Number> c = this.number.getClass();
        if(c == Integer.class)
        {
            this.number = this.number.intValue() + 1;
        }
        else if(c == Long.class)
        {
            this.number = this.number.longValue() + 1;
        }
        else if(c == Double.class)
        {
            this.number = this.number.doubleValue() + 1;
        }
        else if(c == Float.class)
        {
            this.number = this.number.floatValue() + 1;
        }
        else if(c == Byte.class)
        {
            this.number = this.number.byteValue() + 1;
        }
        else if(c == Short.class)
        {
            this.number = this.number.shortValue() + 1;
        }
        else
        {
            throw new RuntimeException(this.number.getClass() + " is not a supported Number type");
        }
        return this;
    }
    
    public NumberPlus getAndDecrement()
    {
        NumberPlus old = new NumberPlus(this.number);
        Class<? extends Number> c = this.number.getClass();
        if(c == Integer.class)
        {
            this.number = this.number.intValue() - 1;
        }
        else if(c == Long.class)
        {
            this.number = this.number.longValue() - 1;
        }
        else if(c == Double.class)
        {
            this.number = this.number.doubleValue() - 1;
        }
        else if(c == Float.class)
        {
            this.number = this.number.floatValue() - 1;
        }
        else if(c == Byte.class)
        {
            this.number = this.number.byteValue() - 1;
        }
        else if(c == Short.class)
        {
            this.number = this.number.shortValue() - 1;
        }
        else
        {
            throw new RuntimeException(this.number.getClass() + " is not a supported Number type");
        }
        return old; 
    }
    
    public NumberPlus decrementAndGet()
    {
        Class<? extends Number> c = this.number.getClass();
        if(c == Integer.class)
        {
            this.number = this.number.intValue() - 1;
        }
        else if(c == Long.class)
        {
            this.number = this.number.longValue() - 1;
        }
        else if(c == Double.class)
        {
            this.number = this.number.doubleValue() - 1;
        }
        else if(c == Float.class)
        {
            this.number = this.number.floatValue() - 1;
        }
        else if(c == Byte.class)
        {
            this.number = this.number.byteValue() - 1;
        }
        else if(c == Short.class)
        {
            this.number = this.number.shortValue() - 1;
        }
        else
        {
            throw new RuntimeException(this.number.getClass() + " is not a supported Number type");
        }
        return this;
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
        return Double.valueOf(number.doubleValue()).hashCode();
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
