package com.example.giftlist.db.models;

import com.example.giftlist.dto.request.CharityRequest;
import com.example.giftlist.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "charity")
@Getter
@Setter
@NoArgsConstructor
public class Charity {

    @Id
    @SequenceGenerator(name = "charity_gen", sequenceName = "charity_seq", allocationSize = 1, initialValue = 14)
    @GeneratedValue(generator = "charity_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private SubCategory subCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    private User reservoir;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "charity_status")
    private Status charityStatus;

    @Column(length = 10000)
    private String description;

    private String condition;

    @Column(length = 10000)
    private String image;

    @Column(name = "created_date")
    private LocalDate createdAt;

    @Column(name = "is_block")
    private Boolean isBlock;

    public Charity(CharityRequest charityRequest) {
        this.name = charityRequest.getName();
        this.charityStatus = Status.WAIT;
        this.description = charityRequest.getDescription();
        this.condition = charityRequest.getCondition();
        this.image = charityRequest.getImage();
        this.createdAt = LocalDate.now();
    }
}