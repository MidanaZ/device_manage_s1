package vn.edu.siu.ailab.devicemanager.service;

import vn.edu.siu.ailab.devicemanager.entity.RepairSlip;

public interface IRepairSlipService extends IBaseService<RepairSlip>{
    public RepairSlip update (RepairSlip repairSlip);
    void add(RepairSlip slip);
    void removeDevice(int id);
    Iterable<RepairSlip> getAllItems();
}
