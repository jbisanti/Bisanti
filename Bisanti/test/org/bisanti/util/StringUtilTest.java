/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jason Bisanti
 */
public class StringUtilTest
{

    /**
     * Test of isNullOrEmpty method, of class StringUtil.
     */
    @Test
    public void testIsNullOrEmpty()
    {
        System.out.println("isNullOrEmpty");
        assertEquals(true, StringUtil.isNullOrEmpty(""));
        assertEquals(false, StringUtil.isNullOrEmpty(" "));
        assertEquals(true, StringUtil.isNullOrEmpty(null));
    }

    /**
     * Test of nonNull method, of class StringUtil.
     */
    @Test
    public void testNonNull()
    {
        System.out.println("nonNull");
        assertEquals("", StringUtil.nonNull(null, true));
        assertEquals(" ", StringUtil.nonNull(" ", false));
        assertEquals("", StringUtil.nonNull(" ", true));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_ObjectArr()
    {
        final String delimeter = ", ";
        final Object x = 'x';
        final Object y = 'y';
        final Object z = 'z';
        assertEquals(x + delimeter + y + delimeter + z, StringUtil.toString(delimeter, x, y, z));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_booleanArr()
    {
        final String delimeter = ", ";
        final String expected = true + delimeter + false + delimeter + true;
        assertEquals(expected, StringUtil.toString(delimeter, true, false, true));
        assertEquals(expected, StringUtil.toString(delimeter, new boolean[]{true, false, true}));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_charArr()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_byteArr()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_shortArr()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_intArr()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_longArr()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_floatArr()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_doubleArr()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_Collection_CharSequence()
    {

    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_3args()
    {

    }

    /**
     * Test of equal method, of class StringUtil.
     */
    @Test
    public void testEqual_3args_1()
    {

    }

    /**
     * Test of indexOf method, of class StringUtil.
     */
    @Test
    public void testIndexOf()
    {

    }

    /**
     * Test of lastIndexOf method, of class StringUtil.
     */
    @Test
    public void testLastIndexOf()
    {

    }

    /**
     * Test of insert method, of class StringUtil.
     */
    @Test
    public void testInsert()
    {

    }

    /**
     * Test of deleteFirst method, of class StringUtil.
     */
    @Test
    public void testDeleteFirst()
    {

    }

    /**
     * Test of deleteLast method, of class StringUtil.
     */
    @Test
    public void testDeleteLast()
    {

    }

    /**
     * Test of deleteAll method, of class StringUtil.
     */
    @Test
    public void testDeleteAll()
    {

    }

    /**
     * Test of trim method, of class StringUtil.
     */
    @Test
    public void testTrim_StringArr()
    {

    }

    /**
     * Test of trim method, of class StringUtil.
     */
    @Test
    public void testTrim_1args_1()
    {

    }

    /**
     * Test of trim method, of class StringUtil.
     */
    @Test
    public void testTrim_1args_2()
    {

    }

    /**
     * Test of equal method, of class StringUtil.
     */
    @Test
    public void testEqual_3args_2()
    {

    }

    /**
     * Test of isPrintableAscii method, of class StringUtil.
     */
    @Test
    public void testIsPrintableAscii()
    {

    }
    
}
