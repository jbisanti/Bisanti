package org.bisanti.util;

import java.lang.reflect.Array;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 *
 * @author Jason Bisanti
 */
public final class ArrayUtil 
{
    private ArrayUtil(){};
    
    public static int indexOf(Object value, Object[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(boolean value, boolean[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(char value, char[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(byte value, byte[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(short value, short[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(int value, int[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(long value, long[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(float value, float[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    public static int indexOf(double value, double[] array)
    {
        return indexOf(value, (Object)array);
    }
    
    private static int indexOf(Object value, Object array)
    {
        for(int i=0; i<Array.getLength(array); i++)
        {
            if(Util.equal(Array.get(array, i), value))
            {
                return i;
            }
        }
        return -1;
    }
    
    public static <T> T[] remove(T value, T[] array)
    {
        return (T[]) remove(value, (Object)array);
    }
    
    public static boolean[] remove(boolean value, boolean[] array)
    {
        return (boolean[]) remove(value, (Object)array);
    }
    
    public static char[] remove(char value, char[] array)
    {
        return (char[]) remove(value, (Object)array);
    }
    
    public static byte[] remove(byte value, byte[] array)
    {
        return (byte[]) remove(value, (Object)array);
    }
    
    public static short[] remove(short value, short[] array)
    {
        return (short[]) remove(value, (Object)array);
    }
    
    public static int[] remove(int value, int[] array)
    {
        return (int[]) remove(value, (Object)array);
    }
    
    public static long[] remove(long value, long[] array)
    {
        return (long[]) remove(value, (Object)array);
    }
    
    public static float[] remove(float value, float[] array)
    {
        return (float[]) remove(value, (Object)array);
    }
    
    public static double[] remove(double value, double[] array)
    {
        return (double[]) remove(value, (Object)array);
    }
    
    private static Object remove(Object value, Object array)
    {
        int index = indexOf(value, array);
        if(index < 0)
        {
            return array;
        }
        
        int length = Array.getLength(array);
        Object newArray = Array.newInstance(array.getClass().getComponentType(), length-1);
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index, length-index);
        return newArray;
    }
    
    public static <T> T[] add(T value, T[] array)
    {
        return (T[]) add(value, (Object)array);
    }
    
    public static boolean[] add(boolean value, boolean[] array)
    {
        return (boolean[]) add(value, (Object)array);
    }
    
    public static char[] add(char value, char[] array)
    {
        return (char[]) add(value, (Object)array);
    }
    
    public static byte[] add(byte value, byte[] array)
    {
        return (byte[]) add(value, (Object)array);
    }
    
    public static short[] add(short value, short[] array)
    {
        return (short[]) add(value, (Object)array);
    }
    
    public static int[] add(int value, int[] array)
    {
        return (int[]) add(value, (Object)array);
    }
    
    public static long[] add(long value, long[] array)
    {
        return (long[]) add(value, (Object)array);
    }
    
    public static float[] add(float value, float[] array)
    {
        return (float[]) add(value, (Object)array);
    }
    
    private static Object add(Object value, Object array)
    {
        int length = Array.getLength(array);
        Object newArray = Array.newInstance(array.getClass().getComponentType(), length+1);
        System.arraycopy(array, 0, newArray, 0, length);
        Array.set(newArray, length+1, value);
        return newArray;
    }
    
    public static <T> T[] join(T[] array1, T[] array2)
    {
        return (T[]) join((Object)array1, (Object)array2);
    }
    
    public static boolean[] join(boolean[] array1, boolean[] array2)
    {
        return (boolean[]) join((Object)array1, (Object)array2);
    }
    
    public static char[] join(char[] array1, char[] array2)
    {
        return (char[]) join((Object)array1, (Object)array2);
    }
    
    public static byte[] join(byte[] array1, byte[] array2)
    {
        return (byte[]) join((Object)array1, (Object)array2);
    }
    
    public static short[] join(short[] array1, short[] array2)
    {
        return (short[]) join((Object)array1, (Object)array2);
    }
    
    public static int[] join(int[] array1, int[] array2)
    {
        return (int[]) join((Object)array1, (Object)array2);
    }
    
    public static long[] join(long[] array1, long[] array2)
    {
        return (long[]) join((Object)array1, (Object)array2);
    }
    
    public static float[] join(float[] array1, float[] array2)
    {
        return (float[]) join((Object)array1, (Object)array2);
    }
    
    public static double[] join(double[] array1, double[] array2)
    {
        return (double[]) join((Object)array1, (Object)array2);
    }
    
    private static Object join(Object array1, Object array2)
    {
        int length1 = Array.getLength(array1);
        int length2 = Array.getLength(array2);
        Object newArray = Array.newInstance(array1.getClass().getComponentType(), length1 + length2);
        System.arraycopy(array1, 0, newArray, 0, length1);
        System.arraycopy(array2, 0, newArray, length1, length2);
        return newArray;
    }
    
}
