package com.example.giftlist.api;

import com.example.giftlist.db.services.FeedService;
import com.example.giftlist.dto.response.AllFeedResponse;
import com.example.giftlist.dto.response.InnerFeedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Feed Api", description = "User can see feed")
public class FeedApi {

    private final FeedService feedService;

    @Operation(summary = "Get wish by id", description = "User can see the wishes of other users")
    @GetMapping("/{id}")
    public InnerFeedResponse getById(@PathVariable Long id) {
        return feedService.getById(id);
    }

    @Operation(summary = "Get wishes of all users", description = "User can see all users wish")
    @GetMapping
    public AllFeedResponse getAllWishes() {
        return feedService.feedResponse();
    }
}