package com.findmedicine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.findmedicine.entity.data;

public class createResult {

	private List<data> infiles = new ArrayList<data>();
	private String name = "";

	public void setInfiles(List<data> infiles) {
		this.infiles = infiles;
	}

	public void createExcel() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("查詢結果.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("result");
		HSSFRow row = worksheet.createRow(0);
		row.createCell(0).setCellValue("藥名");
		row.createCell(1).setCellValue("中文");
		row.createCell(2).setCellValue("健保碼");
		row.createCell(3).setCellValue("新價");
		row.createCell(4).setCellValue("進價");
		row.createCell(5).setCellValue("藥廠");

		int i = 1;
		for (data d : infiles) {
			row = worksheet.createRow(i);
			name = getChineseName(d.getHid());
			row.createCell(0).setCellValue(d.getName());
			row.createCell(1).setCellValue(name);
			row.createCell(2).setCellValue(d.getHid());
			row.createCell(3).setCellValue(d.getNewprice());
			row.createCell(4).setCellValue(d.getInprice());
			row.createCell(5).setCellValue(d.getCompy());
			i++;
		}
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public String getChineseName(String hid) {
		String name = "";
		Document doc = null;
		try {
			if (hid != null && (!hid.trim().equals("")))
				doc = Jsoup.connect(
						"http://www.nhi.gov.tw/query/query1_list.aspx?Q1ID="
								+ hid).get();
		} catch (IOException e) {
			getChineseName(hid);
		}
		if (doc != null) {
			try {
				name = doc.getElementById("gvQuery1Data_ctl02_lblNameChinese")
						.text();
			} catch (NullPointerException e) {
				name = "";
			}
			System.out.println(name + "  " + hid);
		} else {
			name = "";
		}

		return name;
	}
}
