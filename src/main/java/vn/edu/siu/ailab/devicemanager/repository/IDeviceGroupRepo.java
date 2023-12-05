package vn.edu.siu.ailab.devicemanager.repository;

import vn.edu.siu.ailab.devicemanager.entity.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeviceGroupRepo extends JpaRepository<DeviceGroup, Integer> {
    List<DeviceGroup> findByGroupNameContains(String groupName);
}
