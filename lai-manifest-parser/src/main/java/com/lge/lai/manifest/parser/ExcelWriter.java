package com.lge.lai.manifest.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.common.annotations.VisibleForTesting;
import com.lge.lai.common.data.Feature;

public class ExcelWriter {
    static Logger LOGGER = LogManager.getLogger(ExcelWriter.class.getName());

    private String name;
    private Map<String, Integer> categories = new HashMap<String, Integer>();

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFCellStyle cs;

    private final static String[] MANIFEST_PROVIDER_COL = new String[] { "Type", "Component name", "Authorities", "Read permission", "Write permission" };
    private final static String[] MANIFEST_COMMON_COL = new String[] { "Action", "Type", "Component name", "Category", "Data (Scheme)", "Data (Host)", "Data (Port)", "Data (Path)", "Data (Pattern)", "Data (Prefix)", "Data (Mime)" };

    private final static Map<String, String[]> CATEGORY_COL_TABLE = new HashMap<>();
    static {
        CATEGORY_COL_TABLE.put(Manifest.ACTIVITY, MANIFEST_COMMON_COL);
        CATEGORY_COL_TABLE.put(Manifest.ACTIVITY_ALIAS, MANIFEST_COMMON_COL);
        CATEGORY_COL_TABLE.put(Manifest.RECEIVER, MANIFEST_COMMON_COL);
        CATEGORY_COL_TABLE.put(Manifest.SERVICE, MANIFEST_COMMON_COL);
        CATEGORY_COL_TABLE.put(Manifest.PROVIDER, MANIFEST_PROVIDER_COL);
    }

    public ExcelWriter(String name) {
        this.name = name;
        initialize(name);
    }

    private void initialize(String name) {
        String filename = name + ".xls";
        if (new File(filename).exists()) {
            try {
                InputStream is = new FileInputStream(filename);
                workbook = new HSSFWorkbook(is);
            } catch (IOException e) {
                LOGGER.error("I/O exception '" + filename + "'");
                throw new RuntimeException("Cannot initialize due to '" + e + "'");
            }
        } else {
            workbook = new HSSFWorkbook();
        }
        cs = getCellStyle();
    }

    public void addCategory(String category) {
        if (!categories.containsKey(category)) {
            categories.put(category, 0);
        }
        sheet = workbook.getSheet(category);
        if (sheet == null) {
            sheet = workbook.createSheet(category);
            createColumnTitleCell(sheet, category);
        }
    }

    public void setData(String category, Object obj) {
        HSSFRow row = createRow(category);
        String[] columnType = CATEGORY_COL_TABLE.get(category);
        Feature feature = (Feature)obj;
        if (category.equals(Manifest.PROVIDER)) {
            setProviderDataToCell(row, feature, columnType);
        } else {
            setASBDataToCell(row, feature, columnType);
        }
    }

    private HSSFCellStyle getCellStyle() {
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        return cs;
    }

    private void createColumnTitleCell(HSSFSheet sheet, String category) {
        HSSFRow row = sheet.createRow(0);
        String[] columnType = CATEGORY_COL_TABLE.get(category);
        for (int i = 0; i < columnType.length; i++) {
            row.createCell(i).setCellValue(columnType[i]);
            row.getCell(i).setCellStyle(cs);
        }
    }

    private HSSFRow createRow(String category) {
        if (!sheet.getSheetName().equals(category)) {
            sheet = workbook.getSheet(category);
        }
        int index = categories.get(category);
        categories.put(category, index + 1);
        return sheet.createRow(index + 1);
    }

    private void setProviderDataToCell(HSSFRow row, Feature feature, String[] columnType) {
        row.createCell(0).setCellValue(feature.type);
        row.createCell(1).setCellValue(feature.className);
        row.createCell(2).setCellValue(feature.authorities);
        row.createCell(3).setCellValue(feature.readPermission);
        row.createCell(4).setCellValue(feature.writePermission);

        for (int i = 0; i < columnType.length; i++) {
            row.getCell(i).setCellStyle(cs);
            sheet.autoSizeColumn(i);
        }
        write();
    }

    private void setASBDataToCell(HSSFRow row, Feature feature, String[] columnType) {
        row.createCell(0).setCellValue(feature.actionName);
        row.createCell(1).setCellValue(feature.type);
        row.createCell(2).setCellValue(feature.className);
        row.createCell(3).setCellValue(feature.getCategories());
        row.createCell(4).setCellValue(feature.getSchemes());
        row.createCell(5).setCellValue(feature.getHosts());
        row.createCell(6).setCellValue(feature.getPorts());
        row.createCell(7).setCellValue(feature.getPaths());
        row.createCell(8).setCellValue(feature.getPathPatterns());
        row.createCell(9).setCellValue(feature.getPathPrefixes());
        row.createCell(10).setCellValue(feature.getMimeTypes());

        for (int i = 0; i < columnType.length; i++) {
            row.getCell(i).setCellStyle(cs);
            sheet.autoSizeColumn(i);
        }
        write();
    }

    private void write() {
        try {
            OutputStream os = new FileOutputStream(name + ".xls");
            workbook.write(os);
        } catch (IOException e) {
            LOGGER.error("I/O exception '" + name + ".xls" + "'");
        }
    }

    @VisibleForTesting
    public String getCurrentSheetName() {
        return sheet.getSheetName();
    }

    @VisibleForTesting
    public int getColumnIndex(String category) {
        return categories.get(category);
    }
}
