package com.otakumap.domain.event_like.controller;

import com.otakumap.domain.event_like.dto.EventLikeResponseDTO;
import com.otakumap.domain.event_like.service.EventLikeCommandService;
import com.otakumap.domain.event_like.service.EventLikeQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.validation.annotation.ExistEventLike;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class EventLikeController {
    private final EventLikeQueryService eventLikeQueryService;
    private final EventLikeCommandService eventLikeCommandService;

    // 로그인 시 엔드포인트 변경 예정
    @Operation(summary = "저장된 이벤트 목록 조회", description = "저장된 이벤트 목록을 불러옵니다.")
    @GetMapping( "/users/{userId}/saved-events")
    @Parameters({
            @Parameter(name = "userId", description = "사용자 ID"),
            @Parameter(name = "type", description = "이벤트 타입 -> 1: 팝업 스토어, 2: 전시회, 3: 콜라보 카페"),
            @Parameter(name = "lastId", description = "마지막으로 조회된 저장된 이벤트 id, 처음 가져올 때 -> 0"),
            @Parameter(name = "limit", description = "한 번에 조회할 최대 이벤트 수. 기본값은 10입니다.")
    })
    public ApiResponse<EventLikeResponseDTO.EventLikePreViewListDTO> getEventLikeList(@PathVariable Long userId, @RequestParam(required = false) Integer type, @RequestParam(defaultValue = "0") Long lastId, @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(eventLikeQueryService.getEventLikeList(userId, type, lastId, limit));
    }

    @Operation(summary = "저장된 이벤트 삭제", description = "저장된 이벤트를 삭제합니다.")
    @DeleteMapping("/saved-events")
    @Parameters({
            @Parameter(name = "eventIds", description = "저장된 이벤트 id List"),
    })
    public ApiResponse<String> deleteEventLike(@RequestParam(required = false) @ExistEventLike List<Long> eventIds) {
        eventLikeCommandService.deleteEventLike(eventIds);
        return ApiResponse.onSuccess("저장된 이벤트가 성공적으로 삭제되었습니다");
    }
}