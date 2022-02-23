package org.quote.DataTypes;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;

public class MDAConfig {
	// private MdaType mdaType; // 1.HomePageMda 2.MdaTop 3.MdaBottom 4.MdaBlue
	// 5.MdaGray
	// private boolean isHomepage;
	private String hasDropDown;
	private ArrayList<String> dropDownList;

	private String mdaTitleHomePage;
	private String mdaTextHomepage;

	private String mdaTextHeader;

	private String MdaTitle;
	private String mdaText;

	private ArrayList<String> zipCode;
	private String getQuoteButtonText;

	private String pageTitleCurrentTab;
	private String pageTitlenewTab;

	public String getHasDropDown() {
		return hasDropDown;
	}

	public void setHasDropDown(String hasDropDown) {
		this.hasDropDown = hasDropDown;
	}

	public ArrayList<String> getDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(ArrayList<String> dropDownList) {
		this.dropDownList = dropDownList;
	}

	public String getMdaTitleHomePage() {
		return mdaTitleHomePage;
	}

	public void setMdaTitleHomePage(String mdaTitleHomePage) {
		this.mdaTitleHomePage = mdaTitleHomePage;
	}

	public String getMdaTextHomepage() {
		return mdaTextHomepage;
	}

	public void setMdaTextHomepage(String mdaTextHomepage) {
		this.mdaTextHomepage = mdaTextHomepage;
	}

	public String getMdaTextHeader() {
		return mdaTextHeader;
	}

	public void setMdaTextHeader(String mdaTextHeader) {
		this.mdaTextHeader = mdaTextHeader;
	}

	public String getMdaTitle() {
		return MdaTitle;
	}

	public void setMdaTitle(String mdaTitle) {
		MdaTitle = mdaTitle;
	}

	public String getMdaText() {
		return mdaText;
	}

	public void setMdaText(String mdaText) {
		this.mdaText = mdaText;
	}

	public ArrayList<String> getZipCode() {
		return zipCode;
	}

	public void setZipCode(ArrayList<String> zipCode) {
		this.zipCode = zipCode;
	}

	public String getGetQuoteButtonText() {
		return getQuoteButtonText;
	}

	public void setGetQuoteButtonText(String getQuoteButtonText) {
		this.getQuoteButtonText = getQuoteButtonText;
	}

	public String getPageTitleCurrentTab() {
		return pageTitleCurrentTab;
	}

	public void setPageTitleCurrentTab(String pageTitleCurrentTab) {
		this.pageTitleCurrentTab = pageTitleCurrentTab;
	}

	public String getPageTitlenewTab() {
		return pageTitlenewTab;
	}

	public void setPageTitlenewTab(String pageTitlenewTab) {
		this.pageTitlenewTab = pageTitlenewTab;
	}

}
/*
 * enum MdaType { HomePageMda, MdaTop, MdaBottom, MdaBlue, MdaGray }
 */