package com.epam.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;

    public ConfigFileReader() {
        final String configPath = "src/test/resources/config.properties";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configPath))) {
            properties = new Properties();
            loadProperties(bufferedReader);
        } catch (FileNotFoundException exception) {
            LoggerUtil.error("FileNotFoundException occurred in ConfigFileReader");
        } catch (IOException e) {
            LoggerUtil.error("IOException occurred in ConfigFileReader");
        }
    }

    public void loadProperties(BufferedReader bufferedReader) {
        try {
            properties.load(bufferedReader);
        } catch (IOException exception) {
            LoggerUtil.error("IOException occurred in ConfigFileReader while loading the properties");
        }
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser != null) return browser;
        else {
            throw new PageException("browser is not specified in Properties file");
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new PageException("driverPath is not specified in Properties file");
    }

    public String getHTMLReportPath() {
        String htmlPath = properties.getProperty("test.report.htmlPath");
        if (htmlPath != null) return htmlPath;
        else throw new PageException("htmlPath is not specified in Properties file");
    }

    public String getPDFReportPath() {
        String pdfPath = properties.getProperty("test.report.pdfPath");
        if (pdfPath != null) return pdfPath;
        else throw new PageException("pdfPath is not specified in Properties file");
    }

    public String getExcelFilePath(){
        String excelPath = properties.getProperty("test.data.excelPath");
        if (excelPath != null) return excelPath;
        else throw new PageException("excelPath is not specified in Properties file");
    }

    public String getDbUsername(){
        String username = properties.getProperty("db.username");
        if (username != null) return username;
        else throw new PageException("Username is not specified in Properties file");
    }

    public String getDbPassword(){
        String password = properties.getProperty("db.password");
        if (password != null) return password;
        else throw new PageException("Password is not specified in Properties file");
    }

    public String getDbUrl(){
        String dbUrl = properties.getProperty("db.url");
        if (dbUrl != null) return dbUrl;
        else throw new PageException("Db Url is not specified in Properties file");
    }

    public String getExtentReportPath(){
        String reportPath = properties.getProperty("test.extentReport");
        if(reportPath != null) return reportPath;
        else throw new PageException("ExtentReportPath is not specified in Properties file");
    }

    public String getScreenshotsDirectoryPath(){
        String screenshotsPath = properties.getProperty("test.data.screenshotsPath");
        if(screenshotsPath != null) return screenshotsPath;
        else throw new PageException("screenshotsPath is not specified in Properties file");
    }
}
