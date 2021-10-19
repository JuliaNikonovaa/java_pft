package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {


	@Test
	public void testMyIp() {
		String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.71.180.212");
		assertTrue(ipLocation.contains("RU"));
	}

	@Test
	public void testInvalidIp() {
		String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.71.180.xxx");
		assertTrue(ipLocation.contains("RU"));
	}
}
