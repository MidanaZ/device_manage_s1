package vn.edu.siu.ailab.devicemanager.repository;

import vn.edu.siu.ailab.devicemanager.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeviceRepo extends JpaRepository<Device, Integer> {

    List<Device> findByDeviceNameContains(String deviceName);
//    List<Device>findByDeviceNameIsContaining(String deviceName);

}
