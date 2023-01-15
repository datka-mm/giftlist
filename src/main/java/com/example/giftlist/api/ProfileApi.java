package com.example.giftlist.api;

import com.example.giftlist.db.services.UserProfileService;
import com.example.giftlist.dto.request.ProfileRequest;
import com.example.giftlist.dto.response.FriendProfileResponse;
import com.example.giftlist.dto.response.MyProfileResponse;
import com.example.giftlist.dto.response.ProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Profile API", description = "Can save, update profile")
@PreAuthorize("hasAuthority('USER')")
public class ProfileApi {

    private final UserProfileService service;

    @Operation(summary = "Save profile", description = "Can save profile  ")
    @PostMapping
    public ProfileResponse saveUserInfo(@RequestBody ProfileRequest request) {
        return service.saveProfile(request);
    }

    @Operation(summary = "Update profile", description = "Can update profile")
    @PutMapping("{id}")
    public ProfileResponse updateProfileUser(@PathVariable Long id,
                                             @RequestBody ProfileRequest request) {
        return service.saveUpdateUser(id, request);
    }

    @Operation(summary = "Friends profile", description = "Can see friends profile")
    @GetMapping("{id}")
    public FriendProfileResponse FriendProfile(@PathVariable Long id) {
        return service.friendProfile(id);
    }

    @Operation(summary = "My profile", description = "Can see profile")
    @GetMapping("/me")
    public MyProfileResponse myProfile() {
        return service.myProfile();
    }
    @Operation(summary = "Full Info My Profile ", description = "Can see full info my profile")
    @GetMapping()
    public ProfileResponse fullInfoMyProfile(){
        return service.getFullInfoMyProfile();
    }
}