package org.quote.LoadData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quote.DataTypes.CrossBrowserLoginConfig;
import org.quote.DataTypes.MDAConfig;
import org.quote.DataTypes.PlatformConfig;

public class LoadDataFromExcel {
	private ArrayList<PlatformConfig> platformsList;
	private ArrayList<URL> urlsList;

	public ArrayList<PlatformConfig> populatePlatformAndBrowsers() throws IOException {
		// File myFile = new File("C://temp/ListOfURLs.xlsx");

		String fileName = "PlatformAndBrowsers.xlsx";
		ClassLoader classLoader = getClass().getClassLoader();
		File excelFile = new File(classLoader.getResource(fileName).getFile());

		// File excelFile = new
		// File("./MdaChecker/src/main/resources/PlatformAndBrowsers.xlsx");
		platformsList = new ArrayList<PlatformConfig>();
		FileInputStream fis = new FileInputStream(excelFile);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			PlatformConfig platform = new PlatformConfig();
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell device = cellIterator.next();

				switch (device.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					String str1 = device.getStringCellValue();
					System.out.print(str1 + "\t");
					platform.setPlatform(str1);
					Cell browser = cellIterator.next();
					str1 = browser.getStringCellValue();
					System.out.print(str1 + "\t");
					platform.setBrowser(str1);
					break;

				default:

				}
			}
			System.out.println("");
			platformsList.add(platform);
		}
		myWorkBook.close();
		return platformsList;
	}

	public MDAConfig populateMdaConfigs(String mdaFileName) throws IOException {

		String fileName = mdaFileName;
		ClassLoader classLoader = getClass().getClassLoader();
		File excelFile = new File(classLoader.getResource(fileName).getFile());

		FileInputStream fis = new FileInputStream(excelFile);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();
		MDAConfig mdaConfig = new MDAConfig();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					String strType = cell.getStringCellValue();
					switch (strType) {
					case "mdaTitleHomePage":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setMdaTitleHomePage(strType);
						System.out.print(mdaConfig.getMdaTitleHomePage() + "\n");
						break;
					case "mdaTextHomepage":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setMdaTextHomepage(strType);
						System.out.print(mdaConfig.getMdaTextHomepage() + "\n");
						break;
					case "mdaTextHeader":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setMdaTextHeader(strType);
						System.out.print(mdaConfig.getMdaTextHeader() + "\n");
						break;
					case "MdaTitle":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setMdaTitle(strType);
						System.out.print(mdaConfig.getMdaTitle() + "\n");
						break;
					case "mdaText":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setMdaText(strType);
						System.out.print(mdaConfig.getMdaText() + "\n");
						break;
					case "getQuoteButtonText":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setGetQuoteButtonText(strType);
						System.out.print(mdaConfig.getGetQuoteButtonText() + "\n");
						break;
					case "pageTitleCurrentTab":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setPageTitleCurrentTab(strType);
						System.out.print(mdaConfig.getPageTitleCurrentTab() + "\n");
						break;
					case "pageTitlenewTab":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						mdaConfig.setPageTitlenewTab(strType);
						System.out.print(mdaConfig.getPageTitlenewTab() + "\n");
						break;
					case "hasDropDown":
						cell = cellIterator.next();
						strType = cell.getStringCellValue();
						if (strType.equalsIgnoreCase("yes") || strType.equalsIgnoreCase("no")) {
							mdaConfig.setHasDropDown(strType);
							System.out.print(mdaConfig.getHasDropDown() + "\n");
						} else
							System.out.println("Error : Please enter valid data for 'hasDropDown' - Yes / No");
						break;
					case "zipCodeList":
						ArrayList<String> zipCodesList = new ArrayList<String>();
						DataFormatter formatter = new DataFormatter();
						// String val = formatter.formatCellValue(cellIterator.next());
						while (cellIterator.hasNext()) {
							//cell = cellIterator.next();
							strType = formatter.formatCellValue(cellIterator.next());
							// cell.getStringCellValue();
							zipCodesList.add(strType);
							System.out.print("Zipcode:" + strType + "\n");
						}
						mdaConfig.setZipCode(zipCodesList);
						System.out.println(mdaConfig.getZipCode().toString());
						break;
					case "dropDownList":
						if (mdaConfig.getHasDropDown().equalsIgnoreCase("no")) {
							break;
						} else {
							ArrayList<String> dropDownList = new ArrayList<String>();
							while (cellIterator.hasNext()) {
								cell = cellIterator.next();
								strType = cell.getStringCellValue();
								dropDownList.add(strType);
								System.out.print("DrpDown" + strType + "\n");
							}
							mdaConfig.setDropDownList(dropDownList);
							System.out.println(mdaConfig.getDropDownList().toString());
							break;
						}
					}

					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t");
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t");
					break;
				default:

				}
			}
			System.out.println("");
		}
		myWorkBook.close();
		return mdaConfig;

	}

	public CrossBrowserLoginConfig poplulateCBTdetails() throws IOException {
		CrossBrowserLoginConfig browserLoginConfig = new CrossBrowserLoginConfig();
		String fileName = "CBTLoginUsrPwd.xlsx";
		ClassLoader classLoader = getClass().getClassLoader();
		File excelFile = new File(classLoader.getResource(fileName).getFile());
		FileInputStream fis = new FileInputStream(excelFile);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					browserLoginConfig.setUsername(cell.getStringCellValue());
					System.out.print(browserLoginConfig.getUsername() + "\t str ");
					row = rowIterator.next();
					cellIterator = row.cellIterator();
					cell = cellIterator.next();
					browserLoginConfig.setAuthkey(cell.getStringCellValue());
					System.out.print(browserLoginConfig.getAuthkey() + "\t str");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t num");
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t bool");
					break;
				default:

				}
			}
			System.out.println("");
		}
		myWorkBook.close();
		return browserLoginConfig;
	}

	public ArrayList<URL> populateURLs() throws IOException {
		urlsList = new ArrayList<URL>();
		URL url;
		String fileName = "ListOfURLs.xlsx";
		ClassLoader classLoader = getClass().getClassLoader();
		File excelFile = new File(classLoader.getResource(fileName).getFile());
		FileInputStream fis = new FileInputStream(excelFile);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					String str1 = cell.getStringCellValue();
					System.out.print(str1 + "\t");
					url = new URL(str1);
					urlsList.add(url);

					break;

				default:

				}
			}
			System.out.println("");
		}
		myWorkBook.close();
		return urlsList;
	}
}
