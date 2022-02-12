/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdb.files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link FileVisitor} implementation that counts the files, folders, and 
 * total size of the passed-in root directory. See methods {@link #getFiles()},
 * {@link #getFolders()}, {@link #getTotalSize()}, and {@link #getErrors()}.
 * 
 * @author jason
 */
public class FileCountVisitor implements FileVisitor<Path>
{
    protected final AtomicLong files = new AtomicLong(0);
    
    protected final AtomicLong folders = new AtomicLong(0);
    
    protected final AtomicLong totalSize = new AtomicLong(0);
    
    protected final ConcurrentMap<Path, IOException> errors = new ConcurrentHashMap<>();
    
    protected final Path root;
    
    public FileCountVisitor(Path root)
    {
        this.root = root;
    }
    
    @Override
    public FileVisitResult preVisitDirectory(Path t, BasicFileAttributes bfa) throws IOException
    {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path t, BasicFileAttributes bfa) throws IOException
    {
        this.files.incrementAndGet();
        this.totalSize.addAndGet(bfa.size());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path t, IOException ioe)
    {
        this.errors.put(t, ioe);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path t, IOException ioe) throws IOException
    {
        this.folders.incrementAndGet();
        return FileVisitResult.CONTINUE;
    }

    public Number getFiles()
    {
        return files.get();
    }

    public Number getFolders()
    {
        return folders.get();
    }

    public Number getTotalSize()
    {
        return totalSize.get();
    }

    public ConcurrentMap<Path, IOException> getErrors()
    {
        return errors;
    }

    public Path getRoot()
    {
        return root;
    }
    
}
