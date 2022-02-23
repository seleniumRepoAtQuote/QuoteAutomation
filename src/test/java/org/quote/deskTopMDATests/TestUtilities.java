package org.quote.deskTopMDATests;

import java.net.MalformedURLException;
import java.net.URL;

public class TestUtilities {
	public static boolean isHomePage(String urlString) throws MalformedURLException {
		URL aURL = new URL(urlString);
		if (aURL.getPath().equalsIgnoreCase("/")) {
			System.out.println("Homepage");
			return true;
		} else
			return false;
	}
}
