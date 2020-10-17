package com.epam.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotGenerator {

    public static void takeScreenshot(WebDriver webDriver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        ConfigFileReader configFileReader = new ConfigFileReader();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDateTime = dateTime.format(dateTimeFormatter);
        String ss = configFileReader.getScreenshotsDirectoryPath() + "/Screenshot_" + formattedDateTime + ".jpg";
        File destinationFile = new File(ss);
        FileUtils.copyFile(screenshotFile, destinationFile);
    }
}
