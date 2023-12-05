package vn.edu.siu.ailab.devicemanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name="borrow_slip")
public class BorrowSlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "borrower")
    private String borrower;
    //@Column(name = "name")
    private String name;
    @Column(name = "date_borrow")
    private Date dateBorrow;
    @Column(name = "date_return")
    private Date dateReturn;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private int quantity = 1 ;
    @ManyToMany
    @JoinTable(name = "device_has_borrow_slip",
            joinColumns = @JoinColumn(name = "borrow_slip_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private List<Device> device;
    @ManyToOne
    @JoinColumn(name = "slip_condition_id")
    public SlipCondition slipCondition;

    public BorrowSlip(Integer id, String borrower,String name, Date dateBorrow, Date dateReturn, String description, List<Device> device, int quantity) {
        this.id = id;
        this.borrower = borrower;
        this.name = name;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.description = description;
        this.device = device;
        this.quantity = quantity;
    }

    public BorrowSlip() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Device> getDevice() {
        return device;
    }

    public void setDevice(List<Device> device) {
        this.device = device;
    }

    public SlipCondition getSlipCondition() {
        return slipCondition;
    }

    public void setSlipCondition(SlipCondition slipCondition) {
        this.slipCondition = slipCondition;
    }
}
