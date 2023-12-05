package vn.edu.siu.ailab.devicemanager.service.implement;

import vn.edu.siu.ailab.devicemanager.entity.DeviceCondition;
import vn.edu.siu.ailab.devicemanager.repository.IDeviceConditionRepo;
import vn.edu.siu.ailab.devicemanager.service.IDeviceConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DeviceConditionSerImpl implements IDeviceConditionService {
    @Autowired
    IDeviceConditionRepo repository;
    @Override
    public Iterable<DeviceCondition> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DeviceCondition> findById(int id) {
        return repository.findById(id);
    }


    @Override
    public DeviceCondition save(DeviceCondition deviceCondition) {
        return repository.save(deviceCondition);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public DeviceCondition update(DeviceCondition deviceCondition) {
        DeviceCondition update = repository.findById(deviceCondition.getId()).orElse(null);
        update.setStatus(deviceCondition.getStatus());
        return repository.save(deviceCondition);
    }

    @Override
    public List<DeviceCondition> search(String status) {
        return repository.findByStatusContains(status);
    }
}
