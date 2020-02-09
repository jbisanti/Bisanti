package org.bisanti.utility;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * A collection of methods and attributes meant to be used for the comparison, 
 * manipulation, and inspection of useful {@link String}-based utilities. Though 
 * the class name implies only {@link String} objects can be used, this class
 * has been written so that any {@link CharSequence} instance, where applicable,
 * can be used to provide for comparison, manipulation, and inspection. This not
 * only provides greater flexibility and convenience for code, but it is also
 * more efficient when use these operations for large quantities.
 * 
 * @author Jason Bisanti
 */
public final class StringUtil
{
    /** The OS-dependent {@link String} to represent a 'new line' in ASCII */
    public static final String NL = System.getProperty("line.separator");
    
    /** 
     * A {@link Map} of all Java control {@link Character}s as keys and 
     * {@link String} values that can be displayed in output.
     */
    public static final Map<Character, String> CONTROL_CHARS;
    
    // Populate control character map and make it unmodifiable
    static
    {
        Map<Character, String> map = new HashMap<>();
        map.put('\t', "\\t");
        map.put('\b', "\\b");
        map.put('\n', "\\n");
        map.put('\r', "\\r");
        map.put('\f', "\\f");
        CONTROL_CHARS = Collections.unmodifiableMap(map);
    }
    
    /**
     * Default capacity used to estimate the number of characters in an
     * {@link Object}s toString() method. The default is 25. This is used for
     * efficiency purposes when calling toString() on a large number of Objects.
     * <br><br>
     * E.g., when method {@link toString(CharSequence, Object...)} is called,
     * a {@link StringBuilder} is instantiated with the number of Objects times
     * this capacity. 
     * <br><br>
     * The default value was an arbitrary decision based on average toString()
     * lengths. Increase or decrease this value as you see fit.
     */
    public static int TOSTRING_CAPACITY = 32;
    
    /**
     * Private constructor to prevent instantiation.
     */
    private StringUtil(){};
    
    /**
     * Returns true if parameter is null or method isEmpty() returns true.
     * 
     * @param string {@link String}
     * @return true if null or empty, false if not
     */
    public static boolean isNullOrEmpty(final String string)
    {
        return string == null || string.isEmpty();
    }
    
    /**
     * Returns the toString() value of the parameter. If parameter is null, an
     * empty {@link String} ("") is returned.
     * 
     * @param obj Any {@link Object}
     * @param trim If true and parameter is not null, trim() will be returned
     * from the parameter's toString() value
     * @return {@link String}
     */
    public static String nonNull(final Object obj, final boolean trim)
    {
        if(obj == null)
        {
            return "";
        }

        return trim ? String.valueOf(obj).trim() : String.valueOf(obj);
    }
    
    /**
     * Versatile method called by each toString() implementation that implements
     * the common logic.
     * 
     * @param delimiter {@link CharSequence} separating each toString() value
     * @param array {@link Object} that will be an {@link Array} instance
     * @return {@link String}
     */
    private static String toStringImpl(final CharSequence delimiter, final Object array)
    {
        final int length = array == null ? 0 : Array.getLength(array);
        if(length == 0)
        {
            return "";
        }
        
        StringBuilder builder = new StringBuilder(length * TOSTRING_CAPACITY);
        builder.append(Array.get(array, 0));
        for(int i=1; i<length; i++)
        {
            builder.append(delimiter);
            builder.append(Array.get(array, i));          
        }
        return builder.toString();
    }
    
    /**
     * A convenience method to print the toString() value of each {@link Object}.
     * 
     * @param objects Any number of {@link Object}s or any {@link Object} array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final Object... objects)
    {
        return toStringImpl(delimiter, objects);
    }
    
    /**
     * A convenience method to print each toString() value for each element
     * in any {@link Collection}.
     * 
     * @param delimiter {@link CharSequence} to print between elements
     * @param collection Any {@link Collection}
     * @return {@link String}
     */
    public static String toString(final CharSequence delimiter, final Collection collection)
    {
        StringBuilder sb = new StringBuilder();
        if(!CollectionUtil.isNullOrEmpty(collection))
        {            
            Iterator it = collection.iterator();
            if(it.hasNext())
            {
                sb.append(it.next());
            }
            while(it.hasNext())
            {
                sb.append(delimiter).append(it.next());
            }
        }        
        return sb.toString();
    }
    
