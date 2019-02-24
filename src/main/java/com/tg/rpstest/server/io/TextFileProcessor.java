package com.tg.rpstest.server.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tg.rpstest.error.FileProcessorException;


/**
 * TODO - WIP - Add capability to store scores after each game
 */
public class TextFileProcessor implements FileProcessor<List<List<String>>>
{
    private static final String FILE_DELIMITER = ";";

    @Override
    public List<List<String>> readDataFromFile(final String fileName) throws FileProcessorException
    {
        final List<List<String>> data;
        try (final Stream<String> stream = Files.lines(Paths.get(fileName)))
        {
            data = stream.map(record ->
                Arrays.asList(record.split(FILE_DELIMITER)))
                .collect(Collectors.toList());
        }
        catch (final IOException e)
        {
            throw new FileProcessorException("Unable to read data from file");
        }

        return data;
    }

    @Override
    public void writeDataToFile(final String fileName, final List<List<String>> data) throws FileProcessorException
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
            throw new FileProcessorException("Unable to write data to file");
        }
    }

    // TODO - WIP - Add capability to store registered user scores after each game
//    public void writeRecord(final List<String> record)
//    {
//        final List<String> currentData = Files.readAllLines(filePath);
//        boolean appendMode = true;
//
//        for (int i = 0; i < currentData.size(); i++)
//        {
//            if (currentData.get(i).startsWith(record.get(0)))
//            {
//                currentData.set(i, String.join(FILE_DELIMITER, record));
//                appendMode = false;
//                break;
//            }
//        }
//
//        Files.write(filePath, currentData,
//            appendMode ? StandardOpenOption.APPEND : Collections.emptyList());
//    }
}
