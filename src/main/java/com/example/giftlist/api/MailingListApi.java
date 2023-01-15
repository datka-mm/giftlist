package com.example.giftlist.api;

import com.example.giftlist.db.services.MailingListService;
import com.example.giftlist.dto.request.MailingListRequest;
import com.example.giftlist.dto.response.AllMailingListResponse;
import com.example.giftlist.dto.response.MailingListResponse;
import com.example.giftlist.dto.response.SimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Mailing list Api", description = "Admin can add mailing list")
@RequestMapping("api/mailing-list")
public class MailingListApi {

    private final MailingListService mailingListService;

    @Operation(summary = "Save mailing list", description = "Can save mailing list  ")
    @PostMapping
    public AllMailingListResponse saveMailingList(@RequestBody MailingListRequest request) {
        return mailingListService.saveMailingList(request);
    }

    @Operation(summary = "Mailing list", description = "Get mailing list by id")
    @GetMapping("{id}")
    public MailingListResponse getById(@PathVariable Long id) {
        return mailingListService.getId(id);
    }

    @Operation(summary = "All mailing lists", description = "Show all mailing-list")
    @GetMapping
    public List<AllMailingListResponse> getAllMailingLists() {
        return mailingListService.getAllMailingLists();
    }
    @Operation(summary = "Delete mailing list",description = "Admin can delete mailing list")
    @DeleteMapping("{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return mailingListService.delete(id);
    }
}