package org.quote.LoadData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.SiteParsedFromJson;

public class LoadJsonData {
	private static HashMap<String, JsonSiteStructure> jsonSitesList;
	private static JsonSiteStructure jsonSiteObject;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		jsonSitesList = new HashMap<String, JsonSiteStructure>();

		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(
				"C:\\Tayu\\Code\\Java_Workspace\\MdaChecker\\src\\main\\resources\\RegressionSitesList.json")) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray sitesList = (JSONArray) obj;
			System.out.println(sitesList);
			System.out.println(sitesList.size());

			sitesList.forEach(site -> parseSiteObject((JSONObject) site));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, JsonSiteStructure> parseJson() {
		//jsonSitesList = new ArrayList<JsonSiteStructure>();
		jsonSitesList = new HashMap<String, JsonSiteStructure>();
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader(
				"C:\\Tayu\\Code\\Java_Workspace\\MdaChecker\\src\\main\\resources\\RegressionSitesList.json")) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray sitesList = (JSONArray) obj;
			System.out.println(sitesList);
			System.out.println(sitesList.size());

			sitesList.forEach(site -> parseSiteObject((JSONObject) site));

			System.out.println("parsing finishes");
			System.out.println("jsonSitesList========>"+jsonSitesList.size());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonSitesList;
	}

	public static void parseSiteObject(JSONObject site) {
		jsonSiteObject = new JsonSiteStructure();
		String urlString;
		ArrayList<String> urls = new ArrayList<String>();

		JSONObject siteObject = (JSONObject) site.get("site");

		String mdaConfig_fName = (String) siteObject.get("MDAConfig");
		System.out.println(mdaConfig_fName);
		jsonSiteObject.setConfig(mdaConfig_fName);
	

		JSONArray urlArray = (JSONArray) siteObject.get("URLs");
		Iterator<String> iterator = urlArray.iterator();
		while (iterator.hasNext()) {
			urlString = iterator.next();
			System.out.println(urlString);
			urls.add(urlString);
		}
		jsonSiteObject.setUrlsList(urls);
		jsonSitesList.put(mdaConfig_fName, jsonSiteObject);
	}

}
