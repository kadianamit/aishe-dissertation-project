package aishe.gov.in.utility;

import aishe.gov.in.enums.ExportType;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Component
public class NullValueHandler {
    private static final Logger log = LoggerFactory.getLogger(NullValueHandler.class);

    public String nullValueHandler(String value) {
        if (null != value)
            return value.equalsIgnoreCase("null") ? null : value;
        return null;
    }

    public String allAssignForNull(String value) {
        if (null != value && !value.isEmpty())
            return value.equalsIgnoreCase("null") ? "ALL" : value;
        return "ALL";
    }

    public Integer nullValueHandleInteger(String value) {
        if (null != value)
            return value.equalsIgnoreCase("null") ? null : Integer.valueOf(value);
        return null;
    }

    public Boolean nullValueHandlerBoolean(String value) {
        if (null != value)
            return value.equalsIgnoreCase("null") ? null : Boolean.valueOf(value);
        return null;
    }


    public byte[]
    exportReport(JRDataSource customDataSource, String reportFormatType, JasperReport jasperReport,
                 Map<String, Object> parameters) throws JRException {
        try {

            if (ExportType.PDF.type.equals(reportFormatType)) {
                return JasperRunManager.runReportToPdf(jasperReport, parameters, customDataSource);
            } else if (ExportType.EXCEL.type.equals(reportFormatType)) {
                return exportReportToExcel(customDataSource, jasperReport, parameters);
            } else if (ExportType.CSV.type.equals(reportFormatType)) {

                return exportReportToCSV(customDataSource, jasperReport, parameters);
            }
        } catch (Exception e) {
            log.error("Exception in class: GenerateReportAction and method: exportReport()- ", e);
            log.error("Exception while exporting report.");
        }
        return null;
    }


    private byte[] exportReportToExcel(JRDataSource customDataSource, JasperReport jasperReport, Map<String, Object> parameters) throws JRException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, customDataSource);
        JRXlsxExporter exporter = new JRXlsxExporter();
        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(Boolean.FALSE);
        configuration.setDetectCellType(Boolean.TRUE);
        configuration.setWhitePageBackground(Boolean.TRUE);
        configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
        configuration.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
        configuration.setImageBorderFixEnabled(Boolean.TRUE);
        configuration.setCollapseRowSpan(Boolean.TRUE);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        return xlsReport.toByteArray();
    }

    private byte[] exportReportToCSV(JRDataSource customDataSource, JasperReport jasperReport, Map<String, Object> parameters) throws JRException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, customDataSource);
        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(byteArrayOutputStream));
        exporter.exportReport();
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] fileExportByType(ExportType exportType, JasperPrint print) throws JRException, IOException {
        switch (exportType.getType()) {
            case "PDF": {
                return JasperExportManager.exportReportToPdf(print);
            }
            case "EXCEL": {
                JRXlsxExporter exporter = new JRXlsxExporter();
                ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                exporter.setExporterInput(new SimpleExporterInput(print));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(Boolean.FALSE);
                configuration.setDetectCellType(Boolean.TRUE);
                configuration.setWhitePageBackground(Boolean.TRUE);
                configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                configuration.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
                configuration.setImageBorderFixEnabled(Boolean.TRUE);
                configuration.setCollapseRowSpan(Boolean.TRUE);
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                byte[] byteArray = xlsReport.toByteArray();
                xlsReport.close();
                return byteArray;

            }
            default: {
                break;
            }
        }
        return new byte[0];
    }

    public String process(Object value) {
        if (null != value) {
            if (value instanceof Boolean) {
                return (Boolean) value ? "YES" : "NO";
            }
            return value.toString();
        }
        return "";
    }
}