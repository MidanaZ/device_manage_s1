package vn.edu.siu.ailab.devicemanager.controller;

import vn.edu.siu.ailab.devicemanager.entity.Device;
import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.entity.RepairSlip;
import vn.edu.siu.ailab.devicemanager.entity.SlipCondition;
import vn.edu.siu.ailab.devicemanager.service.IDeviceService;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;
import vn.edu.siu.ailab.devicemanager.service.IRepairSlipService;
import vn.edu.siu.ailab.devicemanager.service.ISlipConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/repairSlip")
public class RepairSlipManageController {
    @Autowired
    IRepairSlipService service;
    @Autowired
    IDeviceService deviceService;
    @Autowired
    ISlipConditionService slipConditionService;
    @Autowired
    ILocationService locationService;
    @GetMapping("/list")
    public String repairSlipList(Model model){
        Iterable<RepairSlip> repairSlips = service.findAll();
        Iterable<Device> devices = deviceService.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("devices",devices);
        model.addAttribute("repair_slip",repairSlips);
        return "repairSlipList";
    }
    @GetMapping("/add/{id}")
    public String addToSlip(@PathVariable(value = "id") Integer id, Model model){
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        Optional<Device> device = deviceService.findById(id);
        if (device != null) {
            RepairSlip slip = new RepairSlip();
            slip.setId(device.get().getId());
            slip.setDeviceName(device.get().getDeviceName());
            slip.setQuantity(1);
            service.add(slip);
        }
        return "redirect:/repairSlip/slip";
    }
    @PostMapping("add")
    public String save (RepairSlip repairSlip){
        service.save(repairSlip);
        return "redirect:/repairSlip/list";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable(value = "id")int id){
        service.delete(id);
        return "redirect:/repairSlip/list";
    }
    @GetMapping("/update/{id}")
    public String update (@PathVariable(value = "id")int id, Model model){
        Optional<RepairSlip> repairSlip = service.findById(id);
        Iterable<SlipCondition> slipConditions =slipConditionService.findAll();
        if(repairSlip.isPresent()){
            model.addAttribute("repair_slip" ,repairSlip.get());
        }
        model.addAttribute("slipConditions",slipConditions);
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);

        return "updateRepairSlip";
    }
    @PostMapping("update")
    public String updated (RepairSlip repairSlip){
        service.update(repairSlip);
        return "redirect:/repairSlip/list";
    }
    @GetMapping("slip")
    public String slip (Model model){
        Iterable<SlipCondition> slipConditions = slipConditionService.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("SlipInfo", service.getAllItems());
        model.addAttribute("slipConditions",slipConditions);
        model.addAttribute("repairSlip", new RepairSlip());
        return "repairSlip";
    }

}
