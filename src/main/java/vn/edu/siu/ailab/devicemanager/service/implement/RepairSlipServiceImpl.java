package vn.edu.siu.ailab.devicemanager.service.implement;

import vn.edu.siu.ailab.devicemanager.entity.RepairSlip;
import vn.edu.siu.ailab.devicemanager.repository.IDeviceRepo;
import vn.edu.siu.ailab.devicemanager.repository.IRepairSlipRepo;
import vn.edu.siu.ailab.devicemanager.service.IRepairSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class RepairSlipServiceImpl implements IRepairSlipService {
    Map<Integer, RepairSlip> maps = new HashMap<>();
    @Autowired
    IRepairSlipRepo repository;
    @Autowired
    IDeviceRepo deviceRepo;
    @Override
    public Iterable<RepairSlip> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RepairSlip> findById(int id) {
        return repository.findById(id);
    }
    @Override
    public RepairSlip save(RepairSlip repairSlip) {
        return repository.save(repairSlip);
    }
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public RepairSlip update(RepairSlip repairSlip) {
        RepairSlip update = repository.findById(repairSlip.getId()).orElse(null);
        //update.setDeviceId(repairSlip.getDeviceId());
        update.setDeviceName(repairSlip.getDeviceName());
        update.setDateRepair(repairSlip.getDateRepair());
        update.setDateReturn(repairSlip.getDateReturn());
        update.setDescription(repairSlip.getDescription());
        update.setSlipCondition(repairSlip.getSlipCondition());
        return repository.save(update);
    }

    @Override
    public void add(RepairSlip slip) {
        RepairSlip repairSlip = maps.get(slip.getId());
        if(repairSlip == null){
            maps.put(slip.getId(),slip);
        }
    }

    @Override
    public void removeDevice(int id) {
        maps.remove(id);
    }

    @Override
    public Iterable<RepairSlip> getAllItems() {
        return maps.values();
    }
}
