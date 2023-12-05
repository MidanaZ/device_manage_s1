package vn.edu.siu.ailab.devicemanager.service;

import vn.edu.siu.ailab.devicemanager.entity.Device;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDeviceService extends IBaseService<Device> {
    public Device updateDevice(Device device);

    //    @Query(value = "SELECT * FROM device where ")
    public List<Device> searchDevice(String deviceName);

    String saveImage(MultipartFile imageFile) throws IOException;

    String saveImageBill(MultipartFile imageBillFile) throws IOException;
}
