package com.macabi.controlpanel.model;

import com.macabi.controlpanel.model.enums.TypeBooking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_booking", nullable = false)
    private TypeBooking typeBooking;
    
    @Column(nullable = false)
    private boolean active = true;
}
