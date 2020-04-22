package com.monolit.gamesjar.backend.communicator.mapper;

import com.monolit.gamesjar.backend.communicator.domain.History;
import com.monolit.gamesjar.backend.communicator.domain.HistoryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryMapper {

    public History mapToHistory(final HistoryDto historyDto) {
        return new History(
                historyDto.getId(),
                historyDto.getHistory()
        );
    }

    public HistoryDto mapToHistoryDto(final History history) {
        return new HistoryDto(
                history.getId(),
                history.getHistory()
        );
    }

    public List<HistoryDto> mapToHistoryDtoList(final List<History> histories) {
        return histories.stream()
                .map(history -> new HistoryDto(history.getId(), history.getHistory()))
                .collect(Collectors.toList());
    }
}
