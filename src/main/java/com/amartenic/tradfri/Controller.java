package com.amartenic.tradfri;

import nl.stijngroenen.tradfri.device.Device;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class Controller {
    final DeviceService service;

    public Controller(DeviceService service) {
        this.service = service;
    }

    @GetMapping("/devices")
    public ResponseEntity<Collection<Device>> getAll() {
        Collection <Device> devices = service.getDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/device/golvlampa")
    public ResponseEntity<Device> getGolvlampa() {
        Device device = service.getGolvlampa();
        return new ResponseEntity<>(device, HttpStatus.OK);
    }


    @PostMapping("/device/golvlampa/on")
    public ResponseEntity turnOn() {
        service.turnOnLamp();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/device/golvlampa/off")
    public ResponseEntity turnOff() {
      service.turnOffLamp();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/device/golvlampa/brightness/{procent}")
    public ResponseEntity increase(@PathVariable double procent) {
        service.setBrightness(procent);
        return new ResponseEntity(HttpStatus.OK);
    }
}
