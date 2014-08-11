package org.bisanti.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * @author Jason Bisanti
 */
public final class FileUtil
{
    public static final String USER = System.getProperty("user.name");
    
    public static final String MAIN_DIR = System.getProperty("user.dir");
    
    public static final String USER_HOME = System.getProperty("user.home");
    
    private FileUtil(){};
    
    public static boolean isSerializable(Object object)
    {
        return serialize(object) == null;
    }
    
    public static IOException serialize(Object object)
    {
        try
        {
            new DummyObjectStream().writeObject(object);
            return null;
        }
        catch(IOException ex)
        {
            return ex;
        }
    }
    
    public static Map<Closeable, Exception> close(Closeable... toClose)
    {
        Map<Closeable, Exception> failures = new 
                HashMap<Closeable, Exception>();
        
        if(!Util.isNullOrEmpty(toClose))
        {
            for(Closeable closer: toClose)
            {
                try
                {
                    closer.close();
                }
                catch(Exception ex)
                {
                    failures.put(closer, ex);
                }
            }
        }
        
        return failures;
    }
    
    public static void writeObjects(String filename, Object... objects) throws IOException
    {
        writeObjects(new File(filename), objects);
    }
    
    public static void writeObjects(File file, Object... objects) throws IOException
    {
        FileOutputStream fis = null;
        ObjectOutputStream oos = null;
        try
        {
            fis = new FileOutputStream(file);
            oos = new ObjectOutputStream(fis);
            for(Object object: objects)
            {
                oos.writeObject(object);
            }
        } 
        catch (IOException ex)
        {
            throw ex;
        } 
        finally
        {
            close(fis, oos);
        }
    }
    
    public static <T> List<T> readObjects(String filename, Class<T> type) throws Exception
    {
        return readObjects(new File(filename), type);
    }
    
    public static <T> List<T> readObjects(File file, Class<T> type) throws Exception
    {
        List<T> loaded = new ArrayList<T>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try
        {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while(ois.available() > 0)
            {
                loaded.add(type.cast(ois.readObject()));
            }
            return loaded;
        }
        catch(Exception ex)
        {
            throw ex;
        }
        finally
        {
            close(fis, ois);
        }
    }    
    
    public static List<String> readText(String filename) throws IOException
    {
        return readText(new File(filename));
    }
    
    public static List<String> readText(File file) throws IOException
    {
        return readText(file, 10);
    }
    
    public static List<String> readText(File file, int capacity) throws IOException
    {
        List<String> lines = new ArrayList<String>(capacity);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext())
        {
            lines.add(scanner.next());
        }
        scanner.close();
        return lines;
    }
    
    private static class DummyObjectStream extends ObjectOutputStream
    {
        public DummyObjectStream() throws IOException, SecurityException
        {
            super(new OutputStream()
            {
                @Override
                public void write(int b) throws IOException{}
            });
        }        
    }
    
}
