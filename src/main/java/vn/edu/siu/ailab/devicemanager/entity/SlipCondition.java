package vn.edu.siu.ailab.devicemanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "slip_condition")
public class SlipCondition {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "slip_status")
    public String slipStatus;
//    @OneToMany
//    @JoinColumn(name ="slip_condition_id")
//    private Set<BorrowSlip> borrowSlip;
}
