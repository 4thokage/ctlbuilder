package pt.zenit.oracle.ctl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import pt.zenit.oracle.ctl.domain.DBColumn;
import pt.zenit.oracle.ctl.domain.DBTable;
import pt.zenit.oracle.ctl.enums.CTLTypesEnum;

public class CTLBuilderTest {
	
	
	DBTable mockTable;
	Collection<DBColumn> mockCols = new ArrayList<>();
	
	public CTLBuilderTest() {
		mockTable = new DBTable("TEST", "TEST", "VALID");
		mockCols.add(new DBColumn("ID","NUMBER",0,11));
		mockCols.add(new DBColumn("DESCRIPTION","VARCHAR2",255,0));
	}

	@Test
	public void emptyDataShouldReturnEmptyString() {
		String result = CTLBuilder.generateCTL(null, null, null, null);
		assertEquals("", result);
	}
	
	@Test
	public void extractEmptyTestTableShouldEndWithSemicolon() {
		String result = CTLBuilder.generateCTL(mockTable, null, CTLTypesEnum.EXTRACT, null);
		assertTrue(result.endsWith(";"));
	}
	
	@Test
	public void extractTestTableWithOneColumnShouldNotContainConcat() {
		List<DBColumn> singleMockCol = new ArrayList<>(mockCols).subList(0, 1);
		String result = CTLBuilder.generateCTL(mockTable, singleMockCol, CTLTypesEnum.EXTRACT, null);
		assertFalse(result.contains("||"));
	}
	
	@Test
	public void extractTestTableWithMockColsShouldContainCorrectSQLStatement() {
		String result = CTLBuilder.generateCTL(mockTable, mockCols, CTLTypesEnum.EXTRACT, null);
		assertTrue(result.contains("NVL(LPAD(ID,11,'0'), LPAD('0',11,'0'))"));
		assertTrue(result.contains("NVL(RPAD(DESCRIPTION,255,' '), RPAD(' ',255,' '))"));
	}
	
	@Test
	public void mockColsMaxLengthShouldReturn266() {
		int result = CTLBuilder.getMaxLength(mockCols);
		assertEquals(266,result);
	}
}
