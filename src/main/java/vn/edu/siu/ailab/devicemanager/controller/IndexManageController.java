package vn.edu.siu.ailab.devicemanager.controller;

import vn.edu.siu.ailab.devicemanager.entity.Device;
import vn.edu.siu.ailab.devicemanager.entity.DeviceGroup;
import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.service.IDeviceService;
import vn.edu.siu.ailab.devicemanager.service.IGroupService;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping({"/","index"})
public class IndexManageController {
    @Autowired
    IDeviceService deviceSer;
    @Autowired
    ILocationService locationSer;
    @Autowired
    IGroupService groupService;

    @RequestMapping ("")
    public String index(Model model){
        Iterable<Device> device = deviceSer.findAll();
        Iterable<Location> location = locationSer.findAll();
        Iterable<DeviceGroup> deviceGroup = groupService.findAll();
        model.addAttribute("device", device);
        model.addAttribute("location", location);
        model.addAttribute("group",deviceGroup);
        return "Index";
    }
    @GetMapping("/device/{id}")
    public String deviceDetail(@PathVariable(value = "id") int id, Model model){
        Optional<Device> device = deviceSer.findById(id);
        Iterable<Location> locations = locationSer.findAll();
        model.addAttribute("location",locations);
        device.ifPresent(value -> model.addAttribute("device", value));

        return "deviceDetail";
    }
//    @GetMapping("/location/{id}")
//    public String locationDetail(@PathVariable(value = "id")int id, Model model){
//        Optional<Location> location = locationSer.findById(id);
//        model.addAttribute("location",location.get());
//        return "LocationDetail";
//
//    }
    @PostMapping("/search")
    public String search(@Param("deviceName") String deviceName, Model model){

        List<Device> searchResult = deviceSer.searchDevice(deviceName);
        Iterable<Location> locations = locationSer.findAll();
       // model.addAttribute("deviceName", deviceName);
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("location",locations);
        if(searchResult.isEmpty()){
            return "indexNotFound";
        }
        return "searchDevice";
    }

//    @GetMapping("/search")
//    public String searchDevice(Model model){
//        Iterable<Device> device = deviceSer.findAll();
//        Iterable<Location> location = locationSer.findAll();
//        model.addAttribute("device",device);
//        model.addAttribute("location", location);
//        return "searchDevice";
//    }



}
