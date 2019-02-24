package com.tg.rpstest.server.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tg.rpstest.error.FileProcessorException;
import com.tg.rpstest.server.domain.PlayerData;
import com.tg.rpstest.server.io.TextFileProcessor;


/**
 * PlayerDataRepositoryTest class - Test PlayerDataRepository class.
 */
@ExtendWith(MockitoExtension.class)
public class PlayerDataRepositoryTest
{

    @Mock
    private TextFileProcessor ioFileHandler;

    private static final String MOCK_FILE_PATH1 = "src/test/resources/player-repo-test.txt";
    private static final String MOCK_FILE_PATH2 = "src/test/resources/player-repo-write-test.txt";
    private static final String MOCK_NAME1 = "Buzz";
    private static final String MOCK_NAME2 = "Woody";
    private static final String MOCK_WINS1 = "5";
    private static final String MOCK_WINS2 = "4";
    private static final String MOCK_LOSSES1 = "2";
    private static final String MOCK_LOSSES2 = "1";
    private static final List<String> MOCK_RECORD1;
    private static final List<String> MOCK_RECORD2;
    private static final String MOCK_NAME_HEADER = "PlayerName";
    private static final String MOCK_WINS_HEADER = "Wins";
    private static final String MOCK_LOSSES_HEADER = "Losses";
    private static final List<String> MOCK_RECORD_HEADER;
    private static final List<List<String>> MOCK_DATA;
    private static final List<List<String>> MOCK_EMPTY_DATA;
    private static final PlayerData MOCK_PLAYER_DATA1;
    private static final PlayerData MOCK_PLAYER_DATA2;
    private static final List<PlayerData> MOCK_PLAYERS_DATA;

    static
    {
        MOCK_RECORD1 = Arrays.asList(MOCK_NAME1, MOCK_WINS1, MOCK_LOSSES1);
        MOCK_RECORD2 = Arrays.asList(MOCK_NAME2, MOCK_WINS2, MOCK_LOSSES2);
        MOCK_RECORD_HEADER = Arrays.asList(MOCK_NAME_HEADER, MOCK_WINS_HEADER, MOCK_LOSSES_HEADER);
        MOCK_DATA = Arrays.asList(MOCK_RECORD_HEADER, MOCK_RECORD1, MOCK_RECORD2);
        MOCK_EMPTY_DATA = Collections.singletonList(MOCK_RECORD_HEADER);
        MOCK_PLAYER_DATA1 = generatePlayerData(MOCK_NAME1, MOCK_WINS1, MOCK_LOSSES1);
        MOCK_PLAYER_DATA2 = generatePlayerData(MOCK_NAME2, MOCK_WINS2, MOCK_LOSSES2);
        MOCK_PLAYERS_DATA = Arrays.asList(MOCK_PLAYER_DATA1, MOCK_PLAYER_DATA2);
    }

    private static PlayerData generatePlayerData(final String name, final String wins, final String losses)
    {
        return new PlayerData.PlayerDataBuilder()
            .withName(name)
            .withWins(Integer.parseInt(wins))
            .withLosses(Integer.parseInt(losses))
            .build();
    }

    private PlayerDataRepository playerDataRepository;

    @BeforeEach
    public void setUp()
    {
        this.playerDataRepository = new PlayerDataRepository(ioFileHandler, MOCK_FILE_PATH1);
    }

    // findAll - ok
    @Test
    public void givenValidData_whenFindAllPlayersData_thenReturnAllPlayersData() throws FileProcessorException
    {
        // given
        final List<PlayerData> expectedPlayersData = MOCK_PLAYERS_DATA;
        Mockito.when(ioFileHandler.readDataFromFile(MOCK_FILE_PATH1)).thenReturn(MOCK_DATA);

        // when
        final List<PlayerData> playersData = playerDataRepository.findAll();

        // then
        assertFalse(playersData.isEmpty());
        assertEquals(expectedPlayersData.size(), playersData.size());
        assertEquals(expectedPlayersData, playersData);
    }

    // findAll - nok
    @Test
    public void givenExceptionReadingData_whenFindAllPlayersData_thenThrowSpecificException() throws FileProcessorException
    {
        // given
        Mockito.when(ioFileHandler.readDataFromFile(MOCK_FILE_PATH1)).thenThrow(FileProcessorException.class);

        // when + then
        Assertions.assertThrows(FileProcessorException.class, () -> playerDataRepository.findAll());
    }

