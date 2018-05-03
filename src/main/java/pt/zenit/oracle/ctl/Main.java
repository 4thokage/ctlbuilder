package pt.zenit.oracle.ctl;

import java.util.ArrayList;
import java.util.Collection;

import pt.zenit.oracle.ctl.domain.CTLOptions;
import pt.zenit.oracle.ctl.domain.DBColumn;
import pt.zenit.oracle.ctl.domain.DBTable;
import pt.zenit.oracle.ctl.enums.CTLTypesEnum;

public class Main {

	public static void main(String[] args) {
		String ctl = CTLBuilder.generateCTL(mockTable(), mockCols(), CTLTypesEnum.LOAD, mockOpts());
		System.out.println(ctl);

	}

	private static DBTable mockTable() {
		return new DBTable("ZE", "TEST_PLAIN", "OK");
	}

	private static CTLOptions mockOpts() {
		return new CTLOptions.CTLOptionsBuilder("append", "(sss)", "CHARSET 1337").build();
	}

	private static Collection<DBColumn> mockCols() {
		Collection<DBColumn> mockCols = new ArrayList<>();
		DBColumn col1 = new DBColumn("ID", "NUMBER", 0, 0);
		DBColumn col2 = new DBColumn("DESCRIPCION", "VARCHAR2", 255, 0);
		mockCols.add(col1);
		mockCols.add(col2);

		return mockCols;
	}

}
