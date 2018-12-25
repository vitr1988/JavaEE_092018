package ru.otus.misc.jasperreports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportFiller {

    private static final Logger logger = Logger.getLogger(ReportFiller.class);

    private String reportFileName;

    private JasperReport jasperReport;

    private JasperPrint jasperPrint;

    private DataSource dataSource;

    private Map<String, Object> parameters;

    public ReportFiller() {
        parameters = new HashMap<>();
    }

    public void prepareReport() {
        compileReport();
        fillReport();
    }

    public void compileReport() {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/".concat(reportFileName));
            jasperReport = JasperCompileManager.compileReport(reportStream);
            JRSaver.saveObject(jasperReport, reportFileName.replace(".jrxml", ".jasper"));
        } catch (JRException ex) {
            logger.error(null, ex);
        }
    }

    public void fillReport() {
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
//            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Employee()));
//            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
        } catch (JRException | SQLException ex) {
            logger.error(null, ex);
        }
    }

    public void print(OutputStream out) {
        try {
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (JRException ex) {
            logger.error(null, ex);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

}
