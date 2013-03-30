package com.cnksi.core.tools.poi;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.cnksi.core.tools.poi.annotation.Description;
import com.cnksi.core.tools.poi.annotation.DescriptionInfo;
import com.cnksi.core.tools.utils.Reflections;
import com.google.common.net.HttpHeaders;

/**
 * 创建Workbook
 * @author dell
 *
 */
@SuppressWarnings("all")
public class PoiExportExcel
{

	private static PoiExportExcel excel;

	private Workbook wb = null;

	private CreationHelper createHelper = null;

	private DataFormat dataFormat;

	private Map<String, CellStyle> cellStypeMap;

	private Font font;

	public static PoiExportExcel getInstance()
	{

		if (excel == null)
		{

			excel = new PoiExportExcel();
		}

		return excel;
	}

	/**
	 * 创建Office 2007 Excel
	 * 
	 */
	public OutputStream createExcel(OutputStream stream, List<DescriptionInfo> descriptionInfoList, List datas, String sheetTitle)
	{

		wb = new HSSFWorkbook();

		createHelper = wb.getCreationHelper();

		dataFormat = createHelper.createDataFormat();

		try
		{

			Sheet sheet = wb.createSheet("sheet1");

			font = createFont();

			sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) descriptionInfoList.size() - 1));

			createTitle(sheet, descriptionInfoList, sheetTitle);

			createHeader(sheet, descriptionInfoList);

			writeData(sheet, datas, descriptionInfoList);

			wb.write(stream);

			sheet = null;

			wb = null;

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return stream;
	}

	/**
	 * 创建Excel的Sheet Title信息，一般是Excel的第一行表头
	 * 
	 * @param sheet
	 *  
	 * @param descriptionInfoList 字段描述信息【表头】
	 * 
	 * @return
	 */
	private void createTitle(Sheet sheet, List<DescriptionInfo> descriptionInfoList, String sheetTitle)
	{

		if (cellStypeMap == null)
		{
			cellStypeMap = new HashMap<String, CellStyle>();
		}

		Row row = sheet.createRow((short) 0);

		row.setHeightInPoints(28);

		Cell cell = row.createCell(0);

		cell.setCellValue(sheetTitle);

		CellStyle style = createCellStyle();

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_CENTER);

		cell.setCellStyle(style);

		row = null;
	}

	/**
	 * 创建Excel的表头
	 * 
	 * @param sheet
	 *  
	 * @param descriptionInfoList 字段描述信息【表头】
	 * 
	 * @return
	 */
	private void createHeader(Sheet sheet, List<DescriptionInfo> descriptionInfoList)
	{

		if (cellStypeMap == null)
		{
			cellStypeMap = new HashMap<String, CellStyle>();
		}

		Row row = sheet.createRow((short) 1);

		row.setHeightInPoints(22);

		for (int i = 0; i < descriptionInfoList.size(); i++)
		{
			DescriptionInfo descinfo = descriptionInfoList.get(i);

			sheet.setColumnWidth(i, descinfo.getSize() * 256);

			Cell cell = row.createCell(i);

			cell.setCellValue(descinfo.getComment());

			CellStyle style = createCellStyle();

			style.setFont(font);

			style.setAlignment(CellStyle.ALIGN_LEFT);

			cell.setCellStyle(style);

			cellStypeMap.put(descinfo.getField(), style);

		}
		row = null;
	}

	/**
	 * 向Sheet中写数据
	 * 
	 * @param sheet	 Sheet
	 * 
	 * @param datas 数据集合
	 * 
	 * @param descriptionInfoList 字段描述信息
	 * 
	 */
	private void writeData(Sheet sheet, List datas, List<DescriptionInfo> descriptionInfoList)
	{

		for (int r = 0; r < datas.size(); r++)
		{
			Object obj = datas.get(r);

			Row row = sheet.createRow((short) (r+2));
			row.setHeightInPoints(22);

			for (int c = 0; c < descriptionInfoList.size(); c++)
			{
				DescriptionInfo descinfo = descriptionInfoList.get(c);

				Cell cell = row.createCell(c);

				Object value = Reflections.getFieldValue(obj, descinfo.getField());

				CellStyle style = cellStypeMap.get(descinfo.getField());

				if (value != null)
				{

					if (value instanceof Date)
					{
						cell.setCellValue((Date) value);

						style.setDataFormat(dataFormat.getFormat("yyyy/m/d h:mm"));

					} else if (value instanceof Double)
					{
						cell.setCellValue((Double) value);

						style.setDataFormat(dataFormat.getFormat("#,##0.00"));

					} else if (value instanceof Boolean)
					{
						cell.setCellValue((Boolean) value);

					} else if (value instanceof Float)
					{
						cell.setCellValue((Float) value);

					} else if (value instanceof Integer)
					{
						cell.setCellValue((Integer) value);
					} else
					{
						cell.setCellValue(value.toString());
					}
				}

				cell.setCellStyle(style);
			}

			row = null;
		}
	}

	private CellStyle createCellStyle()
	{

		CellStyle style = wb.createCellStyle();

		style.setWrapText(true); //自动换行

		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		return style;
	}

	private Font createFont()
	{

		Font font = wb.createFont();

		font.setFontHeightInPoints((short) 10);

		font.setFontName("微软雅黑");

		font.setItalic(false); //斜体

		font.setStrikeout(false); //删除线

		return font;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * 下载Excel
	 * 
	 * @param fileName 下载后的文件名.
	 * @param descriptionInfoList Excel 表头信息集合
	 * @param datas Excel数据集合
	 * @param sheetTitle Excel内容表头
	 */
	public void downloadFile(HttpServletResponse response, String fileName, List<DescriptionInfo> descriptionInfoList, List<?> datas, String sheetTitle)
	{

		try
		{
			response.reset();

			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");

			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");

			response.setContentType("application/x-excel;charset=UTF-8");

			OutputStream outputStream;

			try
			{
				outputStream = new BufferedOutputStream(response.getOutputStream());

				PoiExportExcel.getInstance().createExcel(outputStream, descriptionInfoList, datas, sheetTitle);

				outputStream.flush();

				outputStream.close();

			} catch (IOException e)
			{
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e)
		{

		}
	}

	/**
	 * 解析clazz的各个字段中，标记有@Description注解的字段
	 * 
	 * 用于取得字段的原始信息
	 * 
	 * @param clazz
	 * 
	 * @return List<DescriptionInfo> {@link DescriptionInfo}
	 * 
	 */
	public List<DescriptionInfo> parseField(Class clazz)
	{
		return parseField(clazz, "");
	}

	/**
	 * 解析clazz的各个字段中，标记有@Description注解的字段
	 * 
	 * 用于取得字段的原始信息
	 * 
	 * @param clazz
	 * 
	 * @return List<DescriptionInfo> {@link DescriptionInfo}
	 * 
	 */
	public List<DescriptionInfo> parseField(Class clazz, String exportType)
	{

		List<DescriptionInfo> resultList = new ArrayList<DescriptionInfo>();

		for (Field field : clazz.getDeclaredFields())
		{
			Description description = field.getAnnotation(Description.class);

			if (description != null && description.excel())
			{
				DescriptionInfo fieldDescriptionInfo = null;

				String xlsTitle = field.getName();

				String fieldName = field.getName();

				//如果没有备注信息,则设为Field名称
				if (description.comment().trim().length() > 0)
				{
					xlsTitle = description.comment();
				}

				String etype = description.exportType();

				if (description.field().trim().length() > 0)
				{
					fieldName = description.field();
				}

				if (etype.contains(exportType))
				{
					fieldDescriptionInfo = new DescriptionInfo(fieldName, xlsTitle, description.size(), field.getType(), description.order());

					resultList.add(fieldDescriptionInfo);
				}
			}

		}

		DescriptionInfo.Sort(resultList);

		return resultList;
	}
}
