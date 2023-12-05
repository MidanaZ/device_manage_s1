package vn.edu.siu.ailab.devicemanager.controller;

import vn.edu.siu.ailab.devicemanager.entity.BorrowSlip;
import vn.edu.siu.ailab.devicemanager.entity.Device;
import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.entity.SlipCondition;
import vn.edu.siu.ailab.devicemanager.service.IBorrowSlipService;
import vn.edu.siu.ailab.devicemanager.service.IDeviceService;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;
import vn.edu.siu.ailab.devicemanager.service.ISlipConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/borrowSlip")
public class BorrowSlipManageController {
    @Autowired
    IBorrowSlipService slipService;
    @Autowired
    IDeviceService deviceService;
    @Autowired
    ISlipConditionService slipConditionService;
    @Autowired
    ILocationService locationService;

    @GetMapping("/add/{id}")
    public String addToSlip(@PathVariable(value = "id") Integer id,Model model) {
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        Optional<Device> device = deviceService.findById(id);
        if(device.isPresent()){
            if (device != null) {
                BorrowSlip slip = new BorrowSlip();
                slip.setId(device.get().getId());
                slip.setName(device.get().getDeviceName());
                slip.setQuantity(1);
                slipService.add(slip);
            }
        }

        return "redirect:/borrowSlip/slip";
    }

    @GetMapping("slip")
    public String slip(Model model) {
        Iterable<SlipCondition> slipConditions = slipConditionService.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        //Iterable<BorrowSlip> slips = slipService.getAllItems();
//        Iterable<Device> devices = deviceService.findAll();
//        model.addAttribute("devices", devices);
        model.addAttribute("SlipInfo", slipService.getAllItems());
        model.addAttribute("slipConditions", slipConditions);
        model.addAttribute("borrowSlip", new BorrowSlip());
        return "borrowSlip";
    }

    @GetMapping("remove/{id}")
    public String removeDevice(@PathVariable("id") Integer id) {
        slipService.removeDevice(id);
        return "redirect:/borrowSlip/slip";
    }
//    @GetMapping("removeEdit/{id}")
//    public String removeDeviceEdit(@PathVariable("id") Integer id) {
//        slipService.removeDevice(id);
//        return "redirect:/borrowSlip/update";
//    }

    @PostMapping("add")
    public String save(BorrowSlip slip) {

        slipService.save(slip);
        return "redirect:/borrowSlip/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        Iterable<BorrowSlip> borrowSlips = slipService.findAll();
        Iterable<Device> devices = deviceService.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("devices", devices);
        model.addAttribute("BorrowSlip", borrowSlips);
        return "borrowSlipList";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        slipService.delete(id);
        return "redirect:/borrowSlip/list";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, Model model) {
        Optional<BorrowSlip> borrowSlip = slipService.findById(id);
        Iterable<SlipCondition> slipConditions = slipConditionService.findAll();
        model.addAttribute("slipConditions", slipConditions);
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        if (borrowSlip.isPresent()){
            model.addAttribute("borrowSlip",borrowSlip.get());
        }
        model.addAttribute("SlipInfo", slipService.getAllItems());
        return "updateBorrowSlip";
    }
    @PostMapping("update")
    public String updated(BorrowSlip borrowSlip){
        slipService.updateSlip(borrowSlip);
        return "redirect:/borrowSlip/list";
    }
    @PostMapping("/search")
    public String search(@Param("borrower")String borrower, Model model){
        List<BorrowSlip> searchResult = slipService.search(borrower);
        Iterable<BorrowSlip> borrowSlips = slipService.findAll();
        Iterable<Device> devices = deviceService.findAll();
        Iterable<Location> locations = locationService.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("devices", devices);
        model.addAttribute("BorrowSlip", borrowSlips);
        model.addAttribute("searchResult",searchResult);
        if (searchResult.isEmpty()){
            return "borrowSlipNotFound";
        }
        return "searchBorrowList";
    }
}
