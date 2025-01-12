package com.otakumap.domain.eventLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EventLocationResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventLocationDTO {
        Long id;
        String name;
        String longitude;
        String latitude;
    }
}
