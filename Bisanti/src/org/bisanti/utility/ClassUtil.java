/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.utility;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * Utility class to help with {@link Class} implementation and discovery, within
 * the currently running JRE, internal/external jars, or {@link Package}s.
 * @author jason
 */
public final class ClassUtil
{
    private static final Logger LOGGER = Logger.getLogger(ClassUtil.class.getCanonicalName());
    
    /**
     * {@link Set} of all {@link Class} types that can be loaded within the
     * currently accesible environment and available to the developer. Lazily
     * instantiated via {@link #loadJavaClasses()} for performance reasons.
     */
    private static Set<Class<?>> javaClasses;
    
    /** Private constructor to prevent instantiation */
    private ClassUtil(){}
    
    /**
     * Loads all the accessible java {@link Class} types from the JRE and the
     * classpath. 
     */
    private synchronized static void loadJavaClasses()
    {
        if(javaClasses != null)
        {
            return;
        }
        
        javaClasses = new LinkedHashSet<>();
        
        Path javaHome = Paths.get(System.getProperty("java.home"), "lib");
        try
        {
            Files.list(javaHome).filter((p)->p.toString().endsWith(".jar")).forEach((p)->
            {
                try
                {
                    readJarOnClasspath(p);
                }
                catch(IOException ex)
                {
                    LOGGER.log(Level.SEVERE, "Unable to read jar file: " + p, ex);
                }
            });
        }
        catch(IOException ex)
        {
            LOGGER.log(Level.SEVERE, "Exception reading lib directory from Java Home: " + javaHome, ex);
        }
        
        for(Package packgs: Package.getPackages())
        {
            final String packageName = packgs.getName();
            final String packagePath = packageName.replace('.', '/');
            try
            {
                Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packagePath);
                while(resources.hasMoreElements())
                {
                    URL url = resources.nextElement();
                    String file = url.getFile().replace("%20", " ");
                    if(url.getProtocol().contains("jar"))
                    {
                        file = file.replace("file:", "").substring(1);
                        int index = file.indexOf("!");
                        if(index > 0)
                        {
                            file = file.substring(0, index);
                        }                        
                        javaClasses.addAll(readJarOnClasspath(Paths.get(file)));
                    }
                    else
                    {
                        Path dir = Paths.get(file.substring(1));
                        try
                        {
                            Files.list(dir).filter((p)->p.toString().endsWith(".class")).forEach((p)->
                            {
                               String clazz = packageName + "." + p.getFileName();
                               CollectionUtil.addIfNonNull(javaClasses, loadClass(clazz));
                            });
                        }
                        catch(IOException ex)
                        {
                            LOGGER.log(Level.SEVERE, "Exception reading directory: " + dir, ex);
                        }
                    }
                }
            }
            catch(IOException ex)
            {
                LOGGER.log(Level.SEVERE, "Exception reading package: " + packageName, ex);
            }
        }
    }
    
    /**
     * Returns all {@link Class} names contained in the given jar. This can be
     * any jar file on the file system.
     * 
     * @param jar {@link Path} to jar
     * @return {@link String} {@link Set} of all class names
     * @throws IOException 
     */
    public static Set<String> readJar(Path jar) throws IOException
    {
        Set<String> classes = new LinkedHashSet<>();
        try(final JarFile jarFile = new JarFile(jar.toString()))
        {
            final Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements())
            {
                JarEntry entry = entries.nextElement();
                if(StringUtil.indexOf(false, entry.getName(), ".class") > 0)
                {
                    classes.add(entry.getName().replace("/", "."));
                }
            }
        }
        return classes;
    }
    
    /**
     * Returns all classes contained in this jar that can be instantiated. THE
     * PASSED IN JAR MUST BE ON THE EXISTING CLASSPATH! If it is not, no classes
     * will be loaded. If you are simply interested in seeing the classes 
     * contained within a jar, use method {@link #readJar(Path)}.
     * 
     * @param jar {@link Path} to jar
     * @return {@link Set} of {@link Class} types that can be loaded.
     * @throws IOException
     */
    public static Set<Class<?>> readJarOnClasspath(Path jar) throws IOException
    {
        Set<Class<?>> classes = new LinkedHashSet<>();
        try(final JarFile jarFile = new JarFile(jar.toString()))
        {
            final Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements())
            {
                JarEntry entry = entries.nextElement();
                if(StringUtil.indexOf(false, entry.getName(), ".class") > 0)
                {
                    String clazz = entry.getName().replace("/", ".");
                    CollectionUtil.addIfNonNull(classes, loadClass(clazz));
                }
            }
        }
        return classes;
    }
    
    /**
     * Helper method to load a class from its name. It does not attempt to 
     * initialize the class and uses the default {@link ClassLoader}. It first
     * attempts to load the class using 
     * {@link Class#forName(String, boolean, ClassLoader)} with false and null
     * as the second and third parameters, followed by 
     * {@link Class#forName(java.lang.String)}. If a {@link Class} cannot be
     * loaded, null is returned.
     * 
     * @param name {@link String} name of class. E.g., "com.me.pkg.Class1"
     * @return {@link Class} or null
     */
    public static Class<?> loadClass(String name)
    {
        final String clazz = name.replace(".class", "");
        final boolean finest = LOGGER.isLoggable(Level.FINEST);
        try
        {
            return Class.forName(clazz, false, null);
        }
        catch(ClassNotFoundException ex1)
        {
            if(finest)
            {
                LOGGER.log(Level.FINEST, "Exception loading class '" + name + 
                        "' with default class loader", ex1);
            }
            
            try
            {
                return Class.forName(clazz);
            }
            catch(ClassNotFoundException ex2)
            {                
                if(finest)
                {
                    LOGGER.log(Level.FINEST, "Cannot load class: " + name, ex2);
                }
                else
                {
                    LOGGER.log(Level.WARNING, "Exception loading class '" + 
                            clazz + "' enable FINEST logging for details");
                }
            }
        }
        
        return null;
    }
    
    /**
     * Finds all classes that are children of the given type.Data structures
     * for this method are lazily instantiated, so you can expect the first
     * call to this method to be slow. This was done to reduce startup time
     * for any project that has this class on its classpath.
     * 
     * @param <T>
     * @param parent Parent {@link Class} type, such as a {@link List},
     * abstract {@link Class}, or {@link Class} that has been extended
     * @param onlyConcrete If true, no interface or abstract classes will be
     * returned.
     * @return {@link Set} of all {@link Class} type that are assignable from
     * parameter parent
     */
    public static <T> Set<Class<T>> findClasses(Class<T> parent, boolean onlyConcrete)
    {
        loadJavaClasses();
        
        Set<Class<T>> classes = new LinkedHashSet<>();
        for(Class<?> c: javaClasses)
        {
            if(parent.isAssignableFrom(c))
            {
                if(onlyConcrete)
                {
                    if(!c.isInterface() && !Modifier.isAbstract(c.getModifiers()))
                    {
                        classes.add((Class<T>) c);
                    }
                }
                else
                {
                    classes.add((Class<T>) c);
                }
            }            
        }
        return classes;
    }
    
    /**
     * Finds all the classes in the given {@link Package#getName()} that can be
     * loaded. The given {@link Package} name MUST be on the classpath.
     * 
     * @param packageName Name of the {@link Package}. E.g., "com.me.pkg"
     * @param caseSensitive true if matches should be case sensitive
     * @param deepDive If true, ANY class that starts with the given package 
     * name will be included in the results. E.g., if "java.lang" is passed in,
     * all classes under java.lang, java.lang.reflect, java.lang.annotation, etc
     * will also be returned.
     * @return {@link Set} of {@link Class} types in the given package.
     */
    public static Set<Class<?>> getClasses(String packageName, boolean caseSensitive, boolean deepDive)
    {
        loadJavaClasses();

        Set<Class<?>> classes = new LinkedHashSet<>();
        for(Class<?> c: javaClasses)
        {
            if(deepDive)
            {
                if(StringUtil.startsWith(caseSensitive, c.getName(), packageName))
                {
                    classes.add(c);
                }
            }
            else
            {
                String name = c.getName();
                name = name.substring(0, name.lastIndexOf("."));
                if(StringUtil.startsWith(caseSensitive, name, packageName))
                {
                    classes.add(c);
                }
            }
        }
        return classes;
    }
    
}
