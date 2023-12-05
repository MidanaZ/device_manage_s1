package vn.edu.siu.ailab.devicemanager.repository;

import vn.edu.siu.ailab.devicemanager.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILocationRepo extends JpaRepository<Location,Integer> {
    List<Location> findByLocationNameContains(String locationName);
}
