package vn.edu.siu.ailab.devicemanager.repository;

import vn.edu.siu.ailab.devicemanager.entity.DeviceCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeviceConditionRepo extends JpaRepository<DeviceCondition,Integer> {
    List<DeviceCondition> findByStatusContains(String status);
}
