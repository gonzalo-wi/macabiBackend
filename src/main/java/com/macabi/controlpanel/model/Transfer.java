package com.macabi.controlpanel.model;
import java.time.LocalDateTime;
import com.macabi.controlpanel.model.enums.TypeTransfer;
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
@Table(name = "transfers")
public class Transfer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @Column(nullable = false)
    private String origin;
    
    @Column(nullable = false)
    private String destination;
    
    @Column(nullable = false)
    private LocalDateTime date;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_transfer", nullable = false)
    private TypeTransfer typeTransfer;
    
    @Column(name = "number_of_reservations", nullable = false)
    private int numberOfReservations = 0;
    
    @Column(name = "suggested_buses", nullable = false)
    private int suggestedBuses = 0;
}
