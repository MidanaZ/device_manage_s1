package vn.edu.siu.ailab.devicemanager.service.implement;

import vn.edu.siu.ailab.devicemanager.entity.Device;
import vn.edu.siu.ailab.devicemanager.repository.IDeviceRepo;
import vn.edu.siu.ailab.devicemanager.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    IDeviceRepo repository;
    @Value("${upload.imageDevice}")
    private String imageDevice;
    @Value("${upload.imageBill}")
    private String imageBill;


    /*public Device getDeviceById(int id){
        return repository.findById(id).orElse(null);
    }*/
//    public Device findDeviceByName(String deviceName){
//        return repository.findByDeviceName(deviceName);
//    }
//    public String deleteDevice(int id){
//        repository.deleteById(id);
//        return "Device deleted id: " +id;
//    }
    public Device updateDevice(Device device) {
        Device update = repository.findById(device.getId()).orElse(null);
        update.setDeviceName(device.getDeviceName());
        update.setDatePurchase(device.getDatePurchase());
        update.setDateExpiry(device.getDateExpiry());
        update.setImage(device.getImage());
        update.setImageBill(device.getImageBill());
        update.setDeviceGroup(device.getDeviceGroup());
        update.setDeviceCondition(device.getDeviceCondition());
        update.setLocation(device.getLocation());
        update.setQuantity(device.getQuantity());
        return repository.save(update);
    }

    @Override
    public List<Device> searchDevice(String deviceName) {
        return repository.findByDeviceNameContains(deviceName);
    }

    @Override

    public String saveImage(MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();
        String random = UUID.randomUUID().toString() + ".png";
        Path path = Paths.get(imageDevice + random);
        Files.write(path, bytes);
        return random;
    }

    @Override
    public String saveImageBill(MultipartFile imageBillFile) throws IOException {
//        String folder = "E:\\Trung\\device_manage\\src\\main\\resources\\static\\images\\imageBill\\";
        byte[] bytes = imageBillFile.getBytes();
        String random = UUID.randomUUID().toString() + ".png";
        Path path = Paths.get(imageBill + random);
        Files.write(path, bytes);
        return random;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    @Override
    public Iterable<Device> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Device> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Device save(Device device) {
        return repository.save(device);
    }

    @Override
    public void delete(int id) {
        Optional<Device> device = repository.findById(id);
        if (device != null) {
//            String filePath = "E:\\Trung\\device_manage\\src\\main\\resources\\static\\images\\imageDevice\\";
            File file = new File(imageDevice+device.get().getImage());
            if (file.exists()) {
                file.delete();
            }
            repository.deleteById(id);
        }
    }
}
