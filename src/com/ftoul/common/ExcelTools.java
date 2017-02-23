package com.ftoul.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ftoul.common.Common;

@Component
public class ExcelTools {
	/**
	 * 生成excel文件
	 * @param tableName 
	 * @param title 表头列名称
	 * @param list 数据（每条数据代表一个行数据）
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static InputStream getDownloadInputStream(String tableName,
			String[] title, List<Object> list) {

		InputStream is = null;
		try {
			if (list.size() > 0) {
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("sheet1");
				sheet.setDefaultColumnWidth((short) (12));
				sheet.setColumnWidth((short) 0, (short) (7000));
				HSSFRow row = sheet.createRow(0);
				row.setHeightInPoints((short) (15.625 * 1.5));
				sheet.addMergedRegion(new Region(0, (short) (0), 0,
						(short) (((Object[]) list.get(0)).length - 1)));
				HSSFCellStyle cellStyle = wb.createCellStyle();
				HSSFFont font = wb.createFont();
				// 设置字体大小
				font.setFontHeightInPoints((short) 16);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				cellStyle.setFont(font);
				// 水平对齐
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellStyle.setWrapText(true);
				HSSFCellStyle cellStyle2 = wb.createCellStyle();
				cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellStyle2.setWrapText(true);
				HSSFCellStyle cellStyle3 = wb.createCellStyle();
				cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cellStyle3.setWrapText(true);
				HSSFFont font2 = wb.createFont();
				font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				cellStyle3.setFont(font2);
				cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);
				HSSFCellStyle cellStyle4 = wb.createCellStyle();
				cellStyle4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				HSSFFont font3 = wb.createFont();
				font3.setFontName("楷体");
				cellStyle4.setFont(font3);
				cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
				cellStyle4.setWrapText(true);
				// 表头
				HSSFCell cell = row.createCell((short) 0);
//				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(tableName);
				cell.setCellStyle(cellStyle);
				row = sheet.createRow(1);
				row.setHeightInPoints((short) (15.625 * 1.2));
				for (int i = 0; i < title.length; i++) {
					cell = row.createCell((short) i);
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(title[i].toString());
					cell.setCellStyle(cellStyle3);
				}
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					row = sheet.createRow(i + 2);
					// if("企业分类查询明细表".equals(tableName)||
					// "人员查询明细表".equals(tableName)||
					// "企业综合考评查询明细表".equals(tableName)){
					// row.setHeightInPoints((short) (15.625*2));
					// }else if("业务查询统计表".equals(tableName)){
					// }else{
					// row.setHeightInPoints((short) (15.625*1.2));
					// }
					for (int j = 0; j < obj.length; j++) {
						cell = row.createCell((short) j);
//						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						/**
						 * 空字符转换
						 */
						cell.setCellValue(Common.toNotNullString(obj[j]));
						cell.setCellStyle(cellStyle2);
					}
				}
				// sheet.addMergedRegion(new Region(list.size()+2, (short)
				// (((Object[])list.get(0)).length-3), list.size()+2,
				// (short) (((Object[])list.get(0)).length-1)));
				// row = sheet.createRow(list.size()+2);
				// row.setHeightInPoints((short) (15.625*1.5));
				// cell = row.createCell((short)
				// (((Object[])list.get(0)).length-3));
				// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// cell.setCellStyle(cellStyle4);
				// cell.setCellValue("导出日期: "+Tools.dateFormatToString(new
				// Date(), "yyyy-MM-dd HH:mm:ss"));
				ByteArrayOutputStream os = new ByteArrayOutputStream();

				try {
					wb.write(os);
				} catch (IOException e) {
					e.printStackTrace();
				}

				byte[] content = os.toByteArray();
				is = new ByteArrayInputStream(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;

	}
	
	public List<String> readXlsx(MultipartFile file) {
		List<String> list = new ArrayList<String>();
		InputStream input = null;
		XSSFWorkbook wb = null;
		try {
			input = file.getInputStream();
			wb = new XSSFWorkbook(input);
			XSSFSheet sheet = wb.getSheetAt(0);
			int totalRows = sheet.getLastRowNum();
			for (int i = 1; i <= totalRows; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {
					XSSFCell cell = row.getCell(0);
					list.add(cell.getStringCellValue());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
