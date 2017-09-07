package com.toutoujinrong.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Excel解析类
 * @author qiupeng
 *
 */
public class ExcelParser implements IExcelParser {

	public static void main(String[] args) throws IOException {
		ExcelParser parser = new ExcelParser() ;
		String str = "D:\\test.xlsx";
		InputStream in = new FileInputStream(new File(str));
		List<List<String>> list = parser.parse(in, "Sheet1");
		for (List<String> l1 : list)
		{
			System.out.println("------------");
			for (String l2 : l1)
			{
				System.out.println("\t" + l2);
			}
		}
	}
	

	/* (non-Javadoc)
	 * @see Excel.IExcelParser#parse(java.io.InputStream, java.lang.String)
	 */
	@Override
	public List<List<String>> parse(InputStream is, String sheetName) {
		List<List<String>> excelData = new ArrayList<List<String>>();
		try {
			OPCPackage pkg = OPCPackage.open(is);
			XSSFReader r = new XSSFReader(pkg);
			
			SharedStringsTable sst = r.getSharedStringsTable();
			XMLReader parser = XMLReaderFactory.createXMLReader();
			ContentHandler handler = new SheetHandler(sst, excelData);
			parser.setContentHandler(handler);
			
//			System.out.println(handler.toString());
			XSSFReader.SheetIterator iter = (SheetIterator) r.getSheetsData();
			int index = 0;
			while (iter.hasNext()) {  
			    InputStream stream = iter.next();  
			    String sheetNameTemp = iter.getSheetName();  
			    if (sheetName.equals(sheetNameTemp)) {  
			    	InputSource sheetSource = new InputSource(stream);
			    	parser.parse(sheetSource);
			        stream.close();
			        ++index;
			        break;
			    }  
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("XLSX文件解析失败");
			e.printStackTrace();
		} 
//		finally {
//            if (sheet != null)
//                IOUtils.closeQuietly(sheet);
//            if (is != null)
//				try {
//					is.close();
//				} catch (IOException e) {
//					System.out.println("XLSX流关闭失败");
//					throw new ExcelParseException("XLSX流关闭失败");
//				}
//        }
		return excelData;
	}
	
	
	private static class SheetHandler extends DefaultHandler {
		private SharedStringsTable sst;
		private List<List<String>> data;
		private List<String> currentRow;
		private boolean nextIsString;
		private boolean isNull;//单元格是否为空
		private String lastContents;
		
		private SheetHandler(SharedStringsTable sst, List<List<String>> data) {
			this.sst = sst;
			this.data = data;
		}
		
		@Override
		public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
			if (EXCEL_ROW.equals(name)) {
				currentRow = new ArrayList<String>();
				data.add(currentRow);
			}
			if (EXCEL_CELL.equals(name)) {
				String cellType = attributes.getValue(EXCEL_CELL_TPYE);
				if (cellType != null && EXCEL_CELL_TPYE_STRING.equals(cellType)) {
					nextIsString = true;
					isNull = false;
				} else {
					nextIsString = false;
					isNull = true;
				}
			}
			if (EXCEL_CELL_CONTENT.equals(name)) {
				isNull = false;
			}
			lastContents = "";// 遇到新标签时，清除内容
		}
		
		@Override
		public void endElement(String uri, String localName, String name) throws SAXException {
			if (nextIsString) {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
				nextIsString = false;
			}
			if (EXCEL_CELL_CONTENT.equals(name)) {
				currentRow.add(lastContents);
			} else if (EXCEL_CELL.equals(name) && isNull) {
				currentRow.add("");
				isNull = false;
			}
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			lastContents += new String(ch, start, length);
		}
	}
}