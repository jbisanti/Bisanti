package org.bisanti.swingx;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * {@link Enum} used in the {@link ColumnFilterPanel} to determine what type of
 * equality should be used when determining if a row should be filtered or not.
 * 
 * @author Jason Bisanti
 */
public enum ColumnFilter
{
    EQUALS("equals", false)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.compareTo(val2) == 0;
        }
    }, 
    DOES_NOT_EQUAL("does not equal", false)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.compareTo(val2) != 0;
        }
    }, 
    CONTAINS("contains", true)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.toString().contains(val2.toString());
        }
    }, 
    DOES_NOT_CONTAIN("does not contain", true)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return !val1.toString().contains(val2.toString());
        }
    },
    LESS_THAN("less than", false)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.compareTo(val2) < 0;
        }
    }, 
    GREATER_THAN("greater than", false)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.compareTo(val2) > 0;
        }
    }, 
    LESS_THAN_EQUAL_TO("less than or equal to", false)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.compareTo(val2) <= 0;
        }
    }, 
    GREATER_THAN_EQUAL_TO("greater than or equal to", false)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.compareTo(val2) >= 0;
        }
    },
    STARTS_WITH("starts with", true)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.toString().startsWith(val2.toString());
        }
    },
    DOES_NOT_START_WITH("does not start with", true)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return !val1.toString().startsWith(val2.toString());
        }
    },
    ENDS_WITH("ends with", true)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return val1.toString().endsWith(val2.toString());
        }
    },
    DOES_NOT_END_WITH("does not end with", true)
    {
        @Override
        public boolean matches(Comparable val1, Comparable val2)
        {
            return !val1.toString().endsWith(val2.toString());
        }
    };
    
    final String text;
    private final boolean stringOnlyFilter;

    ColumnFilter(String text, boolean stringOnlyFilter)
    {
        this.text = text;
        this.stringOnlyFilter = stringOnlyFilter;
    }

    public String getText()
    {
        return this.text;
    }

    public boolean isStringOnlyFilter()
    {
        return this.stringOnlyFilter;
    }

    public abstract boolean matches(Comparable val1, Comparable val2);

}
