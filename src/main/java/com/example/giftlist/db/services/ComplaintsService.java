package com.example.giftlist.db.services;

import com.example.giftlist.db.models.Complaint;
import com.example.giftlist.db.models.Notification;
import com.example.giftlist.db.models.User;
import com.example.giftlist.db.models.Wish;
import com.example.giftlist.db.repositories.ComplaintRepository;
import com.example.giftlist.db.repositories.NotificationRepository;
import com.example.giftlist.db.repositories.UserRepository;
import com.example.giftlist.db.repositories.WishRepository;
import com.example.giftlist.dto.request.ComplaintRequest;
import com.example.giftlist.dto.response.ComplaintResponseForAdmin;
import com.example.giftlist.dto.response.ReservoirResponse;
import com.example.giftlist.dto.response.SimpleResponse;
import com.example.giftlist.enums.NotificationType;
import com.example.giftlist.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ComplaintsService {

    private final ComplaintRepository complaintRepository;
    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public User getPrinciple() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException(String.format("Жалоба с таким id  = %s не найдено", email))
        );
    }

    public SimpleResponse creatComplain(ComplaintRequest request) {
        Complaint complaint = convertToEntity(request);
        complaintRepository.save(complaint);
        log.info("Complaint send successfully ");
        return new SimpleResponse("Жалоба успешно отправлен", "ok");
    }

    public Complaint convertToEntity(ComplaintRequest request) {
        Wish wish = wishRepository.findById(request.getWishId()).orElseThrow(
                () -> new NotFoundException(String.format("Жалоба с таким id  = %s не найдено", request.getWishId()))
        );
        User user = getPrinciple();
        Complaint complaint = new Complaint();
        complaint.setReasonText(request.getComplaintText());
        complaint.setCreatedAt(LocalDate.now());
        complaint.setComplainer(user);
        complaint.setIsSeen(false);
        complaint.setWish(wish);
        Notification notification = new Notification();
        notification.setComplaints(List.of(complaint));
        notification.setCreatedDate(LocalDate.now());
        notification.setNotificationType(NotificationType.CREATE_COMPLAINTS);
        notification.setIsSeen(false);
        notification.setFromUser(user);
        notification.setUser(wish.getUser());
        notification.setWish(wish);
        notificationRepository.save(notification);
        return complaint;
    }

    public List<ComplaintResponseForAdmin> getAllComplaints() {
        log.info("Admin seen complaints");
        return complaintRepository.getAllComplaints();
    }

    public ComplaintResponseForAdmin getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Жалоба с таким id  = %s не найдено", id))
        );
        return convertToResponse(complaint);
    }

    public SimpleResponse blockWishByIdFromComplaint(Long id) {
        Wish wish = wishRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Желания с таким id = %s не найдено", id))
        );
        wish.setIsBlock(true);
        log.info("Wish with id: {} successfully blocked ", id);
        return new SimpleResponse("Заблокирован", "ok");
    }

    public SimpleResponse unBlockWishByIdFromComplaint(Long id) {
        Wish wish = wishRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Желания с таким id = %s не найдено", id))
        );
        wish.setIsBlock(false);
        log.info("Wish with id: {} successfully unblocked ", id);
        return new SimpleResponse("Разблокирован", "ok");
    }

    public ComplaintResponseForAdmin convertToResponse(Complaint complaint) {
        ComplaintResponseForAdmin complaintResponseForAdmin = new ComplaintResponseForAdmin();
        complaintResponseForAdmin.setId(complaint.getId());
        complaintResponseForAdmin.setUserId(complaint.getWish().getUser().getId());
        complaintResponseForAdmin.setUserPhoto(complaint.getWish().getUser().getImage());
        complaintResponseForAdmin.setUserPhoneNumber(complaint.getWish().getUser().getUserInfo().getPhoneNumber());
        complaintResponseForAdmin.setFirstName(complaint.getWish().getUser().getFirstName());
        complaintResponseForAdmin.setLastName(complaint.getWish().getUser().getLastName());
        complaintResponseForAdmin.setHolidayName(complaint.getWish().getHoliday().getName());
        complaintResponseForAdmin.setWishName(complaint.getWish().getWishName());
        complaintResponseForAdmin.setWishId(complaint.getWish().getId());
        complaintResponseForAdmin.setWishPhoto(complaint.getWish().getImage());
        complaintResponseForAdmin.setCreatedAt(complaint.getCreatedAt());
        complaintResponseForAdmin.setComplainerId(complaint.getComplainer().getId());
        complaintResponseForAdmin.setComplainerPhoto(complaint.getComplainer().getImage());
        complaintResponseForAdmin.setReason(complaint.getReasonText());
        complaintResponseForAdmin.setIsBLock(complaint.getWish().getIsBlock());
        complaintResponseForAdmin.setStatus(complaint.getWish().getWishStatus());
        complaintResponseForAdmin.setComplainerFirstName(complaint.getComplainer().getFirstName());
        complaintResponseForAdmin.setComplainerLastName(complaint.getComplainer().getLastName());
       if (complaint.getWish().getReservoir()==null){
           complaintResponseForAdmin.setReservedUserResponse(new ReservoirResponse());
       }else {
        ReservoirResponse reservoirResponse = new ReservoirResponse(complaint.getWish().getReservoir().getId(),complaint.getWish().getReservoir().getImage());
        complaintResponseForAdmin.setReservedUserResponse(reservoirResponse);
       }
        return complaintResponseForAdmin;
    }
}