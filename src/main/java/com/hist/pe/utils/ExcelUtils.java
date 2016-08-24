package com.hist.pe.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hist.pe.entity.Degree;
import com.hist.pe.entity.Department;
import com.hist.pe.entity.FillQuestion;
import com.hist.pe.entity.JudgeQuestion;
import com.hist.pe.entity.QuestionsBank;
import com.hist.pe.entity.SelectQuestion;
import com.hist.pe.entity.Type;


//操作excel 文档
public class ExcelUtils {
	
	public static List<SelectQuestion> questions=new ArrayList<SelectQuestion>();
	public static List<JudgeQuestion> judgeQuestions=new ArrayList<JudgeQuestion>();
	public static List<FillQuestion> fillQuestions=new ArrayList<FillQuestion>();
	//读 Excel，导入excel文档，并解析该文档

	public static void readExecl(File localFile, Department dp, QuestionsBank qb, Degree dg, int i) throws IOException {
		FileInputStream inp=new FileInputStream(localFile);
		Workbook wb=null;
		if(localFile.getAbsolutePath().endsWith("xls")){
			wb=new HSSFWorkbook(inp);
		}else{
			wb=new XSSFWorkbook(inp);
		}
		
		
		for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
			Sheet  sheet=wb.getSheetAt(numSheet);
			if (sheet==null) {
				continue;
			}
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row xssfRow = sheet.getRow(rowNum);
                switch (i) {
				case 0:
					if (xssfRow != null) {
	                    String title = xssfRow.getCell(0).toString();
	                    String selectA = xssfRow.getCell(1).toString();
	                    String selectB = xssfRow.getCell(2).toString();
	                    String selectC = xssfRow.getCell(3).toString();
	                    String selectD=xssfRow.getCell(4).toString();
	                    String stand_anwer=xssfRow.getCell(5).toString();
	                    String score=xssfRow.getCell(6).toString();
	                    String issue=selectA+"曱"+selectB+"曱"+selectC+"曱"+selectD;
	                    Type type=new Type();
	                    type.setId(1l);
	                    SelectQuestion selectQuestion=new SelectQuestion(title, issue, selectA, selectB, selectC, selectD, stand_anwer, Double.parseDouble(score), qb, dg, dp,type);
	                    questions.add(selectQuestion);
					}
					break;
				//多选题
				case 1:
					if (xssfRow != null) {
						String title = xssfRow.getCell(0).toString();
	                    String selectA = xssfRow.getCell(1).toString();
	                    String selectB = xssfRow.getCell(2).toString();
	                    String selectC = xssfRow.getCell(3).toString();
	                    String selectD=xssfRow.getCell(4).toString();
	                    String stand_anwer=xssfRow.getCell(5).toString();
	                    String score=xssfRow.getCell(6).toString();
	                    String issue=selectA+"曱"+selectB+"曱"+selectC+"曱"+selectD;
	                    String[] anwer=stand_anwer.split(",");
	                    StringBuffer sf=new StringBuffer();
	                    for (int j = 0; j < anwer.length; j++) {
							sf=sf.append(anwer[j]+"曱");
						}
	                    Type type=new Type();
	                    type.setId(4l);
	                    stand_anwer=sf.toString();
	                    SelectQuestion selectQuestion=new SelectQuestion(title, issue, selectA, selectB, selectC, selectD, stand_anwer, Double.parseDouble(score), qb, dg, dp,type);
	                    questions.add(selectQuestion);
					}
					break;
					//判断题
				case 2:
					if (xssfRow != null) {
					 String title= xssfRow.getCell(0).toString();
					 String stand_anwer= xssfRow.getCell(1).toString();
					 String score =xssfRow.getCell(2).toString();
					 Type type=new Type();
	                    type.setId(2l);
					 JudgeQuestion jQuestion=new JudgeQuestion(title, stand_anwer, qb, type, dg,  dp);
					 judgeQuestions.add(jQuestion);
					}
					break;
					//填空题
				case 3:
					if (xssfRow != null) {
						 String title= xssfRow.getCell(0).toString();	
						 String stand_anwer= xssfRow.getCell(1).toString();
						 String score=xssfRow.getCell(2).toString();
						 Double score1=Double.valueOf(score);
						 Type type=new Type();
	                    type.setId(3l);
	                    FillQuestion fQuestion=new FillQuestion(title, stand_anwer, qb, dp, type, dg, score1);
	                    fillQuestions.add(fQuestion);
					}
					break;
				}
                
            }
			
		}
		System.out.println(">>>"+questions);
		System.out.println(">>>"+judgeQuestions);
		System.out.println(">>>"+fillQuestions);
	}
	
}
