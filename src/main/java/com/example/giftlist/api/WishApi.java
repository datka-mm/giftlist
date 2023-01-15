package com.example.giftlist.api;

import com.example.giftlist.db.services.WishService;
import com.example.giftlist.dto.request.WishRequest;
import com.example.giftlist.dto.response.InnerWishResponse;
import com.example.giftlist.dto.response.SimpleResponse;
import com.example.giftlist.dto.response.WishResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wish-list")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Wish Api", description = "Wish CRUD")
@PreAuthorize("hasAuthority('USER')")
public class WishApi {

    private final WishService wishService;

    @Operation(summary = "Save wish", description = "User can save wish")
    @PostMapping
    public WishResponse saveWish(@RequestBody WishRequest wishRequest) {
        return wishService.saveWish(wishRequest);
    }

    @Operation(summary = "Update wish", description = "User can update information only own wish")
    @PutMapping("/{id}")
    public WishResponse updateWishById(@PathVariable Long id,
                                       @RequestBody WishRequest wishRequest) {
        return wishService.update(id, wishRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "Delete wish", description = "User can delete wishlist, when we delete wish holiday and user will not be deleted")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteWishById(@PathVariable Long id) {
        return wishService.deleteWishById(id);
    }

    @Operation(summary = "Get wish", description = "User can get wish by id")
    @GetMapping("/{id}")
    public InnerWishResponse getById(@PathVariable Long id) {
        return wishService.findById(id);
    }

    @Operation(summary = "Get all wishes", description = "User can get all wishes")
    @GetMapping
    public List<WishResponse> getAllWishes() {
        return wishService.findAll();
    }
}