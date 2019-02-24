package com.tg.rpstest.server.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tg.rpstest.error.FileProcessorException;
import com.tg.rpstest.server.domain.PlayerData;
import com.tg.rpstest.server.io.TextFileProcessor;


/**
 * TODO - WIP - Add capability to store scores after each game
 */
public class PlayerDataRepository
{
    private final TextFileProcessor textFileProcessor;
    private final String filePath;

    public PlayerDataRepository(final TextFileProcessor textFileProcessor, final String filePath)
    {
        this.textFileProcessor = textFileProcessor;
        this.filePath = filePath;
    }

    public List<PlayerData> findAll() throws FileProcessorException
    {
        final List<List<String>> dataRead = textFileProcessor.readDataFromFile(filePath);

        // skip first line - header from file
        return PlayerDataMapper.mapFromReader(dataRead.subList(1, dataRead.size()));
    }

    public void saveAll(final List<PlayerData> playersData) throws FileProcessorException
    {
        // content to save (need to append data after header)
        final List<List<String>> dataToWrite = new ArrayList<>();
        dataToWrite.add(PlayerDataMapper.headerRecordToWriter());
        if (playersData != null)
        {
            dataToWrite.addAll(PlayerDataMapper.mapToWriter(playersData));
        }

        // add content
        textFileProcessor.writeDataToFile(filePath, dataToWrite);
    }

    // TODO - WIP - Add capability to store registered user scores after each game
//    public void save(final PlayerData playersData)
//    {
//        final List<String> entity = PlayerDataMapper.mapToWriterRecord(playersData);
//
//        // add content
//        textFileProcessor.writeRecord(entity);
//    }

    private static class PlayerDataMapper
    {
        private static final int FIELD_NAME_POSITION_IN_FILE = 0;
        private static final int FIELD_WINS_POSITION_IN_FILE = 1;
        private static final int FIELD_LOSSES_POSITION_IN_FILE = 2;
        private static final String FIELD_NAME = "PlayerName";
        private static final String FIELD_WINS = "Wins";
        private static final String FIELD_LOSSES = "Losses";
        private static final List<String> HEADER_RECORD = Arrays.asList(FIELD_NAME, FIELD_WINS, FIELD_LOSSES);

        public static List<List<String>> mapToWriter(final List<PlayerData> playersData)
        {
            List<List<String>> dataMapped = null;
            if (playersData != null)
            {
                dataMapped = playersData.stream()
                    .map(PlayerDataMapper::mapToWriterRecord)
                    .collect(Collectors.toList());
            }
            return dataMapped;
        }

        private static List<String> mapToWriterRecord(final PlayerData playerData)
        {
            List<String> record = null;
            if (playerData != null)
            {
                record = new ArrayList<>();
                record.add(playerData.getName());
                record.add(String.valueOf(playerData.getWins()));
                record.add(String.valueOf(playerData.getLosses()));
            }

            return record;
        }

        public static List<PlayerData> mapFromReader(final List<List<String>> readFromFile)
        {
            return readFromFile.stream()
                .map(PlayerDataMapper::mapFromReaderRecord)
                .collect(Collectors.toList());
        }

        private static PlayerData mapFromReaderRecord(final List<String> record)
        {
            PlayerData playerData = null;
            if (!record.isEmpty())
            {
                playerData = new PlayerData.PlayerDataBuilder()
                    .withName(record.get(FIELD_NAME_POSITION_IN_FILE))
                    .withWins(Integer.parseInt(record.get(FIELD_WINS_POSITION_IN_FILE)))
                    .withLosses(Integer.parseInt(record.get(FIELD_LOSSES_POSITION_IN_FILE)))
                    .build();
            }
            return playerData;
        }

        private static List<String> headerRecordToWriter()
        {
            return HEADER_RECORD;
        }
    }
}
