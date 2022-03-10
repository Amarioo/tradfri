package com.amartenic.tradfri;

import nl.stijngroenen.tradfri.device.Gateway;
import nl.stijngroenen.tradfri.util.Credentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import nl.stijngroenen.tradfri.device.Device;
@SpringBootApplication
public class TradfriApplication {

	public static void main(String[] args) {
		Gateway gateway = new Gateway("192.168.1.179");
		Credentials credentials = gateway.connect("9qgszNBivGBJFH1f");
		String identity = credentials.getIdentity();
		String key = credentials.getKey();
		SpringApplication.run(TradfriApplication.class, args);
	}

}
