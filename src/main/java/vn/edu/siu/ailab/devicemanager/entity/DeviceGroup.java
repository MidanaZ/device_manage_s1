package vn.edu.siu.ailab.devicemanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@ToString
@Table(name="device_group")
public class DeviceGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="group_name")
    private String groupName;
    @OneToMany(mappedBy = "deviceGroup", fetch = FetchType.EAGER)
    @ToString.Exclude
    public List<Device> devices = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(name = "location_id")
    public Location location;


    public DeviceGroup(){}
    public DeviceGroup(int id, String groupName){
        this.id=id;
        this.groupName = groupName;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getGroupName(){
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
//    public List<Device> getDevices(){
//        return devices;
//    }
//    public void setDevices(List<Device> devices){
//        this.devices= devices;
//    }

}
