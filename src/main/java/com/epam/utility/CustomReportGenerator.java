package com.epam.utility;

import com.itextpdf.html2pdf.HtmlConverter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CustomReportGenerator implements IReporter {

    final String HEAD = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>TestNG Reports</title>\n" +
            "\t<style>\n" +
            "\t\ttable {\n" +
            "\t\t  font-family: arial, sans-serif;\n" +
            "\t\t  border-collapse: collapse;\n" +
            "\t\t  width: 100%;\n" +
            "\t\t}\n" +
            "\t\ttd, th {\n" +
            "\t\t  border: 1px solid #dddddd;\n" +
            "\t\t  text-align: left;\n" +
            "\t\t  padding: 8px;\n" +
            "\t\t}\n" +
            "\t\ttr:nth-child(even) {\n" +
            "\t\t  background-color: #dddddd;\n" +
            "\t\t}\n" +
            "\t\t.success{\n" +
            "\t\t\tbackground-color: #3ddc84;\n" +
            "\t\t}\n" +
            "\t\t.failed{\n" +
            "\t\t\tbackground-color: #D50000;\n" +
            "\t\t}\n" +
            "\t</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<center><h1>Custom HTML Report</h1></center>\n" +
            "\t<table>\n";
    final String END = "\t</table>\n" +
            "</body>\n" +
            "</html>";

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        StringBuilder stringBuilder = new StringBuilder(HEAD);
        ConfigFileReader configFileReader = new ConfigFileReader();

        for (ISuite iSuite : list1) {
            Map<String, ISuiteResult> suiteResults = iSuite.getResults();
            for (ISuiteResult result : suiteResults.values()) {
                ITestContext context = result.getTestContext();
                LoggerUtil.info(context.getName() + " " + context.getStartDate() + " " + context.getEndDate()
                        + context.getSuite().getName());
                String suiteRow = "<tr>" +
                        "<th colspan='4' style='text-align:center;'>" + context.getSuite().getName() + "</th>" +
                        "</tr>";
                String testMethodHeader = "\t\t<tr>\n" +
                        "\t\t\t<th>Index</th>\n" +
                        "\t\t\t<th>Test</th>\n" +
                        "\t\t\t<th>Class</th>\n" +
                        "\t\t\t<th>Result</th>\n" +
                        "\t\t</tr>";
                stringBuilder.append(suiteRow);
                stringBuilder.append(testMethodHeader);
                IResultMap tests = context.getPassedTests();
                int index = 1;
                for (ITestResult iTestResult : tests.getAllResults()) {
                    ITestNGMethod iTestNGMethod = iTestResult.getMethod();
                    LoggerUtil.info(iTestNGMethod.getMethodName() + " " + (iTestResult.getStatus() == ITestResult.SUCCESS) + " " + iTestNGMethod.getClass().getName());
                    String tempRow = "<tr>" +
                            "<td>" + index + "</td> <td>" + iTestNGMethod.getMethodName() + "</td>" +
                            getColoredTableData(iTestResult.getStatus()) +
                            "<td>" + iTestNGMethod.getClass().getSimpleName() + "</td>" +
                            "</tr>";
                    stringBuilder.append(tempRow);
                    index++;
                }
            }
        }
        stringBuilder.append(END);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configFileReader.getHTMLReportPath()))) {
            String html = stringBuilder.toString();
            bufferedWriter.write(html);
            writePDF(html, configFileReader.getPDFReportPath());
        } catch (IOException e) {
            LoggerUtil.info("IOException" + e);
        }
    }

    public void writePDF(String html, String pdfPath) throws IOException {
        HtmlConverter.convertToPdf(html, new FileOutputStream(pdfPath));
    }

    public String getColoredTableData(int result) {
        switch (result) {
            case ITestResult.SUCCESS:
                return "<td class='success'>SUCCESS</td>";
            case ITestResult.FAILURE:
                return "<td class='failed'>FAILED</td>";
            case ITestResult.SKIP:
                return "<td class='skipped'>SKIPPED</td>";
            default:
                return "<td>ERROR!</td>";
        }
    }
}
