/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdb.utility;

import com.jdb.utility.FileUtil;
import com.jdb.utility.PathUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jason
 */
public class PathUtilTest {
    
    public PathUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getChildren method, of class PathUtil.
     */
    @Test
    public void testGetChildren() throws Exception {
        System.out.println("getChildren");
        Path path = Paths.get(FileUtil.USER_HOME);
        Set<Path> result = PathUtil.getChildren(path);
        assertEquals(false, result.contains(path));
    }
    
    
    
}
