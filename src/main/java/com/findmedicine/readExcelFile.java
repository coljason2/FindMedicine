package com.findmedicine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.findmedicine.entity.data;

public class readExcelFile {
	private List<data> infiles = new ArrayList<data>();

	public List<data> getInfiles() {
		return infiles;
	}

	readExcelFile(String FilePath) {
		try {
			Workbook workbook = WorkbookFactory.create(new File(FilePath));
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				Sheet sheet = workbook.getSheetAt(i);
				for (int j = 1; j < sheet.getLastRowNum(); j++) {
					Row row = sheet.getRow(j);
					System.out.println(row.getCell(4));
					data data = new data();
					data.setName(row.getCell(1).getStringCellValue());
					data.setHid(row.getCell(3).getStringCellValue());
					data.setNewprice("" + row.getCell(4).getNumericCellValue());
					data.setCompy(row.getCell(7).getStringCellValue());
					data.setInprice("" + row.getCell(5).getNumericCellValue());
					infiles.add(data);
				}
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
