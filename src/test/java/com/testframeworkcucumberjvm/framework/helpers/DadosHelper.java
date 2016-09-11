package com.testframeworkcucumberjvm.framework.helpers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DadosHelper {

	public static HashMap<String, String> storeValues = new HashMap<String, String>();

	public static List<HashMap<String, String>> dados(String arquivo,
			String planilhaNome) {
		List<HashMap<String, String>> dados = new ArrayList<>();
		try {
			FileInputStream fs = new FileInputStream(arquivo);
			XSSFWorkbook pastaTrabalho = new XSSFWorkbook(fs);
			XSSFSheet planilha = pastaTrabalho.getSheet(planilhaNome);
			Row cabecalho = planilha.getRow(0);

			for (int i = 1; i < planilha.getPhysicalNumberOfRows(); i++) {
				Row linhaAtual = planilha.getRow(i);
				HashMap<String, String> hash = new HashMap<String, String>();
				for (int j = 0; j < linhaAtual.getPhysicalNumberOfCells(); j++) {
					Cell celulaAtual = linhaAtual.getCell(j);

					switch (celulaAtual.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						// System.out.print(celulaAtual.getStringCellValue() +
						// "\t");
						hash.put(cabecalho.getCell(j).getStringCellValue(),
								celulaAtual.getStringCellValue());
						break;
					}
				}
				dados.add(hash);
			}

			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dados;

	}
}
