package vn.edu.siu.ailab.devicemanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@Table(name="location")
public class Location {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="location_name")
    private String locationName;
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    @ToString.Exclude
    public List<Device> devices = new ArrayList<>() ;
    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    @ToString.Exclude
    public List<DeviceGroup> deviceGroup = new ArrayList<>();

//    public List<Device> getDevices() {
//        return devices;
//    }
//
//    public void setDevices(List<Device> devices) {
//        this.devices = devices;
//    }
    public String getLocationName(){
        return locationName;
    }
    public void setLocationName(String locationName){
        this.locationName = locationName;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }


}

