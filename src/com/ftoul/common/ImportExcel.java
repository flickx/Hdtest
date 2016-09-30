package com.ftoul.common;


import jxl.*;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ftoul.common.Common;

public class ImportExcel {
	public ImportExcel() {
	}

	/**
	 * 读取EXCEL表
	 * @param filepath
	 * @return 二维表list
	 */
	public static List<String[]> readExcel(String filepath) {
		InputStream is = null;
		Workbook rwb = null;
		try {
			List<String[]> list = new ArrayList<String[]>();
			is = new FileInputStream(filepath);
			rwb = Workbook.getWorkbook(is);
			// sheet st = rwb.getsheet("0")这里有两种方法获取sheet表,1为名字，2为下标，从0开始
			Sheet st = rwb.getSheet(0);
			for (int i = 0; i< st.getRows();i++){
				String [] exlDate = new String[st.getColumns()];
				for(int j = 0; j < st.getColumns();j++){
					Cell c00 = st.getCell(j, i);
					// 通用的获取cell值的方式,返回字符串
					String strc00 = c00.getContents();
					// 获得cell具体类型值的方式
					if (c00.getType() == CellType.LABEL) {
						LabelCell labelc00 = (LabelCell) c00;
						strc00 = labelc00.getString();
					}
					// 输出
//					System.out.println("i"+i+",j"+j+"="+strc00);
					exlDate[j]= strc00;
				}
				int nullDate = 0;
				for(int k = 0; k < exlDate.length ;k++){
					if(!("".equals(exlDate[k]))){
						nullDate ++;
					}
				}
				if(nullDate != 0){
					list.add(exlDate);
				}
			}
			// 关闭
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(rwb != null){
				rwb.close();
			}
		}
		return null;
	}

	
	/**
	 * 读取EXCEL表
	 * @param filepath
	 * @return 二维表list
	 */
	public static List<String[]> readExcel(FileInputStream inputStream) {
		InputStream is = null;
		Workbook rwb = null;
		try {
			List<String[]> list = new ArrayList<String[]>();
			is = inputStream;
			rwb = Workbook.getWorkbook(is);
			// sheet st = rwb.getsheet("0")这里有两种方法获取sheet表,1为名字，2为下标，从0开始
			Sheet st = rwb.getSheet(0);
			for (int i = 0; i< st.getRows();i++){
				String [] exlDate = new String[st.getColumns()];
				for(int j = 0; j < st.getColumns();j++){
					Cell c00 = st.getCell(j, i);
					// 通用的获取cell值的方式,返回字符串
					String strc00 = c00.getContents();
					// 获得cell具体类型值的方式
					if (c00.getType() == CellType.LABEL) {
						LabelCell labelc00 = (LabelCell) c00;
						strc00 = labelc00.getString();
					}
					// 输出
//					System.out.println("i"+i+",j"+j+"="+strc00);
					exlDate[j]= strc00;
				}
				int nullDate = 0;
				for(int k = 0; k < exlDate.length ;k++){
					if(!("".equals(exlDate[k]))){
						nullDate ++;
					}
				}
				if(nullDate != 0){
					list.add(exlDate);
				}
			}
			// 关闭
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(rwb != null){
				rwb.close();
			}
		}
		return null;
	}
	/**
	 * 读取EXCEL表
	 * @param filepath
	 * @return 二维表list
	 */
	public static List<String[]> newReadExcel(FileInputStream inputStream) {
		HSSFWorkbook rwb = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			rwb = new HSSFWorkbook(inputStream);
			// sheet st = rwb.getsheet("0")这里有两种方法获取sheet表,1为名字，2为下标，从0开始
			HSSFSheet st = rwb.getSheetAt(0);
			for (int i = 0; i<= st.getLastRowNum();i++){
				HSSFRow row = st.getRow(i);
				String[] exlDate = new String[row.getLastCellNum()];
				if (null != row) {  
                   for (short k = 0; k < row.getLastCellNum(); k++) {  
                	   HSSFCell cell = row.getCell(k);  
						// 通用的获取cell值的方式,返回字符串
                	   if(cell!=null){
							String strc00 = cell.getStringCellValue();
							// 获得cell具体类型值的方式
							exlDate[k]= strc00;
                	   }
                   }
				}
				int nullDate = 0;
				for(int k = 0; k < exlDate.length ;k++){
					if(!("".equals(exlDate[k]))){
						nullDate ++;
					}
				}
				if(nullDate != 0){
					list.add(exlDate);
				}
			}
			// 关闭
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
	
	/**
	 * 读取Office 2007 excel
	 * */
	private static List<List<Object>> read2007Excel(File file)
			throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
															// 字符
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:// 字符串
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:// 数字
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle()
							.getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell
								.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:// Boolean
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_FORMULA: // 公式
					value = cell.getCellFormula();
					break;
				case XSSFCell.CELL_TYPE_BLANK:// 空值
					value = "";
					break;
				case HSSFCell.CELL_TYPE_ERROR: // 故障
					value = "非法字符";
					break;
				default:
					value = cell.toString();
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}
	
	
	
	public static void main(String[] args) {
		String url = "G:\\项目基本信息.xls";
		String extension = url.lastIndexOf(".") == -1 ? "" : url
				.substring(url.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			List<String[]> list = readExcel(url);
			//System.out.println("size="+list.size());
			for(int i=0; i<list.size();i++){
				for(int j=0;j<list.get(i).length;j++){
					System.out.print(list.get(i)[j]+"\t\t");
				}
				System.out.println();
			}
		} else if ("xlsx".equals(extension)) {
			File file = new File(url);
			try {
				List list = read2007Excel(file);
				for (int i = 0; i < list.size(); i++) {
					List one = (List) list.get(i);
					for (int j = 0; j < one.size(); j++) {
						System.out.print(i+"行"+j+"列："+one.get(j) + "\t\t");
					}
					System.out.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("不支持的文件类型");
		}
	}
}
