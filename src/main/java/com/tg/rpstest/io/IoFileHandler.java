package com.tg.rpstest.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *
 */
public class IoFileHandler
{
    private static final String FILE_DELIMITER = ";";

    public List<List<String>> readFromFile(final String fileName) throws IoFileHandlerException
    {
        List<List<String>> data = null;
        try (final Stream<String> stream = Files.lines(Paths.get(fileName)))
        {
            data = stream.map(record ->
                Arrays.asList(record.split(FILE_DELIMITER)))
                .collect(Collectors.toList());
        }
        catch (final IOException e)
        {
            throw new IoFileHandlerException("Unable to read data from file");
        }

        return data;
    }

    public void writeToFile(final String fileName, final List<List<String>> data) throws IoFileHandlerException
    {
        try (final BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName)))
        {
            for (final List<String> record : data)
            {
                writer.write(String.join(FILE_DELIMITER, record));
                writer.newLine();
            }
        }
        catch (final IOException e)
        {
            throw new IoFileHandlerException("Unable to write data to file");
        }
    }
}
