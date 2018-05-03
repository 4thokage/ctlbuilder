package pt.zenit.oracle.ctl.domain;

/**
 * Represents an Oracle DB table column.
 * @author jsilvaro
 * @version 2018-04-24
 */
public class DBColumn {

	/**
	 * Precision to use when not specified (the maximum allowed for oracle Number DataType)
	 * https://docs.oracle.com/cd/B28359_01/server.111/b28318/datatype.htm#CNCPT413
	 */
    private static final int DEFAULT_PRECISION = 38;

    /**
     * Holds the name of this column
     */
    private String name;
    
    /**
     * Holds the type (oracle) of this column
     */
    private final String dataType;
    
    /**
     * Holds the length of this column
     */
    private final int dataLength;
    
    /**
     * Holds the numerical precision of this column
     */
    private final int dataPrecision;

    private int startPosition;
    private int endPosition;

    public DBColumn(String name, final String dataType, final int dataLength, final int dataPrecision) {
        this.name = name;
        this.dataType = dataType;
        this.dataLength = dataLength;
        if(dataPrecision != 0) {
            this.dataPrecision = dataPrecision;
        } else {
            this.dataPrecision = DEFAULT_PRECISION;
        }

    }
    

    public boolean isNumeric() {
        return "NUMBER".equalsIgnoreCase(this.dataType);
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public int getLengthValue() {
        return isNumeric() ? this.dataPrecision : this.dataLength;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }


    public int getStartPosition() {
        return startPosition;
    }


    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public String toString() {
        return "DBColumn{" +
                "name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                ", dataLength=" + dataLength +
                ", dataPrecision=" + dataPrecision +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                '}';
    }
}
