package vn.edu.siu.ailab.devicemanager.service;

import vn.edu.siu.ailab.devicemanager.entity.DeviceGroup;

import java.util.List;

public interface IGroupService extends IBaseService<DeviceGroup>{
    public DeviceGroup update(DeviceGroup deviceGroup);
    public List<DeviceGroup> search (String groupName);
}
