package com.example.giftlist.api;

import com.example.giftlist.db.services.BookedService;
import com.example.giftlist.dto.response.BookResponse;
import com.example.giftlist.dto.response.BookingResponse;
import com.example.giftlist.dto.response.SimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Booking Api", description = "Book wishes and copy other wishes.")
@RequestMapping("/api/bookings")
public class BookedApi {

    private final BookedService bookedService;

    @Operation(summary = "Book wishes",description = "User can book wish")
    @PostMapping("reserve/{id}")
    public SimpleResponse reservation(@PathVariable Long id,
                                      @RequestParam Boolean isAnonymous) {
        return bookedService.reserveWish(id, isAnonymous);
    }

    @Operation(summary = "Get all booked wishes",description = "User can get own booked wishes.")
    @GetMapping("wishes")
    public List<BookResponse> getAllWishes() {
        return bookedService.getAllReservedWishes();
    }

    @Operation(summary = "Get all booked gifts",description = "User can get own booked gifts.")
    @GetMapping("gifts")
    public BookingResponse getAllGifts() {
        return bookedService.getAllGifts();
    }

    @Operation(summary = "Unreserved",description = "Unreserved wish")
    @PostMapping("un-reservation/{id}")
    public SimpleResponse unReserve(@PathVariable Long id) {
        return bookedService.waitStatus(id);
    }

    @Operation(summary = "Add friend's wish to my wish",description = "User can add friend's wish to own wish")
    @PostMapping("/{id}/{holidayId}")
    public SimpleResponse addFriendWishToMyWish(@PathVariable Long id, @PathVariable Long holidayId) {
        return bookedService.saveWish(id, holidayId);
    }
}