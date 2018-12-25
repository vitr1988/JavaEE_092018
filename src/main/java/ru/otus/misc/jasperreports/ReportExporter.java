package ru.otus.misc.jasperreports;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportExporter {
    private static final Logger logger = Logger.getLogger(ReportExporter.class);

    private JasperPrint jasperPrint;
    private Exporter exporter;

    public ReportExporter() {
    }

    public ReportExporter(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public void exportToPdf(String fileName, String author) throws Exception {
        export(jasperPrint, REPORT_FORMAT.PDF, fileName);
    }

    public void exportToXlsx(String fileName, String sheetName) throws Exception {
        export(jasperPrint, REPORT_FORMAT.XLSX, fileName);
    }

    public void exportToCsv(String fileName) throws Exception {
        export(jasperPrint, REPORT_FORMAT.CSV, fileName);
    }

    public void exportToHtml(String fileName) throws Exception {
        export(jasperPrint, REPORT_FORMAT.HTML, fileName);
    }

    public void export(final JasperPrint print, REPORT_FORMAT format, String fileName) throws JRException, IOException {

        File yourFile = new File(fileName);
        yourFile.createNewFile(); // if file already exists will do nothing
        FileOutputStream out = new FileOutputStream(yourFile, false);

        boolean html = false;

        switch (format) {
            case HTML:
                exporter = new HtmlExporter();
                exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
                html = true;
                break;

            case CSV:
                exporter = new JRCsvExporter();
                break;

            case XML:
                exporter = new JRXmlExporter();
                break;

            case XLSX:
                exporter = new JRXlsxExporter();
                break;

            case PDF:
                exporter = new JRPdfExporter();
                break;

            default:
                throw new JRException("Unknown report format: " + format.toString());
        }

        if (!html) {
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        }

        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.exportReport();
    }

    public enum REPORT_FORMAT {
        HTML, CSV, XML, XLSX, PDF
    }
}