    /**
     * A convenience method to print the toString() value of each boolean.
     * 
     * @param booleans Any number of booleans or any boolean array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final boolean[] booleans)
    {
        return toStringImpl(delimiter, booleans);
    }
    
    /**
     * A convenience method to print the toString() value of each char.
     * 
     * @param chars Any number of chars or any char array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final char[] chars)
    {
        return toStringImpl(delimiter, chars);
    }
    
    /**
     * A convenience method to print the toString() value of each byte.
     * 
     * @param bytes Any number of bytes or any byte array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final byte[] bytes)
    {
        return toStringImpl(delimiter, bytes);
    }
    
    /**
     * A convenience method to print the toString() value of each short.
     * 
     * @param shorts Any number of shorts or any short array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final short[] shorts)
    {
        return toStringImpl(delimiter, shorts);
    }
    
    /**
     * A convenience method to print the toString() value of each int.
     * 
     * @param ints Any number of ints or any int array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final int[] ints)
    {
        return toStringImpl(delimiter, ints);
    }
    
    /**
     * A convenience method to print the toString() value of each long.
     * 
     * @param longs Any number of longs or any long array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final long[] longs)
    {
        return toStringImpl(delimiter, longs);
    }
    
    /**
     * A convenience method to print the toString() value of each float.
     * 
     * @param floats Any number of floats or any float array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final float[] floats)
    {
        return toStringImpl(delimiter, floats);
    }
    
    /**
     * A convenience method to print the toString() value of each double.
     * 
     * @param doubles Any number of doubles or any double array
     * @param delimiter {@link CharSequence} separating each toString() value
     * @return User-friendly {@link String} of all toString() values
     */
    public static String toString(final CharSequence delimiter, final double[] doubles)
    {
        return toStringImpl(delimiter, doubles);
    }
    
    /**
     * A convenience method to print the toString() value of each {@link Entry}
     * from a {@link Map}.
     * 
     * @param map Any {@link Map} instance
     * @param keyValueDelimiter {@link CharSequence} separating each Key
     * toString() from its Value toString()
     * @param entryDelimiter {@link CharSequence} separating each {@link Entry}
     * @return User-friendly {@link String} of {@link Entry} toString() values
     */
    public static String toString(final CharSequence keyValueDelimiter, final CharSequence entryDelimiter, final Map map)
    {        
        if(Util.isNullOrEmpty(map))
        {
            return "";
        }
        
        StringBuilder builder = new StringBuilder(map.size() * TOSTRING_CAPACITY * 2);
        Iterator<Entry> it = map.entrySet().iterator();
        Entry entry = it.next();
        builder.append(entry.getKey()).append(keyValueDelimiter);
        builder.append(entry.getValue());
        while (it.hasNext())
        {
            builder.append(entryDelimiter);
            entry = it.next();
            builder.append(entry.getKey()).append(keyValueDelimiter);
            builder.append(entry.getValue());
        }
        
        return builder.toString();
    }
    
    /**
     * Returns a {@link String} of the full stack trace.
     * 
     * @param throwable {@link Throwable} of stack trace to obtain
     * @return {@link String} form of the full stack trace.
     */
    public static String getStackTrace(Throwable throwable)
    {
        try(final StringWriter stringWriter = new StringWriter();
                final PrintWriter pw = new PrintWriter(stringWriter))
        {
            throwable.printStackTrace(pw);
            return stringWriter.toString();
        }
        catch(Exception ex)
        {
            return String.valueOf(throwable);
        }
    }
    
