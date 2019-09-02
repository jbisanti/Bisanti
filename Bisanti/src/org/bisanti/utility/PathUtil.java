package org.bisanti.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author jason
 */
public class PathUtil 
{        
    private PathUtil(){}
    
    public static Set<Path> getFiles(Path path) throws IOException
    {
        return getChildren(path, (p)->!Files.isDirectory(p));
    }
    
    public static Set<Path> getDirectories(Path path) throws IOException
    {
        return getChildren(path, new DirectoryPredicate(path));
    }
    
    public static Set<Path> getChildren(Path path) throws IOException
    {
        return getChildren(path, new SingleDepth(path));
    }
    
    public static Set<Path> getChildren(Path path, Predicate<Path> predicate) throws IOException
    {
        if(Files.isDirectory(path))
        {
            return Files.walk(path, 1).filter(predicate).collect(Collectors.toSet());
        }
        else
        {
            return Collections.emptySet();
        }   
    }

    public static class SingleDepth implements Predicate<Path>
    {
        protected final Path directory;
        
        public SingleDepth(Path directory)
        {
            this.directory = directory;
        }
        
        @Override
        public boolean test(Path t) 
        {
            return !Util.equal(this.directory, t);
        }        
    }
    
    public static class DirectoryPredicate extends SingleDepth
    {        
        private DirectoryPredicate(Path directory) 
        {
            super(directory);
        }

        @Override
        public boolean test(Path t) 
        {
            return super.test(t) && Files.isDirectory(t);
        } 
    } 
    
}
