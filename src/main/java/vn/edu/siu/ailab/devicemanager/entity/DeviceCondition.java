package vn.edu.siu.ailab.devicemanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="device_condition")
public class DeviceCondition {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    private String status;
    /*@OneToMany
    @JoinColumn(name = "device_condition_id")
    private Set<Device> device;*/

}
