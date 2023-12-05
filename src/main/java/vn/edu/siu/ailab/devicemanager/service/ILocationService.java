package vn.edu.siu.ailab.devicemanager.service;

import vn.edu.siu.ailab.devicemanager.entity.Location;

import java.util.List;

public interface ILocationService extends IBaseService<Location>{
    public Location update (Location location);
    public List<Location> search(String locationName);
}
