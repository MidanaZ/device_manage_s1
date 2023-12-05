package vn.edu.siu.ailab.devicemanager.controller;

import vn.edu.siu.ailab.devicemanager.entity.Device;
import vn.edu.siu.ailab.devicemanager.entity.DeviceCondition;
import vn.edu.siu.ailab.devicemanager.entity.DeviceGroup;
import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.service.IDeviceConditionService;
import vn.edu.siu.ailab.devicemanager.service.IDeviceService;
import vn.edu.siu.ailab.devicemanager.service.IGroupService;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/device")
public class DeviceManageController {
    @Autowired
    IDeviceService service;
    @Autowired
    IGroupService groupService;
    @Autowired
    IDeviceConditionService conditionService;
    @Autowired
    ILocationService locationService;
    //private static List<Device> devices = new ArrayList<Device>();

//    @RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
//    public String detail(Model model) {
//        Iterable<Device> device = service.findAll();
//        model.addAttribute("device", device);
//        return "deviceDetail";
//    }

    @GetMapping(value = {"/list"})
    public String deviceList(Model model) {
        Iterable<Device> devices = service.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("devices", devices);
        return "deviceList";
    }

    @GetMapping(value = "/add")
    public String addDevice(Model model) {
        Iterable<DeviceGroup> groups = groupService.findAll();
        Iterable<DeviceCondition> conditions = conditionService.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("device", new Device());
        model.addAttribute("groups", groups);
        model.addAttribute("conditions", conditions);
        model.addAttribute("locations", locations);
        return "addDevice";
    }

    @PostMapping("add")
    public String save(Device device, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("imageBillFile") MultipartFile imageBillFile) {
        if (!imageFile.isEmpty()) {
            try {
                String imageFileName = service.saveImage(imageFile);
                device.setImage(imageFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!imageBillFile.isEmpty()) {
            try {
                String imageFileName = service.saveImageBill(imageBillFile);
                device.setImageBill(imageFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        service.save(device);
        return "redirect:/device/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        service.delete(id);
        return "redirect:/device/list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, Model model) {
        Optional<Device> device = service.findById(id);
        Iterable<DeviceGroup> groups = groupService.findAll();
        Iterable<DeviceCondition> conditions = conditionService.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("groups", groups);
        model.addAttribute("conditions", conditions);
        model.addAttribute("locations", locations);
        model.addAttribute("device", device.get());
        return "updateDevice";
    }

    @PostMapping(value = "update")
    public String updated(Device device, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("imageBillFile") MultipartFile imageBillFile) {
        if (!imageFile.isEmpty()) {
            try {
                String imageFileName = service.saveImage(imageFile);
                device.setImage(imageFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!imageBillFile.isEmpty()) {
            try {
                String imageFileName = service.saveImageBill(imageBillFile);
                device.setImageBill(imageFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        service.updateDevice(device);
        return "redirect:/device/list";
    }
    @PostMapping("/search")
    public String search(@Param("deviceName")String deviceName, Model model){
        List<Device> searchResult = service.searchDevice(deviceName);
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("searchResult",searchResult);
        if (searchResult.isEmpty()){
            return "deviceNotFind";
        }
        return "searchDeviceList";
    }
//    @GetMapping("/search")
//    public String searchDevice(Model model){
//        return "searchDeviceList";
//    }


}
