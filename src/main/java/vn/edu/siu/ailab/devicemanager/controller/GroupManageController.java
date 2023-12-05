package vn.edu.siu.ailab.devicemanager.controller;

import vn.edu.siu.ailab.devicemanager.entity.DeviceGroup;
import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/group")
public class GroupManageController {
    @Autowired
    IGroupService service;
    @Autowired
    ILocationService locationService;

    @GetMapping( "/list")
    public String groupList (Model model){
        Iterable<DeviceGroup> deviceGroups = service.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("device_group", deviceGroups);
        return "groupList";
    }
    @GetMapping("/add")
    public String groupAdd(Model model){
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("device_group", new DeviceGroup());
        return "addGroup";
    }
    @PostMapping("add")
    public String add (DeviceGroup deviceGroup){
        service.save(deviceGroup);
        return "redirect:/group/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id")int id){
        service.delete(id);
        return "redirect:/group/list";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, Model model){
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        Optional<DeviceGroup> deviceGroup = service.findById(id);
        deviceGroup.ifPresent(group -> model.addAttribute("deviceGroup", group));
        return "updateGroup";
    }
    @PostMapping("update")
    public String updated(DeviceGroup deviceGroup){
        service.update(deviceGroup);
        return "redirect:/group/list";
    }
    @PostMapping("/search")
    public String search (@Param("groupName") String groupName, Model model){
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        List<DeviceGroup> searchResult = service.search(groupName);
        model.addAttribute("searchResult", searchResult);
        if(searchResult.isEmpty()){
            return "groupNotFound";
        }
        return "searchGroup";
    }
}
