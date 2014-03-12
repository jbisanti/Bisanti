/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.util;

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
    private Random random;
    
    public ArrayUtilTest()
    {
        this.random = new Random(System.currentTimeMillis());
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
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
        byte x = Integer.valueOf(this.random.nextInt()).byteValue();
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
        int value = 0;
        int[] array = null;
        int expResult = 0;
        int result = ArrayUtil.indexOf(value, array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_long_longArr()
    {
        System.out.println("indexOf");
        long value = 0L;
        long[] array = null;
        int expResult = 0;
        int result = ArrayUtil.indexOf(value, array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_float_floatArr()
    {
        System.out.println("indexOf");
        float value = 0.0F;
        float[] array = null;
        int expResult = 0;
        int result = ArrayUtil.indexOf(value, array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class ArrayUtil.
     */
    @Test
    public void testIndexOf_double_doubleArr()
    {
        System.out.println("indexOf");
        double value = 0.0;
        double[] array = null;
        int expResult = 0;
        int result = ArrayUtil.indexOf(value, array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_GenericType_GenericType()
    {
        System.out.println("remove");
        Object value = null;
        T[] array = null;
        Object[] expResult = null;
        Object[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_boolean_booleanArr()
    {
        System.out.println("remove");
        boolean value = false;
        boolean[] array = null;
        boolean[] expResult = null;
        boolean[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_char_charArr()
    {
        System.out.println("remove");
        char value = ' ';
        char[] array = null;
        char[] expResult = null;
        char[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_byte_byteArr()
    {
        System.out.println("remove");
        byte value = 0;
        byte[] array = null;
        byte[] expResult = null;
        byte[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_short_shortArr()
    {
        System.out.println("remove");
        short value = 0;
        short[] array = null;
        short[] expResult = null;
        short[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_int_intArr()
    {
        System.out.println("remove");
        int value = 0;
        int[] array = null;
        int[] expResult = null;
        int[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_long_longArr()
    {
        System.out.println("remove");
        long value = 0L;
        long[] array = null;
        long[] expResult = null;
        long[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_float_floatArr()
    {
        System.out.println("remove");
        float value = 0.0F;
        float[] array = null;
        float[] expResult = null;
        float[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ArrayUtil.
     */
    @Test
    public void testRemove_double_doubleArr()
    {
        System.out.println("remove");
        double value = 0.0;
        double[] array = null;
        double[] expResult = null;
        double[] result = ArrayUtil.remove(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_GenericType_GenericType()
    {
        System.out.println("add");
        Object value = null;
        T[] array = null;
        Object[] expResult = null;
        Object[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_boolean_booleanArr()
    {
        System.out.println("add");
        boolean value = false;
        boolean[] array = null;
        boolean[] expResult = null;
        boolean[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_char_charArr()
    {
        System.out.println("add");
        char value = ' ';
        char[] array = null;
        char[] expResult = null;
        char[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_byte_byteArr()
    {
        System.out.println("add");
        byte value = 0;
        byte[] array = null;
        byte[] expResult = null;
        byte[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_short_shortArr()
    {
        System.out.println("add");
        short value = 0;
        short[] array = null;
        short[] expResult = null;
        short[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_int_intArr()
    {
        System.out.println("add");
        int value = 0;
        int[] array = null;
        int[] expResult = null;
        int[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_long_longArr()
    {
        System.out.println("add");
        long value = 0L;
        long[] array = null;
        long[] expResult = null;
        long[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ArrayUtil.
     */
    @Test
    public void testAdd_float_floatArr()
    {
        System.out.println("add");
        float value = 0.0F;
        float[] array = null;
        float[] expResult = null;
        float[] result = ArrayUtil.add(value, array);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_GenericType_GenericType()
    {
        System.out.println("join");
        T[] array1 = null;
        T[] array2 = null;
        Object[] expResult = null;
        Object[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_booleanArr_booleanArr()
    {
        System.out.println("join");
        boolean[] array1 = null;
        boolean[] array2 = null;
        boolean[] expResult = null;
        boolean[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_charArr_charArr()
    {
        System.out.println("join");
        char[] array1 = null;
        char[] array2 = null;
        char[] expResult = null;
        char[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_byteArr_byteArr()
    {
        System.out.println("join");
        byte[] array1 = null;
        byte[] array2 = null;
        byte[] expResult = null;
        byte[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_shortArr_shortArr()
    {
        System.out.println("join");
        short[] array1 = null;
        short[] array2 = null;
        short[] expResult = null;
        short[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_intArr_intArr()
    {
        System.out.println("join");
        int[] array1 = null;
        int[] array2 = null;
        int[] expResult = null;
        int[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_longArr_longArr()
    {
        System.out.println("join");
        long[] array1 = null;
        long[] array2 = null;
        long[] expResult = null;
        long[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_floatArr_floatArr()
    {
        System.out.println("join");
        float[] array1 = null;
        float[] array2 = null;
        float[] expResult = null;
        float[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ArrayUtil.
     */
    @Test
    public void testJoin_doubleArr_doubleArr()
    {
        System.out.println("join");
        double[] array1 = null;
        double[] array2 = null;
        double[] expResult = null;
        double[] result = ArrayUtil.join(array1, array2);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}