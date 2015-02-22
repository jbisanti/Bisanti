/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jason Bisanti
 */
public class StringUtilTest
{
    private final String delimiter = ", ";

    /**
     * Test of isNullOrEmpty method, of class StringUtil.
     */
    @Test
    public void testIsNullOrEmpty()
    {
        assertEquals(true, StringUtil.isNullOrEmpty(""));
        assertEquals(true, StringUtil.isNullOrEmpty(null));
        assertEquals(false, StringUtil.isNullOrEmpty(" "));
    }

    /**
     * Test of nonNull method, of class StringUtil.
     */
    @Test
    public void testNonNull()
    {
        assertEquals("", StringUtil.nonNull(null, true));
        assertEquals("", StringUtil.nonNull(" ", true));
        assertEquals(" ", StringUtil.nonNull(" ", false));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_ObjectArr()
    {
        Object x = "x";
        Object y = "y";
        Object z = "z";
        String output = x + delimiter + y + delimiter + z;
        assertEquals(output, StringUtil.toString(delimiter, x, y, z));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_booleanArr()
    {
        String output = "true, false, true";
        assertEquals(output, StringUtil.toString(this.delimiter, new boolean[]{true, false, true}));
        assertEquals(output, StringUtil.toString(this.delimiter, true, false, true));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_charArr()
    {
        String output = "x, y, z";
        assertEquals(output, StringUtil.toString(this.delimiter, new char[]{'x', 'y', 'z'}));
        assertEquals(output, StringUtil.toString(this.delimiter, 'x', 'y', 'z'));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_byteArr()
    {
        String output = "1, 2, 3";
        assertEquals(output, StringUtil.toString(this.delimiter, new byte[]{1, 2, 3}));
        assertEquals(output, StringUtil.toString(this.delimiter, 1, 2, 3));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_shortArr()
    {
        String output = "1, 2, 3";
        assertEquals(output, StringUtil.toString(this.delimiter, new short[]{1, 2, 3}));
        assertEquals(output, StringUtil.toString(this.delimiter, 1, 2, 3));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_intArr()
    {
        String output = "1, 2, 3";
        assertEquals(output, StringUtil.toString(this.delimiter, new int[]{1, 2, 3}));
        assertEquals(output, StringUtil.toString(this.delimiter, 1, 2, 3));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_longArr()
    {
        String output = "1, 2, 3";
        assertEquals(output, StringUtil.toString(this.delimiter, new long[]{1, 2, 3}));
        assertEquals(output, StringUtil.toString(this.delimiter, 1, 2, 3));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_floatArr()
    {
        String output = "1.0, 2.0, 3.0";
        assertEquals(output, StringUtil.toString(this.delimiter, new float[]{1, 2, 3}));
        assertEquals(output, StringUtil.toString(this.delimiter, 1f, 2f, 3f));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_CharSequence_doubleArr()
    {
        String output = "1.0, 2.0, 3.0";
        assertEquals(output, StringUtil.toString(this.delimiter, new double[]{1, 2, 3}));
        assertEquals(output, StringUtil.toString(this.delimiter, 1d, 2d, 3d));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_Collection_CharSequence()
    {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertEquals("1, 2, 3", StringUtil.toString(list, delimiter));
    }

    /**
     * Test of toString method, of class StringUtil.
     */
    @Test
    public void testToString_3args()
    {
        SortedMap<Integer, Double> map = new TreeMap<Integer, Double>();
        map.put(1, 1d);
        map.put(2, 2d);
        map.put(3, 3d);
        assertEquals("1:1.0, 2:2.0, 3:3.0", StringUtil.toString(map, ":", delimiter));
    }

    /**
     * Test of equal method, of class StringUtil.
     */
    @Test
    public void testEqual_3args_1()
    {
       String match = "mAtcH";
       assertEquals(true, StringUtil.equal(true, match, match));
       assertEquals(true, StringUtil.equal(false, match, "fdsafds", "fdaferwe", "23432", "match", "89865"));
       assertEquals(false, StringUtil.equal(true, match, "fdsafds", "fdaferwe", "23432", "match", "89865"));
       assertEquals(true, StringUtil.equal(true, match, "fdsafds", "fdaferwe", "23432", match, "89865"));
    }

    /**
     * Test of indexOf method, of class StringUtil.
     */
    @Test
    public void testIndexOf_3args()
    {
        String container = "jfkdsjrioewqruwinbrueiwqrBisantirierqne54254325";
        final String substring = "BISANTI";
        assertEquals(true, StringUtil.indexOf(false, container, substring) > -1);
        assertEquals(-1, StringUtil.indexOf(true, container, substring));
        container+=substring;
        assertEquals(true, StringUtil.indexOf(false, container, substring) > -1);
    }

    /**
     * Test of indexOf method, of class StringUtil.
     */
    @Test
    public void testIndexOf_4args()
    {
        final String container = "jfkdsjrioewqruwinbrueiwqrrewrewrierqne54254325BISANTI";
        String substring = "BISANTIduiuiuiuiuipupioupiouiop";
        assertEquals(-1, StringUtil.indexOf(false, 10, container, substring));
        substring = "BISANTI";
        assertEquals(container.length() - substring.length(), StringUtil.indexOf(false, 10, container, substring));
        assertEquals(-1, StringUtil.indexOf(true, 10, container, substring.toLowerCase()));
    }

    /**
     * Test of lastIndexOf method, of class StringUtil.
     */
    @Test
    public void testLastIndexOf_3args()
    {
        final String container = "abcabcABC";
        final String substring = "ab";
        assertEquals(6, StringUtil.lastIndexOf(false, container, substring));
        assertEquals(3, StringUtil.lastIndexOf(true, container, substring));
        assertEquals(-1, StringUtil.lastIndexOf(false, "43124321423147123423141234", substring));
    }

    /**
     * Test of lastIndexOf method, of class StringUtil.
     */
    @Test
    public void testLastIndexOf_4args()
    {
        final String container = "abcABCabc";
        String substring = "ab";
        assertEquals(3, StringUtil.lastIndexOf(false, 4, container, substring));
        assertEquals(0, StringUtil.lastIndexOf(true, 4, container, substring));
    }

    /**
     * Test of insert method, of class StringUtil.
     */
    @Test
    public void testInsert()
    {
        String insertion = "abc";
        CharSequence container = "123456";
        final String combined = "123abc456";
        assertEquals(combined, StringUtil.insert(container, 3, insertion));
        StringBuilder stringBuilder = new StringBuilder(container);
        assertEquals(combined, StringUtil.insert(stringBuilder, 3, insertion).toString());
    }

    /**
     * Test of deleteFirst method, of class StringUtil.
     */
    @Test
    public void testDeleteFirst()
    {
        String s = "ABC123abc";
        assertEquals("123abc", StringUtil.deleteFirst(false, s, "abc"));
        assertEquals("ABC123", StringUtil.deleteFirst(true, s, "abc"));
        assertEquals(s, StringUtil.deleteFirst(false, s, "0000"));
    }

    /**
     * Test of deleteLast method, of class StringUtil.
     */
    @Test
    public void testDeleteLast()
    {
        String s = "ABC123abc";
        assertEquals("ABC123", StringUtil.deleteLast(false, s, "ABC"));
        assertEquals("123abc", StringUtil.deleteLast(true, s, "ABC"));
        assertEquals(s, StringUtil.deleteLast(true, s, "000"));
    }

    /**
     * Test of deleteAll method, of class StringUtil.
     */
    @Test
    public void testDeleteAll()
    {
        String s = "ABC123abc";
        assertEquals("123", StringUtil.deleteAll(false, s, "abc"));
        assertEquals("ABC123", StringUtil.deleteAll(true, s, "abc"));
        assertEquals(s, StringUtil.deleteLast(true, s, "000"));
    }

    /**
     * Test of trim method, of class StringUtil.
     */
    @Test
    public void testTrim_StringArr()
    {
        String[] array = new String[]{"123   ", "  123", "123", " 123 "};
        StringUtil.trim(array);
        for(String s: array)
        {
            assertEquals("123", s);
        }
    }

    /**
     * Test of trim method, of class StringUtil.
     */
    @Test
    public void testTrim_1args_1()
    {
        List<String> list = Arrays.asList("123  ", " 123", "123", "  123  ");
        StringUtil.trim(list);
        for(String s: list)
        {
            assertEquals("123", s);
        }
    }

    /**
     * Test of trim method, of class StringUtil.
     */
    @Test
    public void testTrim_1args_2()
    {
        Set<String> set = new HashSet<String>(Arrays.asList("123  ", " 123", "123", "  123  "));
        set = StringUtil.trim(set);
        assertEquals(1, set.size());
        assertEquals("123", set.iterator().next());
    }

    /**
     * Test of equal method, of class StringUtil.
     */
    @Test
    public void testEqual_3args_2()
    {
        assertEquals(false, StringUtil.equal(false, 'z', 'a'));
        assertEquals(true, StringUtil.equal(false, 'A', 'a'));
        assertEquals(false, StringUtil.equal(true, 'A', 'a'));
    }

    /**
     * Test of isPrintableAscii method, of class StringUtil.
     */
    @Test
    public void testIsPrintableAscii()
    {
        assertEquals(true, StringUtil.isPrintableAscii('a'));
        assertEquals(false, StringUtil.isPrintableAscii('\n'));
    }
    
}
