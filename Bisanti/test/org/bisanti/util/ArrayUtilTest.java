/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.util;

import org.bisanti.utility.ArrayUtil;
import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jason Bisanti
 */
public class ArrayUtilTest
{
    private static Random random;
    
    public ArrayUtilTest()
    {
        
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        random = new Random(System.currentTimeMillis());
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_boolean_booleanArr()
    {
        boolean[] test = new boolean[]{false, false, true, false};
        assertEquals(2, ArrayUtil.indexOf(true, test));
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_char_charArr()
    {
        char x = 'c';
        char[] test = new char[]{'a', 'b', x, 'd'};
        assertEquals(2, ArrayUtil.indexOf(x, test));
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_byte_byteArr()
    {
        byte x = Integer.valueOf(random.nextInt()).byteValue();
        byte y = x++;
        byte[] test = new byte[]{y, y, x, y};
        assertEquals(2, ArrayUtil.indexOf(x, test));
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_short_shortArr()
    {
        short x = 'c';
        short[] test = new short[]{'a', 'b', x, 'd'};
        assertEquals(2, ArrayUtil.indexOf(x, test));
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_int_intArr()
    {
        System.out.println("indexOf");
        int value = 3;
        int[] array = new int[]{1, 3, 5};
        int expResult = 1;
        int result = ArrayUtil.indexOf(value, array);
        assertEquals(expResult, result);
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_long_longArr()
    {
        System.out.println("indexOf");
        long value = 4L;
        long[] array = new long[]{1, 3, 4, 4, 4};
        int expResult = 2;
        int result = ArrayUtil.indexOf(value, array);
        assertEquals(expResult, result);
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_float_floatArr()
    {
        System.out.println("indexOf");
        float value = 0.0F;
        float[] array = new float[0];
        int expResult = -1;
        int result = ArrayUtil.indexOf(value, array);
        assert(expResult == result);
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_double_doubleArr()
    {
        System.out.println("indexOf");
        double value = 0.0;
        double[] array = new double[]{1, 1, 1, 0, 1};
        int expResult = 3;
        int result = ArrayUtil.indexOf(value, array);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_GenericType_GenericType()
    {
        System.out.println("remove");
        Object value = new Thread();
        Object[] array = new Object[]{value, 1};
        Object[] expResult = new Object[]{1};
        Object[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_boolean_booleanArr()
    {
        System.out.println("remove");
        boolean value = false;
        boolean[] array = {false, false, false};
        boolean[] expResult = {false, false};
        boolean[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_char_charArr()
    {
        System.out.println("remove");
        char value = 'x';
        char[] array = "xyz".toCharArray();
        char[] expResult = "yz".toCharArray();
        char[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_byte_byteArr()
    {
        System.out.println("remove");
        byte value = 2;
        byte[] array = new byte[]{1, 2, 3};
        byte[] expResult = new byte[]{1, 3};
        byte[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_short_shortArr()
    {
        System.out.println("remove");
        short value = 0;
        short[] array = new short[]{1, 2};
        short[] expResult = new short[]{1, 2};
        short[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_int_intArr()
    {
        System.out.println("remove");
        int value = 1;
        int[] array = new int[]{1, 1};
        int[] expResult = new int[]{1};
        int[] result = ArrayUtil.remove(value, array);
        System.out.println(":::: " + expResult[0] + ", " + result[0] + "-" + result.length);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_long_longArr()
    {
        System.out.println("remove");
        long value = 0L;
        long[] array = new long[]{value};
        long[] expResult = new long[0];
        long[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_float_floatArr()
    {
        System.out.println("remove");
        float value = 0.0F;
        float[] array = new float[]{-1, 0, 1};
        float[] expResult = new float[]{-1, 1};
        float[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_double_doubleArr()
    {
        System.out.println("remove");
        double value = 0.0;
        double[] array = {1, 0, -1};
        double[] expResult = {1, -1};
        double[] result = ArrayUtil.remove(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_GenericType_GenericType()
    {
        System.out.println("add");
        Object value = new Object();
        Object[] array = new Object[]{};
        Object[] expResult = new Object[]{value};
        Object[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_boolean_booleanArr()
    {
        System.out.println("add");
        boolean value = false;
        boolean[] array = new boolean[]{true};
        boolean[] expResult = new boolean[]{true, value};
        boolean[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_char_charArr()
    {
        System.out.println("add");
        char value = 'z';
        char[] array = "xy".toCharArray();
        char[] expResult = "xyz".toCharArray();
        char[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_byte_byteArr()
    {
        System.out.println("add");
        byte value = 0;
        byte[] array = new byte[]{2};
        byte[] expResult = new byte[]{2, 0};
        byte[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_short_shortArr()
    {
        System.out.println("add");
        short value = 0;
        short[] array = new short[0];
        short[] expResult = new short[]{value};
        short[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_int_intArr()
    {
        System.out.println("add");
        int value = 0;
        int[] array = new int[]{1};
        int[] expResult = new int[]{1, 0};
        int[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_long_longArr()
    {
        System.out.println("add");
        long value = 4;
        long[] array = new long[0];
        long[] expResult = new long[]{4};
        long[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_float_floatArr()
    {
        System.out.println("add");
        float value = 2;
        float[] array = new float[]{1};
        float[] expResult = new float[]{1, 2};
        float[] result = ArrayUtil.add(value, array);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_GenericType_GenericType()
    {
        System.out.println("join");
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        Object[] array1 = {t1};
        Object[] array2 = {t2}; 
        Object[] expResult = {t1, t2};
        Object[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_booleanArr_booleanArr()
    {
        System.out.println("join");
        boolean[] array1 = new boolean[0];
        boolean[] array2 = new boolean[]{true, false};
        boolean[] expResult = new boolean[]{true, false};
        boolean[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_charArr_charArr()
    {
        System.out.println("join");
        char[] array1 = "abc".toCharArray();
        char[] array2 = "def".toCharArray();
        char[] expResult = "abcdef".toCharArray();
        char[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_byteArr_byteArr()
    {
        System.out.println("join");
        byte[] array1 = {1, 0};
        byte[] array2 = {-1, -2};
        byte[] expResult = {1, 0, -1, -2};
        byte[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_shortArr_shortArr()
    {
        System.out.println("join");
        short[] array1 = new short[]{2, 2};
        short[] array2 = new short[0];
        short[] expResult = new short[]{2, 2};
        short[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_intArr_intArr()
    {
        System.out.println("join");
        int[] array1 = new int[0];
        int[] array2 = new int[0];
        int[] expResult = new int[0];
        int[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_longArr_longArr()
    {
        System.out.println("join");
        long[] array1 = {-3, -2};
        long[] array2 = {-1, 0};
        long[] expResult = {-3, -2, -1, 0};
        long[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_floatArr_floatArr()
    {
        System.out.println("join");
        float[] array1 = new float[]{4, 3};
        float[] array2 = new float[]{2, 1};
        float[] expResult = new float[]{4, 3, 2, 1};
        float[] result = ArrayUtil.join(array1, array2);
        assert(Arrays.equals(expResult, result));
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_doubleArr_doubleArr()
    {
        System.out.println("join");
        double[] array1 = new double[]{1, 2};
        double[] array2 = new double[]{3, 4};
        double[] expResult = new double[]{1, 2, 3, 4};
        double[] result = ArrayUtil.join(array1, array2);
        assertEquals(Arrays.equals(expResult, result), true);
    }
}