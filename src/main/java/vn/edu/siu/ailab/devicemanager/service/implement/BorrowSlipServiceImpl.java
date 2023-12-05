package vn.edu.siu.ailab.devicemanager.service.implement;

import vn.edu.siu.ailab.devicemanager.entity.BorrowSlip;
import vn.edu.siu.ailab.devicemanager.repository.IBorrowSlipRepo;
import vn.edu.siu.ailab.devicemanager.repository.IDeviceRepo;
import vn.edu.siu.ailab.devicemanager.service.IBorrowSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BorrowSlipServiceImpl implements IBorrowSlipService {
    Map<Integer, BorrowSlip> maps = new HashMap<>();
    @Autowired
    IBorrowSlipRepo repository;
    @Autowired
    IDeviceRepo deviceRepo;


    @Override
    public void add(BorrowSlip slip) {
        BorrowSlip borrowSlip = maps.get(slip.getId());
        if(borrowSlip == null){
            maps.put(slip.getId(),slip);
        }
//        else {
//            borrowSlip.setQuantity(borrowSlip.getQuantity()+1);
//        }
    }

    @Override
    public Iterable<BorrowSlip> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<BorrowSlip> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public BorrowSlip save(BorrowSlip slip) {

        return repository.save(slip);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void removeDevice(int id) {
        maps.remove(id);
    }

    @Override
    public void clear() {
        maps.clear();
    }

    @Override
    public BorrowSlip update(int deviceId, int quantity) {
        BorrowSlip borrowSlip = maps.get(deviceId);
        borrowSlip.setQuantity(quantity);
        return borrowSlip;
    }

    @Override
    public Iterable<BorrowSlip> getAllItems() {
        return maps.values();
    }

    @Override
    public int getCount() {
        return maps.values().size();
    }

    @Override
    public BorrowSlip updateSlip(BorrowSlip borrowSlip) {
        BorrowSlip update = repository.findById(borrowSlip.getId()).orElse(null);
        update.setBorrower(borrowSlip.getBorrower());
        update.setDateBorrow(borrowSlip.getDateBorrow());
        update.setDateReturn(borrowSlip.getDateReturn());
        update.setName(borrowSlip.getName());
        update.setDescription(borrowSlip.getDescription());
        update.setDevice(borrowSlip.getDevice());
        update.setSlipCondition(borrowSlip.getSlipCondition());
        return repository.save(update);
    }

    @Override
    public List<BorrowSlip> search(String borrower) {
        return repository.findByBorrowerContains(borrower);
    }
}
