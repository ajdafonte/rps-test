package com.tg.rpstest.server.io;

import com.tg.rpstest.error.FileProcessorException;


/**
 *
 */
public interface FileProcessor<T>
{
    T readDataFromFile(final String fileName) throws FileProcessorException;

    void writeDataToFile(final String fileName, final T data) throws FileProcessorException;
}
