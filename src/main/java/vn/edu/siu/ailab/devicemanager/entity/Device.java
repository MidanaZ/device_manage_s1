package vn.edu.siu.ailab.devicemanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
@Data
@Entity
@ToString
@Table(name="device")
public class Device {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "date_purchase")
    private Date datePurchase;
    @Column(name = "date_expiry")
    private Date dateExpiry;
    @Column(name = "image")
    private String image;
    @Column(name = "image_bill")
    private String imageBill;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "is_delete")
    private Boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "group_id")
    public DeviceGroup deviceGroup;
    @ManyToOne
    @JoinColumn(name = "location_id")
    public Location location;
    @ManyToOne
    @JoinColumn(name = "device_condition_id")
    public DeviceCondition deviceCondition;


    public Device() {
    }

    public Device(int id, String deviceId, String deviceName, Date datePurchase, Date dateExpiry, String image,int quantity, Boolean isDelete, DeviceGroup deviceGroup) {
        this.id = id;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.datePurchase = datePurchase;
        this.dateExpiry = dateExpiry;
        this.image = image;
        this.quantity = quantity;
        this.isDelete = isDelete;
        this.deviceGroup = deviceGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Date getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(Date dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImageBill(){
        return imageBill;
    }
    public void setImageBill(String imageBill){
        this.imageBill = imageBill;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    public DeviceCondition getDeviceCondition() {
        return deviceCondition;
    }

    public void setDeviceCondition(DeviceCondition deviceCondition) {
        this.deviceCondition = deviceCondition;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
