package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

	@Test
	public void testMyIp() {
		String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.71.180.212");
		String geoIPCountry = null;
		if (ipLocation.contains("RU")) {
			geoIPCountry = "RU";
		}
		Assert.assertEquals(geoIPCountry, "RU");
	}
}
