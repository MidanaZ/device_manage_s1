package vn.edu.siu.ailab.devicemanager.controller;

import vn.edu.siu.ailab.devicemanager.entity.DeviceCondition;
import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.service.IDeviceConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/condition")
public class DeviceConditionManageController {
    @Autowired
    IDeviceConditionService service;
    @Autowired
    ILocationService locationService;
    @GetMapping("/list")
    public String detail(Model model){
        Iterable<Location> locations = locationService.findAll();
        Iterable<DeviceCondition> deviceCondition = service.findAll();
        model.addAttribute("condition",deviceCondition);
        model.addAttribute("location", locations);
        return "deviceConditionList";
    }
    @GetMapping("/add")
    public String conditionAdd(Model model){
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location", locations);
        model.addAttribute("condition", new DeviceCondition());
        return "addCondition";
    }
    @PostMapping("add")
    public String save(DeviceCondition deviceCondition){
        service.save(deviceCondition);
        return "redirect:/condition/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id")int id){
        service.delete(id);
        return "redirect:/condition/list";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, Model model){
        Optional<DeviceCondition> deviceCondition = service.findById(id);
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("deviceCondition", deviceCondition.get());
        return "updateDeviceCondition";
    }
    @PostMapping("update")
    public String updated (DeviceCondition deviceCondition){
        service.update(deviceCondition);
        return "redirect:/condition/list";
    }
    @PostMapping("/search")
    public String search(@Param("status")String status, Model model){
        List<DeviceCondition> searchResult = service.search(status);
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("searchResult",searchResult);
        if(searchResult.isEmpty()){
            return "deviceConditionNotFound";
        }
        return "searchDeviceCondition";
    }

}
