package org.bisanti.utility;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
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
    
    /**
     * Determines if an {@link Object} is truly {@link Serializable}
     * 
     * @param object {@link Object}
     * @return true if can be serialized, false otherwise
     */
    public static boolean isSerializable(Serializable object)
    {
        return serialize(object) == null;
    }
    
    /**
     * Returns the {@link IOException} thrown if this {@link Object} cannot
     * be serialized.
     * 
     * @param object {@link Object} for serialization
     * @return {@link IOException} or null if can be serialized
     */
    public static IOException serialize(Serializable object)
    {
        try(DummyObjectStream dos = new DummyObjectStream())
        {
            dos.writeObject(object);
            return null;
        }
        catch(IOException ex)
        {
            return ex;
        }
    }
    
    /**
     * This method has been replaced with the 'try-with-resources' construct
     * available as of Java 7. E.g.:<br><code>
     * try(FileOutputStream fos = new FileOutputStream(file))<br>
     * {}<br>
     * catch(Exception ex)<br>
     * {}</code>
     * @param toClose
     * @return
     * @deprecated
     */
    @Deprecated
    public static Map<Closeable, Exception> close(Closeable... toClose)
    {
        Map<Closeable, Exception> failures = new 
                HashMap<>();
        
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
    
    /**
     * Writes the given {@link Object}s to the given {@link File}; a 
     * {@link FileOutputStream} and {@link ObjectOutputStream} are used.
     * 
     * @param filename Name of file (absolute path)
     * @param objects {@link Serializable} to serialize
     * @throws IOException 
     */
    public static void writeObjects(String filename, Serializable... objects) throws IOException
    {
        writeObjects(new File(filename), objects);
    }
    
    /**
     * Writes the given {@link Object}s to the given {@link File}; a 
     * {@link FileOutputStream} and {@link ObjectOutputStream} are used.
     * 
     * @param file {@link File}
     * @param objects {@link Serializable} to serialize
     * @throws IOException 
     */
    public static void writeObjects(File file, Serializable... objects) throws IOException
    {
        try(FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis))
        {
            for(Object object: objects)
            {
                oos.writeObject(object);
            }
        } 
        catch (IOException ex)
        {
            throw ex;
        } 
    }
    
    /**
     * Reads the given {@link Class} types from the given filename; a
     * {@link FileInputStream} and {@link ObjectInputStream} are used.
     * 
     * @param <T> 
     * @param filename Name of file (absolute path)
     * @param type {@link Class} type to read
     * @return {@link List} of {@link T}
     * @throws Exception 
     */
    public static <T extends Serializable> List<T> readObjects(String filename, Class<T> type) throws Exception
    {
        return readObjects(new File(filename), type);
    }
    
    /**
     * Reads the given {@link Class} types from the given {@link File}; a
     * {@link FileInputStream} and {@link ObjectInputStream} are used.
     * 
     * @param <T> 
     * @param file {@link File}
     * @param type {@link Class} type to read
     * @return {@link List} of {@link T}
     * @throws Exception 
     */
    public static <T extends Serializable> List<T> readObjects(File file, Class<T> type) throws Exception
    {
        List<T> loaded = new ArrayList<>();
        try(FileInputStream fis= new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis))
        {
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
    }    
    
    /**
     * <i>Use {@link Files#readAllLines(Path)}</i> instead<br><br>
     * Read all lines of text in a file
     * 
     * @param filename Name of file (absolute path)
     * @return {@link List} with each line of text
     * @throws IOException 
     * @deprecated
     */
    @Deprecated
    public static List<String> readText(String filename) throws IOException
    {
        return readText(new File(filename));
    }
    
    /**
     * <i>Use {@link Files#readAllLines(Path)}</i> instead<br><br>
     * Read all lines of text in a file
     * 
     * @param file {@link File}
     * @return {@link List} with each line of text
     * @throws IOException 
     * @deprecated
     */
    @Deprecated
    public static List<String> readText(File file) throws IOException
    {
        return readText(file, 10);
    }
    
    /**
     * <i>Use {@link Files#readAllLines(Path)}</i> instead<br><br>
     * Read all lines of text in a file
     * 
     * @param file {@link File}
     * @param capacity Initial size of backing array
     * @return {@link List} with each line of text
     * @throws IOException 
     * @deprecated
     */
    public static List<String> readText(File file, int capacity) throws IOException
    {
        List<String> lines = new ArrayList<>(capacity);
        try (Scanner scanner = new Scanner(file)) 
        {
            while(scanner.hasNext())
            {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }
    
    /**
     * Dummy class used to ensure an {@link Object} is {@link Serializable}.
     */
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
