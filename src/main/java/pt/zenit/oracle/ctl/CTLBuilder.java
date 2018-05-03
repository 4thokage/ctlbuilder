package pt.zenit.oracle.ctl;

import java.io.StringWriter;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.zenit.oracle.ctl.domain.CTLOptions;
import pt.zenit.oracle.ctl.domain.DBColumn;
import pt.zenit.oracle.ctl.domain.DBTable;
import pt.zenit.oracle.ctl.enums.CTLTypesEnum;

/**
 * Classe utilizada para gera��o dos ficheiros CTL
 */
public class CTLBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(CTLBuilder.class);

	private static final VelocityEngine ve;
	private static final int DEFAULT_COL_PAD_FORMAT = 32;

	/* Init Apache Velocity Engine */
	static {
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		ve.init();
	}

	private CTLBuilder() {
		throw new AssertionError();
	}

	public static String generateCTL(DBTable dbTable, Collection<DBColumn> columns, CTLTypesEnum ctlType,
			CTLOptions opts) {

		if (dbTable == null || ctlType == null) {
			LOG.error("invalid input!");
			return "";
		}
		
		if (opts == null) {
			LOG.warn("CTL option provided are null, using default options");
			opts = new CTLOptions.CTLOptionsBuilder().build();
		}

		if (columns == null || columns.isEmpty()) {
			LOG.warn("Table has no columns!");
		} else if (CTLTypesEnum.LOAD == ctlType) {
			columns = prepareColumnPositions(columns);
			columns = formatColumnNames(columns);
		}

		Template t = ve.getTemplate(String.format("/velocityTemplates/CTL_%s.vm", ctlType.toString()));

		VelocityContext context = new VelocityContext();
		context.put("table", dbTable);
		context.put("allColumns", columns);
		context.put("nl", "\n");
		context.put("tab", "\t");
		context.put("maxLength", getMaxLength(columns));
		context.put("opts", opts);

		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		String result = writer.toString();

		LOG.debug("generateCTL of type [{}] for table [{}]: \n {}", ctlType.toString(), dbTable.getName(), result);
		return result;

	}

	private static Collection<DBColumn> formatColumnNames(Collection<DBColumn> columns) {
		for (DBColumn column : columns) {
			String safeName = String.format("\"%s\"", column.getName());
			String formattedName = StringUtils.rightPad(safeName, DEFAULT_COL_PAD_FORMAT);
			column.setName(formattedName);
		}
		return columns;
	}

	public static int getMaxLength(Collection<DBColumn> tables) {
		return tables != null ? tables.stream().mapToInt(DBColumn::getLengthValue).sum() : 0;
	}

	private static Collection<DBColumn> prepareColumnPositions(Collection<DBColumn> columns) {

		int sumColsLength = 0;
		int startPosition = 1;
		int oldColumnEndPosition = 0;
		for (DBColumn column : columns) {
			column.setStartPosition(startPosition);
			sumColsLength += column.getLengthValue();

			column.setEndPosition(oldColumnEndPosition + column.getLengthValue());
			oldColumnEndPosition += column.getLengthValue();

			startPosition = sumColsLength + 1;
		}
		return columns;
	}
}
