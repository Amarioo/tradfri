package com.amartenic.tradfri;

import nl.stijngroenen.tradfri.device.Device;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/device")
public class DeviceController {
    final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Device>> getAll() {
        Collection <Device> devices = service.getDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/golvlampa")
    public ResponseEntity<Device> getGolvlampa() {
        Device device = service.getGolvlampa();
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @PostMapping("/golvlampa/on")
    public ResponseEntity turnOn() {
        service.turnOnLamp();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/golvlampa/off")
    public ResponseEntity turnOff() {
      service.turnOffLamp();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/golvlampa/brightness/{percent}")
    public ResponseEntity increase(@PathVariable double percent) {
        service.setBrightness(percent);
        return new ResponseEntity(HttpStatus.OK);
    }
}
