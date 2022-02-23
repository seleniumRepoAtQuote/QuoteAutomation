package org.quote.DataTypes;

import java.util.ArrayList;

public class JsonSiteStructure {
	private String mdaConfigFileName;
	private ArrayList<String> urlsList;

	public JsonSiteStructure(String mdaConfigFileName, ArrayList<String> urlsList) {
		super();
		this.mdaConfigFileName = mdaConfigFileName;
		this.urlsList = urlsList;
	}

	public JsonSiteStructure() {
		super();
	}

	public String getConfig() {
		return mdaConfigFileName;
	}

	public void setConfig(String mdaConfigFileName) {
		this.mdaConfigFileName = mdaConfigFileName;
	}

	public ArrayList<String> getUrlsList() {
		return urlsList;
	}

	public void setUrlsList(ArrayList<String> urlsList) {
		this.urlsList = urlsList;
	}
}
