package vn.edu.siu.ailab.devicemanager.service.implement;

import vn.edu.siu.ailab.devicemanager.entity.DeviceGroup;
import vn.edu.siu.ailab.devicemanager.repository.IDeviceGroupRepo;
import vn.edu.siu.ailab.devicemanager.repository.IDeviceRepo;
import vn.edu.siu.ailab.devicemanager.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceGroupServiceImpl implements IGroupService {
    @Autowired
    IDeviceGroupRepo repository;

    @Autowired
    IDeviceRepo deviceRepo;
    @Override
    public Iterable<DeviceGroup> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DeviceGroup> findById(int id) {
        return repository.findById(id);
    }
    @Override
    public DeviceGroup save(DeviceGroup deviceGroup) {
        return repository.save(deviceGroup);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public DeviceGroup update(DeviceGroup deviceGroup) {
        DeviceGroup update = repository.findById(deviceGroup.getId()).orElse(null);
        update.setGroupName(deviceGroup.getGroupName());
        return repository.save(update);
    }

    @Override
    public List<DeviceGroup> search(String groupName) {
        return repository.findByGroupNameContains(groupName);
    }
}
