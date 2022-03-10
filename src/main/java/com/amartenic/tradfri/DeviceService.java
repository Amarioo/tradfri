package com.amartenic.tradfri;

import lombok.extern.slf4j.Slf4j;
import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.device.Light;
import nl.stijngroenen.tradfri.util.Credentials;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j

@Service
public class DeviceService {
    private Gateway gateway;
    private Credentials credentials;
    private String identity;
    private String key;

    public Collection<Device> getDevices() {
        //config();
        return List.of(gateway.getDevices());
    }

    public Device getGolvlampa() {
        config();
        return gateway.getDevice(65539);

    }

    public void turnOnLamp() {
        config();
        Device device = getGolvlampa();
        Light light = device.toLight();
        log.info("Light brightness: ");
        log.info( light.getBrightness().toString());
        light.setOn(true);

    }

    public void turnOffLamp() {
        config();
        Device device = getGolvlampa();
        Light light = device.toLight();
        light.setOn(false);
    }

    public void setBrightness(double procentage) {
        config();
        Device device = getGolvlampa();
        Light light = device.toLight();
        int brightness = (int) (255 * (double) (procentage/100.0));
        log.info("Light brightness: ");
        log.info(String.valueOf(brightness));
        light.setBrightness(brightness);
    }

    public void config() {
        this.gateway = new Gateway("192.168.1.179");
        this.credentials = gateway.connect("9qgszNBivGBJFH1f");
        this.identity = credentials.getIdentity();
        this.key = credentials.getKey();
    }
}
