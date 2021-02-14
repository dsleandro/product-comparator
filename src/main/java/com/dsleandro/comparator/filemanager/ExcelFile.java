package com.dsleandro.comparator.filemanager;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dsleandro.comparator.entity.Product;

public class ExcelFile {

	public Workbook createWorkbook() {
		return new XSSFWorkbook();
	}

	public void createSheetAndInsertData(Workbook workbook, List<Product> products) {
		Sheet sheet = workbook.createSheet("Products");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < Product.fieldNames.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(Product.fieldNames[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Create other rows and cells with products data
		int rowNum = 1;

		for (Product product : products) {
			Row row = sheet.createRow(rowNum++);

			Cell cell1 = row.createCell(0);
			cell1.setCellValue(product.getTitle());

			Cell cell2 = row.createCell(1);
			CellStyle cs = setCellStyle(cell2, workbook, 1);
			cell2.setCellStyle(cs);
			cell2.setCellValue(Double.parseDouble(product.getPrice()));

			Cell cell3 = row.createCell(2);
			cell3.setCellValue(product.getLink());
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < Product.fieldNames.length; i++) {
			sheet.autoSizeColumn(i);
		}

	}

	public void createFile(Workbook workbook, int sheetNumber) {

		String desktopPath = System.getProperty("user.home") + "\\Desktop\\";

		try (FileOutputStream fileOut = new FileOutputStream(desktopPath + "products" + sheetNumber + ".xlsx")) {
			workbook.write(fileOut);
			System.out.println("Archivo Creado exitosamente");
			System.out.println("Archivo creado en: " + desktopPath);
		} catch (Exception e) {
			System.out.println("El archivo no pudo ser creado");
			System.out.println("Si tienes el archivo abierto, cierralo e intenta nuevamente.");
		}

	}

	private CellStyle setCellStyle(Cell cell, Workbook workbook, int format) {

		CellStyle cs = workbook.createCellStyle();
		switch (format) {
		case 1:
			// currency
			cs.setDataFormat((short) 8);
			break;
		case 2:
			// dd-mm-yyyy
			CreationHelper createHelper = workbook.getCreationHelper();
			cs.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yyyy"));
			break;
		default:
			break;
		}

		return cs;

	}

}
