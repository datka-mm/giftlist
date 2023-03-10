package com.example.giftlist.db.services;

import com.example.giftlist.db.models.Holiday;
import com.example.giftlist.db.models.User;
import com.example.giftlist.db.models.Wish;
import com.example.giftlist.db.repositories.GiftRepository;
import com.example.giftlist.db.repositories.HolidayRepository;
import com.example.giftlist.db.repositories.UserRepository;
import com.example.giftlist.dto.request.HolidayRequest;
import com.example.giftlist.dto.response.HolidayGiftsResponse;
import com.example.giftlist.dto.response.HolidayResponseForGet;
import com.example.giftlist.dto.response.HolidayResponses;
import com.example.giftlist.dto.response.SimpleResponse;
import com.example.giftlist.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final UserRepository userRepository;
    private final GiftRepository giftRepository;

    public String capitalize(String str) {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public User getPrinciple() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException(String.format("Пользователь с таким электронным адресом: %s не найден!", email))
        );
    }

    public HolidayResponses saveHoliday(HolidayRequest request) {
        Holiday holiday = convertToEntity(request);
        log.info("Holiday with id: {} successfully saved in the database", holiday.getId());
        return convertToResponse(holidayRepository.save(holiday));
    }

    public HolidayResponseForGet getHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Праздник с таким  id: %s не найден!", id)));
        return convertToResponseForGetById(holiday);
    }

    public SimpleResponse deleteHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Праздник с таким  id: %s не найден!", id)));
        for (Wish w : holiday.getWishes()) {
            giftRepository.deleteByWishId(w.getId());
        }
        holidayRepository.deleteByWishId(holiday.getId());
        holiday.setUser(null);
        holidayRepository.delete(holiday);
        log.info("Holiday with id: {} successfully deleted", id);
        return new SimpleResponse(
                "Удалено",
                "Праздник с id: " + holiday.getId() + " успешно удалено "
        );
    }

    public HolidayResponses saveUpdateHoliday(Long id, HolidayRequest request) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Праздник с таким  id: %s не найден!", id)));
        Holiday holiday1 = updateHoliday(holiday, request);
        log.info("Holiday with id: {} successfully updated", id);
        return convertToResponse(holiday1);
    }

    public List<HolidayResponses> getAllHolidays() {
        User user = getPrinciple();
        return holidayRepository.getAllHolidays(user.getEmail());
    }

    public Holiday updateHoliday(Holiday holiday, HolidayRequest holidayRequest) {
        holiday.setName(holidayRequest.getName());
        holiday.setDateOfHoliday(holidayRequest.getDateOfHoliday());
        holiday.setImage(holidayRequest.getImage());
        return holidayRepository.save(holiday);
    }

    public Holiday convertToEntity(HolidayRequest request) {
        User user = getPrinciple();
        Holiday holiday = new Holiday();
        holiday.setUser(user);
        holiday.setName(capitalize(request.getName()));
        holiday.setDateOfHoliday(request.getDateOfHoliday());
        holiday.setImage(request.getImage());
        holiday.setIsBlock(false);
        return holiday;
    }

    public HolidayResponseForGet convertToResponseForGetById(Holiday holiday) {
        List<HolidayGiftsResponse> wishResponses = new ArrayList<>();
        for (Wish wish : holiday.getWishes()) {
            HolidayGiftsResponse wishResponse = new HolidayGiftsResponse(wish.getId(), wish.getWishName(),
                    wish.getLinkToGift(), wish.getDateOfHoliday(), wish.getDescription(), wish.getImage(), wish.getWishStatus());
            wishResponses.add(wishResponse);
        }
        User user = getPrinciple();
        HolidayResponseForGet holidayResponse = new HolidayResponseForGet();
        holidayResponse.setId(holiday.getId());
        holidayResponse.setName(holiday.getName());
        holidayResponse.setDateOfHoliday(holiday.getDateOfHoliday());
        holidayResponse.setImage(holiday.getImage());
        holidayResponse.setUser(user.getId());
        holidayResponse.setWishResponse(wishResponses);
        holidayResponse.setHolidayIsBlock(holiday.getIsBlock());
        return holidayResponse;
    }

    public HolidayResponses convertToResponse(Holiday holiday) {
        User user = getPrinciple();
        HolidayResponses holidayResponses = new HolidayResponses();
        holidayResponses.setId(holiday.getId());
        holidayResponses.setName(holiday.getName());
        holidayResponses.setDateOfHoliday(holiday.getDateOfHoliday());
        holidayResponses.setImage(holiday.getImage());
        holidayResponses.setUser(user.getId());
        holidayResponses.setIsBlock(holiday.getIsBlock());
        return holidayResponses;
    }

    @Transactional
    public SimpleResponse blockHoliday(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() -> {
            log.error("Holiday with id:{} not found", id);
            throw new NotFoundException("Праздник с таким id: %s не найден");
        });
        holiday.setIsBlock(true);
        log.info("Holiday with id{} is block", id);
        return new SimpleResponse("Заблокирован", "Праздник заблокирован");
    }

    @Transactional
    public SimpleResponse unblockHoliday(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(() -> {
            log.error("Holiday with id:{} not found", id);
            throw new NotFoundException("Праздник с таким id: %s не найден");
        });
        holiday.setIsBlock(false);
        log.info("Holiday with id:{} is unblocked", id);
        return new SimpleResponse("Разблокирован", "Праздник разблокирован");
    }
}