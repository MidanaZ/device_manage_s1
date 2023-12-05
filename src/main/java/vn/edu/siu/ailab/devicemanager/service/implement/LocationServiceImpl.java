package vn.edu.siu.ailab.devicemanager.service.implement;

import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.repository.IDeviceRepo;
import vn.edu.siu.ailab.devicemanager.repository.ILocationRepo;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements ILocationService {
    @Autowired
    ILocationRepo repository;
    @Autowired
    IDeviceRepo deviceRepo;
    @Override
    public Iterable<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Location> findById(int id) {
        return repository.findById(id);
    }
    @Override
    public Location save(Location location) {
        return repository.save(location);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    @Override
    public Location update(Location location) {
        Location update = repository.findById(location.getId()).orElse(null);
        update.setLocationName(location.getLocationName());
        return repository.save(update);
    }

    @Override
    public List<Location> search(String locationName) {
        return repository.findByLocationNameContains(locationName);
    }
}
