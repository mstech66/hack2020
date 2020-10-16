package com.epam.framework.utilities;

import com.epam.framework.constants.LoggerConstants;
import com.epam.framework.exceptions.ConfigException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static final String configPath = "src/test/resources/config.properties";
    private static Properties properties;

    static {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configPath))) {
            properties = new Properties();
            loadProperties(bufferedReader);
        } catch (FileNotFoundException exception) {
            LoggerUtils.error("{} FileNotFoundException occurred in ConfigFileReader",
                    LoggerConstants.FRAMEWORK_EXCEPTION);
        } catch (IOException e) {
            LoggerUtils.error("{} IOException occurred in ConfigFileReader", LoggerConstants.FRAMEWORK_EXCEPTION);
        }
    }

    public static void loadProperties(BufferedReader bufferedReader) {
        try {
            properties.load(bufferedReader);
        } catch (IOException exception) {
            LoggerUtils.error("{} IOException occurred in ConfigFileReader while loading the properties",
                    LoggerConstants.FRAMEWORK_EXCEPTION);
        }
    }

    public static String getEmail() {
        return getPropertyValue("user.email");
    }

    public static String getHomepageURL() {
        return getPropertyValue("test.baseURI.homepage");
    }

    public static String getExtentReportsDirectoryPath() {
        return getPropertyValue("test.extentReportsDirectory");
    }

    public static String getScreenshotsDirectoryPath() {
        return getPropertyValue("test.data.screenshotsPath");
    }

    public static String getExcelPath() {
        return getPropertyValue("test.data.excelPath");
    }

    public static String getCopyExcelPath() {
        return getPropertyValue("test.data.copyExcelPath");
    }

    public static String getDbInstance() {
        return getPropertyValue("db.instance");
    }

    public static String getBrowser() {
        return getPropertyValue("browser");
    }

    private static String getPropertyValue(String property) {
        String propertyValue = properties.getProperty(property);
        if (propertyValue != null)
            return propertyValue;
        else {
            throw new ConfigException(property);
        }
    }
}
