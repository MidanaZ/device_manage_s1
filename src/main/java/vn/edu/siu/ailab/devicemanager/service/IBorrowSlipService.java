package vn.edu.siu.ailab.devicemanager.service;

import vn.edu.siu.ailab.devicemanager.entity.BorrowSlip;

import java.util.List;

public interface IBorrowSlipService extends IBaseService<BorrowSlip> {
    void add(BorrowSlip slip);
    void removeDevice(int id);
    void clear();
    BorrowSlip update(int deviceId, int quantity);
    Iterable<BorrowSlip> getAllItems();
    int getCount();
    BorrowSlip updateSlip(BorrowSlip borrowSlip);
    List<BorrowSlip> search (String borrower);



}
