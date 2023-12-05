package vn.edu.siu.ailab.devicemanager.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name="repair_slip")
public class RepairSlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "device_id")
//    private String deviceId;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "warranty_person")
    private String warrantyPerson;
    @Column(name = "date_repair")
    private Date dateRepair;
    @Column(name = "date_return")
    private Date dateReturn;
    @Column(name = "description")
    private String description;
    private int quantity =1;
    @ManyToMany
    @JoinTable(name="device_has_repair_slip",
            joinColumns = @JoinColumn(name = "repair_slip_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private Set<Device> device;
    @ManyToOne
    @JoinColumn(name ="slip_condition_id")
    private SlipCondition slipCondition;


    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//    public String getDeviceId(){
//        return deviceId;
//    }
//    public void setDeviceId(String deviceId){
//        this.deviceId = deviceId;
//    }
    public String getDeviceName(){
        return deviceName;
    }
    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }
    public Date getDateRepair(){
        return dateRepair;
    }
    public void setDateRepair(Date dateRepair){
        this.dateRepair =dateRepair;
    }
    public Date getDateReturn(){
        return dateReturn;
    }
    public void setDateReturn(Date dateReturn){
        this.dateReturn = dateReturn;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description =description;
    }
}
