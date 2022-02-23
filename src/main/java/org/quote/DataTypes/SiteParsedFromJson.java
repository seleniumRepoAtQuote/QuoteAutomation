package org.quote.DataTypes;

import java.util.ArrayList;

public class SiteParsedFromJson {
	String Filename;
	ArrayList<String> URLs;

	public SiteParsedFromJson() {
		super();
	}

	public SiteParsedFromJson(String filename, ArrayList<String> uRLs) {
		super();
		Filename = filename;
		URLs = uRLs;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public ArrayList<String> getURLs() {
		return URLs;
	}

	public void setURLs(ArrayList<String> uRLs) {
		URLs = uRLs;
	}

	/*
	 * @Override public String toString() { return "SiteParsedFromJson [Filename=" +
	 * Filename + ", URLs=" + URLs + "]"; }
	 */

}
