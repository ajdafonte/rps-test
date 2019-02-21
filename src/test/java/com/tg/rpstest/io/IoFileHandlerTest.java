package com.tg.rpstest.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 */
public class IoFileHandlerTest
{
    private static final String MOCK_FILE_PATH = "src/test/resources/io-test.txt";
    private static final String MOCK_NEW_FILE_PATH = "src/test/resources/new-io-test.txt";
    //    private static final String MOCK_UNKNOWN_FILE_PATH = "src/test/resources/io-unknown.txt";
    private static final String MOCK_NAME1 = "Buzz";
    private static final String MOCK_NAME2 = "Woody";
    private static final String MOCK_NAME3 = "Bob";
    private static final String MOCK_NAME_HEADER = "PlayerName";
    private static final String MOCK_WINS_HEADER = "Wins";
    private static final String MOCK_LOSSES_HEADER = "Losses";
    private static final String MOCK_WINS1 = "5";
    private static final String MOCK_WINS2 = "4";
    private static final String MOCK_WINS3 = "3";
    private static final String MOCK_LOSSES1 = "2";
    private static final String MOCK_LOSSES2 = "1";
    private static final String MOCK_LOSSES3 = "3";
    private static final List<List<String>> MOCK_READ_DATA;
    private static final List<List<String>> MOCK_WRITE_DATA;
    private static final List<String> MOCK_RECORD1;
    private static final List<String> MOCK_RECORD2;
    private static final List<String> MOCK_RECORD3;
    private static final List<String> MOCK_RECORD_HEADER;

    private IoFileHandler ioFileHandler;

    static
    {
        MOCK_RECORD1 = Arrays.asList(MOCK_NAME1, MOCK_WINS1, MOCK_LOSSES1);
        MOCK_RECORD2 = Arrays.asList(MOCK_NAME2, MOCK_WINS2, MOCK_LOSSES2);
        MOCK_RECORD_HEADER = Arrays.asList(MOCK_NAME_HEADER, MOCK_WINS_HEADER, MOCK_LOSSES_HEADER);
        MOCK_READ_DATA = Arrays.asList(MOCK_RECORD_HEADER, MOCK_RECORD1, MOCK_RECORD2);
        MOCK_RECORD3 = Arrays.asList(MOCK_NAME3, MOCK_WINS3, MOCK_LOSSES3);
        MOCK_WRITE_DATA = Arrays.asList(MOCK_RECORD_HEADER, MOCK_RECORD3, MOCK_RECORD2, MOCK_RECORD1);
    }

    @BeforeEach
    public void setUp()
    {
        this.ioFileHandler = new IoFileHandler();
    }

    // read data ok
    @Test
    public void givenFile_whenReadingFromFile_thenReturnDataRead() throws IoFileHandlerException
    {
        // given
        final List<List<String>> expectedData = MOCK_READ_DATA;

        // when
        final List<List<String>> data = ioFileHandler.readFromFile(MOCK_FILE_PATH);

        // then
        assertFalse(data.isEmpty());
        assertEquals(expectedData.size(), data.size());
        assertEquals(expectedData, data);
    }

    // write data ok
    @Test
    public void givenFile_whenWritingToFile_thenDataCorrectlyWritten() throws IoFileHandlerException, IOException
    {
        // given
        final String fileName = MOCK_NEW_FILE_PATH;
        Files.deleteIfExists(new File(fileName).toPath()); // setup part - if file already exists, remove
        final List<List<String>> dataToWrite = MOCK_WRITE_DATA;

        // when
        ioFileHandler.writeToFile(fileName, dataToWrite);

        // then
        final List<List<String>> expectedData = ioFileHandler.readFromFile(fileName);

        assertFalse(expectedData.isEmpty());
        assertEquals(expectedData.size(), dataToWrite.size());
        assertEquals(expectedData, dataToWrite);
    }
}
