package com.amartenic.tradfri;

import lombok.extern.slf4j.Slf4j;
import nl.stijngroenen.tradfri.device.Device;
import nl.stijngroenen.tradfri.device.DeviceType;
import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.device.Light;
import nl.stijngroenen.tradfri.util.Credentials;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${gateway_ip}")
    private String gatewayIp;

    @Value("${security_code}")
    private String securityCode;

    public Collection<Device> getDevices() {
        return List.of(gateway.getDevices());
    }

    public Device getGolvlampa() {
        return gateway.getDevice(65539);
    }

    public boolean isGolvlampOn() {
        return getGolvlampa().toLight().getOn();
    }

    public void turnOnLamp() {
        Device device = gateway.getDevice(65539);
        Light light = device.toLight();
        light.setOn(true);
    }

    public int getGolvlampaColourHex() {
        Device device = gateway.getDevice(65539);
        Light light = device.toLight();
        log.info(light.getColourHex());
        if (light.getColourHex().equals("efd275")) {
            return 1;
        }

        else if (light.getColourHex().equals("f1e0b5")) {
            return 2;
        }

        else if (light.getColourHex().equals("f2eccf")) {
            return 3;
        }

        else if (light.getColourHex().equals("f3f3e3")) {
            return 4;
        }
        else return 5;
    }

    public void changeColourHex(int colour) {
        Device device = gateway.getDevice(65539);
        Light light = device.toLight();
        switch (colour) {
            case 1:
                light.setColourHex("efd275");
                break;
            case 2:
                light.setColourHex("f1e0b5");
                break;
            case 3:
                light.setColourHex("f2eccf");
                break;
            case 4:
                light.setColourHex("f3f3e3");
                break;
            case 5:
                light.setColourHex("f5faf6");
                break;

        }

    }

    public void turnOffLamp() {
        Device device = gateway.getDevice(65539);
        Light light = device.toLight();
        light.setOn(false);
    }

    public void setBrightness(double percentage) {
        Device device = gateway.getDevice(65539);
        Light light = device.toLight();
        int brightness = (int) (255 * (double) (percentage / 100.0));
        log.info("Light brightness: ");
        log.info(String.valueOf(brightness));
        light.setBrightness(brightness);
    }

    public void config() {
        this.gateway = new Gateway(gatewayIp);
        this.credentials = gateway.connect(securityCode);
        this.identity = credentials.getIdentity();
        this.key = credentials.getKey();
    }
}