    // findAll - no data
    @Test
    public void givenEmptyData_whenFindAllPlayersData_thenReturnEmptyPlayersData() throws FileProcessorException
    {
        // given
        final List<PlayerData> expectedPlayersData = Collections.emptyList();
        Mockito.when(ioFileHandler.readDataFromFile(MOCK_FILE_PATH1)).thenReturn(MOCK_EMPTY_DATA);

        // when
        final List<PlayerData> playersData = playerDataRepository.findAll();

        // then
        assertTrue(playersData.isEmpty());
        assertEquals(expectedPlayersData.size(), playersData.size());
        assertEquals(expectedPlayersData, playersData);
    }

    // saveAll - ok
    @Test
    public void givenValidData_whenSaveAllPlayersData_thenSaveCorrectly() throws FileProcessorException
    {
        // given
        final PlayerDataRepository repository = new PlayerDataRepository(ioFileHandler, MOCK_FILE_PATH2);
        final List<PlayerData> expectedPlayersData = MOCK_PLAYERS_DATA;
        Mockito.doCallRealMethod().when(ioFileHandler).writeDataToFile(MOCK_FILE_PATH2, MOCK_DATA);
        Mockito.doCallRealMethod().when(ioFileHandler).readDataFromFile(MOCK_FILE_PATH2);

        // when
        Assertions.assertDoesNotThrow(() -> repository.saveAll(expectedPlayersData));

        // then
        final List<PlayerData> writtenPlayersData = repository.findAll();
        assertFalse(writtenPlayersData.isEmpty());
        assertEquals(expectedPlayersData.size(), writtenPlayersData.size());
        assertEquals(expectedPlayersData, writtenPlayersData);
    }

    // saveAll - nok
    @Test
    public void givenExceptionWritingData_whenSaveAllPlayersData_thenThrowSpecificException() throws FileProcessorException
    {
        final PlayerDataRepository repository = new PlayerDataRepository(ioFileHandler, MOCK_FILE_PATH2);

        // given
        Mockito.doThrow(FileProcessorException.class).when(ioFileHandler).writeDataToFile(MOCK_FILE_PATH2, MOCK_DATA);

        // when + then
        Assertions.assertThrows(FileProcessorException.class, () -> repository.saveAll(MOCK_PLAYERS_DATA));
    }

    // saveAll - no data
    @Test
    public void givenNullData_whenSaveAllPlayersData_thenEmptyFile() throws FileProcessorException, IOException
    {
        // given
        final PlayerDataRepository repository = new PlayerDataRepository(ioFileHandler, MOCK_FILE_PATH2);
        Files.deleteIfExists(new File(MOCK_FILE_PATH2).toPath()); // setup part - if file already exists, remove
        Mockito.doCallRealMethod().when(ioFileHandler).writeDataToFile(MOCK_FILE_PATH2, MOCK_EMPTY_DATA);
        Mockito.doCallRealMethod().when(ioFileHandler).readDataFromFile(MOCK_FILE_PATH2);

        // when
        Assertions.assertDoesNotThrow(() -> repository.saveAll(null));

        // then
        final List<PlayerData> writtenPlayersData = repository.findAll();
        assertTrue(writtenPlayersData.isEmpty());
    }

    @Test
    public void givenEmptyData_whenSaveAllPlayersData_thenEmptyFile() throws FileProcessorException, IOException
    {
        // given
        final PlayerDataRepository repository = new PlayerDataRepository(ioFileHandler, MOCK_FILE_PATH2);
        Files.deleteIfExists(new File(MOCK_FILE_PATH2).toPath()); // setup part - if file already exists, remove
        Mockito.doCallRealMethod().when(ioFileHandler).writeDataToFile(MOCK_FILE_PATH2, MOCK_EMPTY_DATA);
        Mockito.doCallRealMethod().when(ioFileHandler).readDataFromFile(MOCK_FILE_PATH2);

        // when
        Assertions.assertDoesNotThrow(() -> repository.saveAll(Collections.emptyList()));

        // then
        final List<PlayerData> writtenPlayersData = repository.findAll();
        assertTrue(writtenPlayersData.isEmpty());
    }
}