    /**
     * Determines if parameter container is equal to any additional parameter.
     * If no other parameters are present, this method will always return false.
     * 
     * @param <T> {@link CharSequence}
     * @param matchCase True if case should match for equality, false if not
     * @param container {@link CharSequence} to match for equality
     * @param others {@link CharSequence}(es) to test parameter container against
     * @return true if any parameter matches parameter container, false if not
     */
    public static <T extends CharSequence> boolean equal(final boolean matchCase, final T container, final T... others)
    {
        final int length = container.length();
        
        for(T other: others)
        {
            if(length == other.length())
            {
                boolean matches = true;
                
                for(int i=0; i<length; i++)
                {
                    if(!equal(matchCase, container.charAt(i), other.charAt(i)))
                    {
                        matches = false;
                        break;
                    }
                }
                
                if(matches)
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Determines if the first {@link CharSequence} contains the second
     * {@link CharSequence}.
     * 
     * @param <T> {@link CharSequence} 
     * @param matchCase true to match case, false to ignore case
     * @param container {@link CharSequence} to compare against
     * @param sequence {link CharSequence} potentially within container
     * @return true if contained, false if not
     */
    public static <T extends CharSequence> boolean contains(final boolean matchCase, final T container, final T sequence)
    {
        return containsAny(matchCase, container, sequence);
    }
    
    /**
     * Determines if the first {@link CharSequence} contains any of the 
     * subsequent {@link CharSequence}.
     * 
     * @param <T> {@link CharSequence} 
     * @param matchCase true to match case, false to ignore case
     * @param container {@link CharSequence} to compare against
     * @param sequences {link CharSequence}s potentially within container
     * @return true if any are contained, false if not
     */
    public static <T extends CharSequence> boolean containsAny(final boolean matchCase, final T container, final T... sequences)
    {
        for(T t: sequences)
        {
            if(indexOf(matchCase, container, t) >= 0)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if the first {@link CharSequence} starts with the second
     * {@link CharSequence}.
     * 
     * @param <T> {@link CharSequence} 
     * @param matchCase true to match case, false to ignore case
     * @param container {@link CharSequence} to compare against
     * @param sequence {link CharSequence} potentially at the beginning of
     * parameter container
     * @return true if starts with, false if not
     */
    public static <T extends CharSequence> boolean startsWith(final boolean matchCase, final T container, final T sequence)
    {
        return startsWithAny(matchCase, container, sequence);
    }
    
    /**
     * Determines if the first {@link CharSequence} starts with any subsequent
     * {@link CharSequence}s.
     * 
     * @param <T> {@link CharSequence} 
     * @param matchCase true to match case, false to ignore case
     * @param container {@link CharSequence} to compare against
     * @param sequences {link CharSequence}s potentially at the beginning of
     * parameter container
     * @return true if starts with any, false if not
     */
    public static <T extends CharSequence> boolean startsWithAny(final boolean matchCase, final T container, final T... sequences)
    {
        for(T t: sequences)
        {
            if(indexOf(matchCase, container, t) == 0)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if the first {@link CharSequence} ends with with any 
     * subsequent {@link CharSequence}s.
     * 
     * @param <T> {@link CharSequence} 
     * @param matchCase true to match case, false to ignore case
     * @param container {@link CharSequence} to compare against
     * @param sequence {link CharSequence} potentially at the beginning of
     * parameter container
     * @return true if ends with, false if not
     */
    public static <T extends CharSequence> boolean endsWith(final boolean matchCase, final T container, final T sequence)
    {
        return endsWithAny(matchCase, container, sequence);
    }
    
    /**
     * Determines if the first {@link CharSequence} ends with with any 
     * subsequent {@link CharSequence}s.
     * 
     * @param <T> {@link CharSequence} 
     * @param matchCase true to match case, false to ignore case
     * @param container {@link CharSequence} to compare against
     * @param sequences {link CharSequence}s potentially at the beginning of
     * parameter container
     * @return true if ends with any, false if not
     */
    public static <T extends CharSequence> boolean endsWithAny(final boolean matchCase, final T container, final T... sequences)
    {
        for(T t: sequences)
        {
            int lastIndex = lastIndexOf(matchCase, container, t);
            if(lastIndex >= 0 && lastIndex + t.length() == container.length())
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the first index of the occurrence of parameter sequence in 
     * parameter container. 
     * 
     * @param <T>
     * @param matchCase true if case should be considered, false if not
     * @param container The {@link CharSequence} to search in
     * @param sequence The {@link CharSequence} to search for
     * @return 0 or greater int value if found, -1 if not found
     */
    public static <T extends CharSequence> int indexOf(final boolean matchCase, final T container, final T sequence)
    {
        return indexOf(matchCase, 0, container, sequence);
    }
    
    /**
     * Returns the first index of the occurrence of parameter sequence in 
     * parameter container starting at the given index.
     * 
     * @param <T>
     * @param matchCase true if case should be considered, false if not
     * @param startIndex Index from which to start search
     * @param container The {@link CharSequence} to search in
     * @param sequence The {@link CharSequence} to search for
     * @return 0 or greater int value if found, -1 if not found
     */
    public static <T extends CharSequence> int indexOf(final boolean matchCase, final int startIndex, final T container, final T sequence)
    {
        final int containerLength = container.length();
        final int sequenceLength = sequence.length();
        
        if(containerLength >= sequenceLength)
        {
            for(int i=startIndex; i<containerLength; i++)
            {
                if( sequenceLength > containerLength-i)
                {
                    break;
                }
                
                boolean matches = true;
                
                for(int j=0; j<sequenceLength; j++)
                {
                    int subIndex = i+j;
                    
                    if( subIndex >= containerLength || !equal(matchCase, container.charAt(subIndex), sequence.charAt(j)))
                    {
                        matches = false;
                        break;
                    }
                }
                
                if(matches)
                {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Returns the last index of the occurrence of parameter sequence in 
     * parameter container. 
     * 
     * @param <T>
     * @param matchCase true if case should be considered, false if not
     * @param container The {@link CharSequence} to search in
     * @param sequence The {@link CharSequence} to search for
     * @return 0 or greater int value if found, -1 if not found
     */
    public static <T extends CharSequence> int lastIndexOf(final boolean matchCase, final T container, final T sequence)
    {
        return lastIndexOf(matchCase, container.length() - 1, container, sequence);
    }
    
    /**
     * Returns the last index of the occurrence of parameter sequence in 
     * parameter container. 
     * 
     * @param <T>
     * @param matchCase true if case should be considered, false if not
     * @param startIndex Index from which to start search
     * @param container The {@link CharSequence} to search in
     * @param sequence The {@link CharSequence} to search for
     * @return 0 or greater int value if found, -1 if not found
     */
    public static <T extends CharSequence> int lastIndexOf(final boolean matchCase, final int startIndex, final T container, final T sequence)
    {
        if(container.length() >= sequence.length())
        {
            for(int i=startIndex; i>=0; i--)
            {
                boolean matches = true;
                int index = i;
                
                for(int j=sequence.length()-1; j>=0 ; j--)
                {
                    if(index < 0 || !equal(matchCase, container.charAt(index--), sequence.charAt(j)))
                    {
                        matches = false;
                        break;
                    }
                }
                
                if(matches)
                {
                    return index + 1;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Inserts parameter insertion into parameter container at the given index.
     * 
     * @param <T>
     * @param container {@link CharSequence} to insert into
     * @param index Index of where to insert
     * @param insertion {@link CharSequence} to insert
     * @return If parameter container is a {@link StringBuilder} or 
     * {@link StringBuffer} instance, that instance will be returned. Otherwise,
     * a {@link String} instance will be returned.
     */
    public static <T extends CharSequence> T insert(final T container, final int index, final T insertion)
    {
        if(index > -1 && index < container.length())
        {
            if(container instanceof StringBuilder)
            {
                return (T) ((StringBuilder)container).insert(index, insertion);
            }
            else if(container instanceof StringBuffer)
            {
                return (T) ((StringBuffer)container).insert(index, insertion);
            }
            
            return (T) (container.subSequence(0, index) + insertion.toString() + container.subSequence(index, container.length()));
        }
        
        return container;
    }
    
    /**
     * Deletes the first occurrence of parameter sequence from parameter 
     * container if it exists.
     * 
     * @param <T> Any {@link CharSequence} implementation
     * @param matchCase If true, take case into account
     * @param container {@link CharSequence} to remove from
     * @param sequence {@link CharSequence} to remove
     * @return If parameter container is a {@link StringBuilder} or 
     * {@link StringBuffer} that instance, otherwise, a {@link String}.
     */
    public static <T extends CharSequence> T deleteFirst(final boolean matchCase, final T container, final T sequence)
    {        
        int index = indexOf(matchCase, container, sequence);
        
        if(index > -1)
        {
            if(container instanceof StringBuilder)
            {
                return (T) ((StringBuilder)container).delete(index, index+sequence.length());
            }
            else if(container instanceof StringBuffer)
            {
                return (T) ((StringBuffer)container).delete(index, index+sequence.length());
            }
            
            return (T) (container.subSequence(0, index).toString() + container.subSequence(index + sequence.length(), container.length()));
        }
        
        return container;
    }
    
    /**
     * Deletes the last occurrence of parameter sequence from parameter 
     * container if it exists.
     * 
     * @param <T> Any {@link CharSequence} implementation
     * @param matchCase If true, take case into account
     * @param container {@link CharSequence} to remove from
     * @param sequence {@link CharSequence} to remove
     * @return If parameter container is a {@link StringBuilder} or 
     * {@link StringBuffer} that instance, otherwise, a {@link String}.
     */
    public static <T extends CharSequence> T deleteLast(final boolean matchCase, T container, T sequence)
    {        
        int index = lastIndexOf(matchCase, container, sequence);
        
        if(index > -1)
        {
            if(container instanceof StringBuilder)
            {
                return (T) ((StringBuilder)container).delete(index, index+sequence.length());
            }
            else if(container instanceof StringBuffer)
            {
                return (T) ((StringBuffer)container).delete(index, index+sequence.length());
            }
            
            return (T) (container.subSequence(0, index).toString() + container.subSequence(index + sequence.length(), container.length()));
        }
        
        return container;
    }
    
    /**
     * Deletes all occurrences of parameter sequence from parameter container if
     * it exists.
     * 
     * @param <T> Any {@link CharSequence} implementation
     * @param matchCase If true, take case into account
     * @param container {@link CharSequence} to remove from
     * @param sequence {@link CharSequence} to remove
     * @return If parameter container is a {@link StringBuilder} or 
     * {@link StringBuffer} that instance, otherwise, a {@link String}.
     */
    public static <T extends CharSequence> T deleteAll(final boolean matchCase, final T container, final T sequence)
    {        
        T charSequence = container;
        
        int index;
        while( (index = indexOf(matchCase, charSequence, sequence)) > -1 )
        {
            if(container instanceof StringBuilder)
            {
                charSequence = (T) ((StringBuilder)container).delete(index, index+sequence.length());
            }
            else if(container instanceof StringBuffer)
            {
                charSequence = (T) ((StringBuffer)container).delete(index, index+sequence.length());
            }
            else
            {
                charSequence = (T) (charSequence.subSequence(0, index).toString() + charSequence.subSequence(index + sequence.length(), charSequence.length()));
            }
        }
        
        return charSequence;
    }
    
    /**
     * Calls the trim() method on each index in parameter strings and sets it as
     * the new index value.
     * 
     * @param strings {@link Array} of {@link String}s
     */
    public static void trim(final String[] strings)
    {
        for(int i=0; i<strings.length; i++)
        {
            if(strings[i] != null)
            {
                strings[i] = strings[i].trim();
            }
        }
    }
    
    /**
     * Calls the trim() method on each index in parameter strings and sets it as
     * the new index value.
     * 
     * @param <T> Any {@lisk List} implementation with a {@link String} generic
     * @param strings {@link List} of {@link String}s
     */
    public static <T extends List<String>> void trim(final T strings)
    {
        for(int i=0; i<strings.size(); i++)
        {
            String s = strings.get(i);
            if(s != null)
            {
                strings.set(i, s.trim());
            }
        }
    }
    
    /**
     * Calls the trim() method on each element in parameter strings. Unlike
     * methods {@link trim(String[])} and {@link trim(List)}, which replace the
     * current value with the new trim() value, this method instantiates a new
     * {@link Collection} type to be returned, making it inefficient for large
     * {@link Collection}s or to be called repeatedly.
     * 
     * @param <T> {@lisk Collection} implementation with a {@link String} generic
     * @param strings {@link Collection} of {@link String}s
     * @return A new {@link Collection} with values that have called trim()
     */
    public static <T extends Collection<String>> T trim(final T strings)
    {
        Constructor constructor;
        T collection;
        try
        {
            constructor = strings.getClass().getConstructor(Integer.TYPE);
            collection = (T) constructor.newInstance(strings.size());
        }
        catch(Exception ex)
        {
            if(ex instanceof NoSuchMethodException || ex instanceof SecurityException)
            {
                try 
                {
                    constructor = strings.getClass().getConstructor();
                    collection = (T) constructor.newInstance();
                } 
                catch (Exception ex1) 
                {
                    throw new RuntimeException(ex1);
                }
            }
            else
            {
                throw new RuntimeException(ex);
            }
        }
        
        for(String s: strings)
        {
            collection.add(s == null ? s : s.trim());
        }
        
        return collection;
    }
    
    /**
     * Determines if characters are equivalent to one another.
     * 
     * @param matchCase If true, takes case into account
     * @param c1 char
     * @param c2 char
     * @return true if characters are equal, false if not
     */
    public static boolean equal(final boolean matchCase, final char c1, final char c2)
    {
        return matchCase ? c1 == c2 : Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }
    
    /**
     * Returns if the character is considered a printable Ascii value. This 
     * method is only intended for English language based systems.
     * 
     * @param character char
     * @return true if character is guaranteed to be printable to output, false
     * if not
     */
    public static boolean isPrintableAscii(char character)
    {
        return character > 32 && character < 127;
    }
    
    /**
     * Converts the size of a file in bytes to a human readable format. E.g., 
     * 10.56 MB, 237 KB, 4.56 GB, etc. This solution was taken from the 
     * famous StackOverflow post at: https://stackoverflow.com/a/24805871
     * 
     * @param bytes Length in bytes of the file, typically gotten by 
     * {@link Files#size(Path)} or {@link File#length()}.
     * @return Human readable {@link String} of file size.
     */
    public static String getFileSize(long bytes)
    {
        Double decimal = Double.valueOf(bytes);
        if(decimal < 1024)
        {
            return bytes + " B";
        }
        
        int bits = (63 - Long.numberOfLeadingZeros(bytes)) / 10; 
        return String.format("%.2f %sB", decimal / (1L << (bits*10)), " KMGTPE".charAt(bits));
    }
}
