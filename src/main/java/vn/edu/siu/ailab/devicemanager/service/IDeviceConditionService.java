package vn.edu.siu.ailab.devicemanager.service;

import vn.edu.siu.ailab.devicemanager.entity.DeviceCondition;

import java.util.List;

public interface IDeviceConditionService extends IBaseService<DeviceCondition>{
    DeviceCondition update(DeviceCondition deviceCondition);
    List<DeviceCondition> search (String status);
}
