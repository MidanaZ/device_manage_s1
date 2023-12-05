package vn.edu.siu.ailab.devicemanager.controller;

import vn.edu.siu.ailab.devicemanager.entity.Location;
import vn.edu.siu.ailab.devicemanager.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/location")
public class LocationManageController {
    @Autowired
    ILocationService service;


    @GetMapping("/list")
    public String locationList (Model model){
        Iterable<Location> locations = service.findAll();
        model.addAttribute("location", locations);
        return "locationList";
    }
    @GetMapping("/add")
    public String locationAdd (Model model){
        Iterable<Location> locations =service.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("location", new Location());
        return "addLocation";
    }
    @PostMapping("add")
    public String save (Location location){
        service.save(location);
        return "redirect:/location/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id){
        service.delete(id);
        return "redirect:/location/list";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, Model model){
        Optional<Location> location = service.findById(id);
        Iterable<Location> locations = service.findAll();
        model.addAttribute("locations",locations);
        if(location.isPresent()){
            model.addAttribute("location", location.get());
        }

        return "updateLocation";
    }
    @PostMapping(value = "updated")
    public String updated (Location location) {
        service.update(location);
        return "redirect:/location/list";
    }
    @PostMapping("/search")
    public String search(@Param("locationName") String locationName, Model model){
        List<Location> searchResult = service.search(locationName);
        Iterable<Location> locations = service.findAll();
        model.addAttribute("location",locations);
        model.addAttribute("searchResult",searchResult);
        if(searchResult.isEmpty()){
            return "locationNotFound";
        }
        return "searchLocation";

    }
}
