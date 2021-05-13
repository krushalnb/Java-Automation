package com.automation.init;

import java.io.BufferedOutputStream;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {
	public static String rndmString(int length) {
		String rnd1 = RandomStringUtils.randomAlphabetic(length);
		return rnd1; 
	}
 
	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	static int hour1 = TestData.randBetween(7, 12); // Hours will be displayed
													// in between 9 to 22
	static int min = TestData.randBetween(0, 59);

	static int hour2 = TestData.randBetween(13, 20);

	public static String intime = hour1 + ":" + min;
	public static String outtime = hour2 + ":" + min;

	public static int diff = (((hour2) * 60) + min) - (((hour1) * 60) + min);

	public static String total_time = Integer.toString(diff);

	public static String getCurrentDateTime() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Calcutta");
		String t = timeZone.getID().toString();
		df.setTimeZone(TimeZone.getTimeZone(t));
		System.out.println(df.format(calobj.getTime()));
		String time = df.format(calobj.getTime());
		return time;
	}

	/**
	 * Get Data from the Excel
	 * 
	 * @param sheetIndex
	 * @return
	 */

	public static Sheet getExcelSheet(int sheetIndex) {
		String dataFilePath = "Resources/DataConfigurations.xlsx";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {

			/*
			 * System.out.println("full path " + datafile.getAbsolutePath() +
			 * " con " + datafile.getCanonicalPath());
			 */

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();

		} catch (Exception e) {

		}
		return firstSheet;
	}

	public static Sheet getExcelSheetforUser(int sheetIndex) {
		String dataFilePath = "Resources/data.xlsx";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {

			/*
			 * System.out.println("full path " + datafile.getAbsolutePath() +
			 * " con " + datafile.getCanonicalPath());
			 */

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();
		} catch (Exception e) {

		}
		return firstSheet;
	}

	/*
	 * datafile.properties Read values
	 * 
	 */
	public static String readPropertiesFile(String propertyFile, String propertiesName) {
		String result = "";
		File file = new File(propertyFile);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
			result = prop.getProperty(propertiesName);
			System.out.println("Read from propertys file: " + result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}

		return result;
	}

	public static int getlastRowcount(int sheetNo) {
		return getExcelSheet(sheetNo).getLastRowNum();
	}

	/**
	 * Getting Downloaded file path
	 * 
	 * @param sheetIndex
	 * @return
	 */

	public static HSSFSheet getDonloadedExcelSheet(int sheetIndex) {
		String ss = "==download path system====";
		System.out.println("===*******====  " + ss);
		String dataFilePath = ss + "EmployeeMaster.xls";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		HSSFSheet firstSheet = null;

		try {

			/*
			 * System.out.println("full path " + datafile.getAbsolutePath() +
			 * " con " + datafile.getCanonicalPath());
			 */

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			/* XSSFWorkbook(inputStream) */
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

			firstSheet = workbook.getSheetAt(sheetIndex);
			workbook.close();
			inputStream.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("====:" + firstSheet.getSheetName());

		return firstSheet;
	}

	public static String cell_data;
	public static short fg;

	public static short getcellData() {
		System.out.println(getDonloadedExcelSheet(0));
		cell_data = getDonloadedExcelSheet(0).getRow(3).getCell(1).getStringCellValue();

		System.out.println("--- :: " + cell_data);

		fg = getDonloadedExcelSheet(0).getRow(3).getCell(1).getCellStyle().getFillForegroundColor();
		System.out.println("-- :: " + fg);

		return fg;

	}

	public static long sysStartTime() {
		long startTime = System.currentTimeMillis();
		return startTime;
	}

	public static long sysEndTime() {
		long startTime = System.currentTimeMillis();
		return startTime;
	}

	public static long calTotaltimeTaken(long start, long end) {
		long total = (end - start) / 1000;
		return total;
	}

	public static String rndmTitle(int i) {
		String sc = "DS " + rndmString(i) + " by Automation " + rndmString(i);
		return sc;
	}

	public static String rndmEpisodeName(int i) {
		String sc = "ES " + rndmString(i) + " Automation";
		return sc;
	}

	/*
	 * public static String getTimeZone() { String url =
	 * readDataProperties("timezone"); return url; }
	 * 
	 * public static String getURL() { //String url = readDataProperties("Url");
	 * String url = System.getenv("FALKONRY_HOST_URL"); if(url == null){ url =
	 * readDataProperties("Url"); } return url; }
	 * 
	 * 
	 * public static String getAuthToken() { String token =
	 * System.getenv("FALKONRY_TOKEN"); if(token == null){
	 * if(getURL().contains("https://dev.falkonry.ai")){ token =
	 * readDataProperties("DevToken"); } else{ token =
	 * readDataProperties("StageToken"); } } return token; }
	 * 
	 * public static String getUsernameFL() { DataFormatter formatter = new
	 * DataFormatter(); Cell cell = getExcelSheet(0).getRow(3).getCell(3);
	 * String username = formatter.formatCellValue(cell); String username =
	 * readDataProperties("FalkonryEmail"); return username; }
	 * 
	 * public static String getPasswordFL() { DataFormatter formatter = new
	 * DataFormatter(); Cell cell = getExcelSheet(0).getRow(3).getCell(4);
	 * String pswd = readDataProperties("FalkonryPassword"); return pswd; }
	 * 
	 * public static String getUsernameGL() { DataFormatter formatter = new
	 * DataFormatter(); Cell cell = getExcelSheet(0).getRow(6).getCell(3);
	 * String username = readDataProperties("GoogleEmail"); return username; }
	 * 
	 * public static String getPasswordGL() { DataFormatter formatter = new
	 * DataFormatter(); Cell cell = getExcelSheet(0).getRow(6).getCell(4);
	 * String pswd = readDataProperties("GooglePasword"); return pswd; }
	 * 
	 * public static String getInvalidUsername() { DataFormatter formatter = new
	 * DataFormatter(); Cell cell = getExcelSheet(0).getRow(3).getCell(3);
	 * String username = readDataProperties("InvalidEmail"); return username; }
	 * 
	 * public static String getInvalidPswd() { DataFormatter formatter = new
	 * DataFormatter(); Cell cell = getExcelSheet(0).getRow(3).getCell(4);
	 * String pswd = readDataProperties("InvalidPassword"); return pswd; }
	 * 
	 * public static String getFirstSourceDataFile(String name) { DataFormatter
	 * formatter = new DataFormatter(); Cell cell =
	 * getExcelSheet(1).getRow(row).getCell(cel); String fsource =
	 * formatter.formatCellValue(cell); String fsource =
	 * readDataProperties(name); return fsource; }
	 * 
	 * public static String getValue(String name) { String value =
	 * readDataProperties(name); return value; }
	 * 
	 * public static String getSecondSecondSourceDataFile(int row, int cel) {
	 * 
	 * DataFormatter formatter = new DataFormatter(); Cell cell =
	 * getExcelSheet(1).getRow(row).getCell(cel); String ssource =
	 * readDataProperties("SecondSourceFilePath"); return ssource; } public
	 * static String getJSONsourceFile(String name) {
	 * 
	 * String ssource = readDataProperties(name); return ssource; }
	 */

	public static String getLinkedEmailId() {
		DataFormatter formatter = new DataFormatter();
		Cell cell = getExcelSheet(0).getRow(4).getCell(1);
		String email = formatter.formatCellValue(cell);
		return email;
	}

	public static String getLinkedPaswd() {
		DataFormatter formatter = new DataFormatter();
		Cell cell = getExcelSheet(0).getRow(5).getCell(1);
		String pswd = formatter.formatCellValue(cell);
		return pswd;
	}

	public static String getSubCategory() {
		DataFormatter formatter = new DataFormatter();
		Cell cell = getExcelSheet(0).getRow(8).getCell(0);
		String pswd = formatter.formatCellValue(cell);
		return pswd;
	}

	public static String getSearchSoftware() {
		DataFormatter formatter = new DataFormatter();
		Cell cell = getExcelSheet(0).getRow(11).getCell(0);
		String pswd = formatter.formatCellValue(cell);
		return pswd;
	}

	/*
	 * public static int gettimeColmnNoforSourceDataJSON() {
	 * 
	 * 
	 * DataFormatter formatter = new DataFormatter(); Cell cell =
	 * getExcelSheet(1).getRow(row).getCell(cel); String path =
	 * formatter.formatCellValue(cell); int indentifierColumnNo =
	 * Integer.parseInt(path);
	 * 
	 * String tsource = readDataProperties("timePositionSourceJson"); int
	 * timeclmn = Integer.parseInt(tsource); return timeclmn; }
	 * 
	 * public static int getEntityColmnNoforSourceDataJSON() {
	 * 
	 * 
	 * DataFormatter formatter = new DataFormatter(); Cell cell =
	 * getExcelSheet(1).getRow(row).getCell(cel); String path =
	 * formatter.formatCellValue(cell); int indentifierColumnNo =
	 * Integer.parseInt(path);
	 * 
	 * String esource = readDataProperties("entityPositionSourceJson"); int
	 * entityclmn = Integer.parseInt(esource); return entityclmn; }
	 * 
	 * public static int getColmnNoforFactsDataJSON(String value) {
	 * 
	 * String esource = readDataProperties(value); int entityclmn =
	 * Integer.parseInt(esource); return entityclmn; }
	 * 
	 * public static String getFilePath(String name) {
	 * 
	 * DataFormatter formatter = new DataFormatter(); Cell cell =
	 * getExcelSheet(1).getRow(row).getCell(cel); String fsource =
	 * formatter.formatCellValue(cell);
	 * 
	 * String file = readDataProperties(name); return file; }
	 * 
	 * public static int getColumnIndex(String name) { String clmn =
	 * readDataProperties(name); int clmnNo = Integer.parseInt(clmn); return
	 * clmnNo; }
	 */
	/*
	 * public static int getEndTimeColumnNoforFacts(){
	 * 
	 * DataFormatter formatter = new DataFormatter(); Cell cell =
	 * getExcelSheet(1).getRow(1).getCell(9); String path =
	 * formatter.formatCellValue(cell); int indentifierColumnNo =
	 * Integer.parseInt(path); return indentifierColumnNo; }
	 */

	/*
	 * Getting the Time Format from uploaded source data
	 */
	public static String getTimeFormat(String s) {

		String ss = null;

		final String[] formats = { "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm",
				"yyyy-MM-dd'T'HH:mmZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "MM-DD-YYYY HH:mm:ss", "MM/dd/yyyy HH:mm:ss",
				"MM/dd/yyyy'T'HH:mm:ss.SSS'Z'", "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
				"MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss", };

		if (s != null) {
			for (String parse : formats) {
				SimpleDateFormat sdf = new SimpleDateFormat(parse);
				try {
					sdf.parse(s);
					System.err.println("Printing the value of " + parse);
					ss = parse;
				} catch (ParseException e) {

				}
			}
		}
		return ss;
	}

	/*
	 * Get the Time [YYYY/MM/DD HH:MM:SS] from the last row which has Entity
	 */
	public static String getTimefromCSV(String csvFile, String where, String format, int startRowHasEntity,
			int endRowHasEntity) throws ParseException, IOException {

		// String format = getTimeFormat(getFirstTimefromCSV(csvFile));
		System.err.println(format);

		ArrayList<String> linesList = new ArrayList<>();
		BufferedReader csvReader;
		String java_date = "";
		String csvSplitBy = ",";
		String[] l = new String[10000];
		try {
			// Read the CSV file into an ArrayList array for easy processing.
			String line;
			csvReader = new BufferedReader(new FileReader(csvFile));
			while ((line = csvReader.readLine()) != null) {
				linesList.add(line);

				// System.err.println(line)
			}

			if (where.contains("end")) {
				l = linesList.get(linesList.size() - endRowHasEntity).split(csvSplitBy);
			} else {
				l = linesList.get(startRowHasEntity).split(csvSplitBy);
			}
			long unix_seconds = 0;
			String d1 = "";

			try {

				if (l[0].length() == 16) {
					unix_seconds = Long.parseLong(l[0]) / 1000;
				} else if (l[0].length() == 10) {
					unix_seconds = Long.parseLong(l[0]) * 1000;
				} else {
					unix_seconds = Long.parseLong(l[0]);
				}

				Calendar now = Calendar.getInstance();
				TimeZone timeZone = TimeZone.getTimeZone("Asia/Calcutta");
				String t = timeZone.getID().toString();

				// System.err.println(t);

				SimpleDateFormat jdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				jdf.setTimeZone(TimeZone.getTimeZone(t));
				java_date = jdf.format(unix_seconds);
				System.out.println("\n" + java_date + "\n");

				csvReader.close();

			}

			catch (NumberFormatException n) {
				d1 = l[0];

				final String[] formats = { "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm",
						"yyyy-MM-dd'T'HH:mmZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
						"YYYY/MM/DD HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "MM/dd/yyyy HH:mm:ss", "yy-MM-dd HH:mm:ss",
						"MM/dd/yyyy'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSSZ",
						"MM/dd/yyyy'T'HH:mm:ss.SSS", "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss",
						"MM-dd-yyyy HH:mm:ss", "YY-MM-DD HH:mm:ss", "MM/DD/YY HH:mm:ss" };

				if (format.equalsIgnoreCase("ISO 8601")) {
					for (String parse : formats) {
						SimpleDateFormat sdf = new SimpleDateFormat(parse);
						try {
							sdf.parse(d1);
							format = parse;
							d1 = d1.replace("Z", "+5:30");
							// System.err.println("Printing the value of:::- " +
							// ss);

						} catch (ParseException e) {

						}
					}
				} else if (format.equals("YY-MM-DD HH:mm:ss")) {
					format = "yy-MM-dd HH:mm:ss";
				} else if (format.equals("YYYY/MM/DD HH:mm:ss")) {
					format = "yyyy/MM/dd HH:mm:ss";
				} else if (format.equals("YY/MM/DD HH:mm:ss")) {
					format = "yy/MM/dd HH:mm:ss";
				} else if (format.equals("MM/DD/YY HH:mm:ss")) {
					format = "MM/dd/yy HH:mm:ss";
				}

				else {
					for (String f : formats) {

						if (format.equalsIgnoreCase(f)) {
							format = f;
						}
					}
				}

				Date initDate = new SimpleDateFormat(format).parse(d1);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				java_date = formatter.format(initDate);
				System.out.println("-- \n" + java_date + "\n");

				csvReader.close();
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return java_date;

	}

	/*
	 * Identify the Min-Max time from the ArrayList
	 */
	public static ArrayList<String> identifyMinMaxTime(ArrayList<String> start, ArrayList<String> end)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		ArrayList<String> minMaxDates = new ArrayList<String>();
		ArrayList<Date> startD = new ArrayList<Date>();
		ArrayList<Date> endD = new ArrayList<Date>();

		for (String s : start) {
			startD.add(sdf.parse(s));
		}
		for (String s : end) {
			endD.add(sdf.parse(s));
		}

		Date minStartDT = Collections.min(startD);
		Date maxEndDT = Collections.max(endD);

		minMaxDates.add(sdf.format(minStartDT));
		minMaxDates.add(sdf.format(maxEndDT));

		System.err.println(minMaxDates);

		return minMaxDates;
	}

	public static String getDownloadFileNamefromfolder(String foldername, String fileName) {
		String path1 = new File(foldername).getAbsolutePath();
		System.out.println("---------" + path1);
		File folder = new File(path1);
		File[] listOfFiles = folder.listFiles();
		String x = null;

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				// System.out.println("File " + listOfFiles[i].getName());

				if (listOfFiles[i].getName().equals(fileName)) {
					x = listOfFiles[i].getName();
					System.err.println("File name Matched: " + x);
				}
			}
		}
		return x;
	}

	public static String getDownloadEpisodeFileNamefromfolder(String foldername, String fileName) {
		String path1 = new File(foldername).getAbsolutePath();
		System.out.println("---------" + path1);
		File folder = new File(path1);
		File[] listOfFiles = folder.listFiles();
		String x = null;
		String s1 = null;

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				// System.out.println("File " + listOfFiles[i].getName());

				if (listOfFiles[i].getName().equals(fileName)) {
					x = listOfFiles[i].getName();
					String[] s = x.split("-EpisodeGallery-");
					s1 = s[0];
					System.err.println("File name Matched: " + x);
				}
			}
		}
		return s1;
	}

	/**
	 * User Email id and Password data into data.xls file
	 */
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell Cell;
	public static XSSFRow Row;

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {
			String path = new File(FilePath).getAbsolutePath();
			FileInputStream ExcelFile = new FileInputStream(path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;
			int startCol = 1;
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();

			int totalCols = ExcelWSheet.getRow(0).getLastCellNum() - 1;
			tabArray = new String[totalRows][totalCols];
			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;

				for (int j = startCol; j <= totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
					System.out.println(tabArray[ci][cj]);
				}
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			int dataType = Cell.getCellType();
			System.out.println("==--- >> " + dataType + "==--->>" + Cell);
			if (dataType == 3) {
				return "";
			} else {
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			throw (e);
		}
	}

	public static String getRecentFileName(String path) {

		Path parentFolder = Paths.get(path);
		String s = null;

		Optional<File> mostRecentFileOrFolder = Arrays.stream(parentFolder.toFile().listFiles())
				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));

		Optional<File> mostRecentFile = Arrays.stream(parentFolder.toFile().listFiles()).filter(f -> f.isFile())
				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));

		if (mostRecentFile.isPresent()) {
			File mostRecent = mostRecentFileOrFolder.get();

			s = mostRecent.getName();

			System.out.println("most recent is:" + s);
		} else {
			System.out.println("folder is empty!");
		}
		return s;
	}

	public static String getRecentFolderName(String path) {

		Path parentFolder = Paths.get(path);
		String s = null;

		Optional<File> mostRecentFileOrFolder = Arrays.stream(parentFolder.toFile().listFiles())
				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));

		Optional<File> mostRecentFolder = Arrays.stream(parentFolder.toFile().listFiles()).filter(f -> f.isDirectory())
				.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));

		if (mostRecentFolder.isPresent()) {
			File mostRecent = mostRecentFileOrFolder.get();

			s = mostRecent.getName();

			System.out.println("most recent is:" + s);

		} else {
			System.out.println("folder is empty!");
		}
		return s;
	}

	public static void deleteFolder(String pathtofolder) {
		File index = new File(pathtofolder);

		System.err.println(index);

		String[] entries = index.list();
		for (String sss : entries) {
			File currentFile = new File(index.getPath(), sss);
			currentFile.delete();
		}
		if (!index.exists()) {
			index.mkdir();
		} else {
			index.delete();
			System.err.println("Folder deleted");
		}

	}

	public static String filesfromFolder(String pathf) {
		String filename = null;

		File folder = new File(pathf);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				filename = listOfFiles[i].getName();
				System.out.println("File:::::: " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		return filename;
	}

	/*
	 * Unzip Downloaded File Extract into another Folder
	 * 
	 */
	public static String extractDownloadedZipFile(String zipFilePath, String destDirectory) throws IOException {
		// String zipFilePath =
		// "E:\\FalkonryAutomation\\FalkonryAutomation\\Download\\DS ExS by
		// Automation
		// Xja create Run Model full flow-EpisodeGallery-1508840785280.zip";
		// String destDirectory =
		// "E:\\FalkonryAutomation\\FalkonryAutomation\\zipoutput";
		String filePath = null;
		try {
			filePath = unzip(zipFilePath, destDirectory);
		} catch (Exception ex) {
			// some errors occurred
			ex.printStackTrace();
		}
		return filePath;
	}

	public static String unzip(String zipFilePath, String destDirectory) throws IOException {

		File destDir = new File(destDirectory).getAbsoluteFile();
		String filePath = null;

		if (!destDir.exists()) {
			destDir.mkdir();
		}

		String path1 = new File(zipFilePath).getAbsolutePath();
		System.err.println(path1);

		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			filePath = destDirectory + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, filePath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
		return filePath.replaceAll("\\\\", "/");
	}

	private static final int BUFFER_SIZE = 4096;

	private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}
}
