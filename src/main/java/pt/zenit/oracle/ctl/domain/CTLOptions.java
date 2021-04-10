package pt.zenit.oracle.ctl.domain;

import pt.zenit.oracle.ctl.enums.PadTypesEnum;

import java.io.Serializable;

/** Guarda as prefenrecias do user que ser�o utilizadas na gera��o dos ficheiros de ontrole (CTL) */
public class CTLOptions implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_LOADER_TYPE = "append";
  private static final String DEFAULT_LOADER_HEADER = "OPTIONS (BINDSIZE=512000, ROWS=10000)";
  private static final String DEFAULT_LOADER_CHARSET = "CHARACTERSET WE8ISO8859P1";

  private static final String DEFAULT_PAD_CHAR_NUMERIC = "'0'";
  private static final String DEFAULT_PAD_CHAR_STRING = "' '";

  private String ctlLoaderType;
  private String ctlLoaderHeader;
  private String ctlLoaderCharSet;

  private PadTypesEnum padTypeNumeric;
  private String padCharNumeric;

  private PadTypesEnum padTypeString;
  private String padCharString;

  private CTLOptions(CTLOptionsBuilder builder) {
    this.ctlLoaderType = builder.ctlLoaderType;
    this.ctlLoaderHeader = builder.ctlLoaderHeader;
    this.ctlLoaderCharSet = builder.ctlLoaderCharSet;

    this.padTypeNumeric = builder.padTypeNumeric;
    this.padCharNumeric = builder.padCharNumeric;

    this.padTypeString = builder.padTypeString;
    this.padCharString = builder.padCharString;
  }

  public String getCtlLoaderType() {
    return ctlLoaderType;
  }

  public String getCtlLoaderHeader() {
    return ctlLoaderHeader;
  }

  public String getCtlLoaderCharSet() {
    return ctlLoaderCharSet;
  }

  public PadTypesEnum getPadTypeNumeric() {
    return padTypeNumeric;
  }

  public String getPadCharNumeric() {
    return padCharNumeric;
  }

  public PadTypesEnum getPadTypeString() {
    return padTypeString;
  }

  public String getPadCharString() {
    return padCharString;
  }

  public static class CTLOptionsBuilder {

    private final String ctlLoaderType;
    private final String ctlLoaderHeader;
    private final String ctlLoaderCharSet;

    private PadTypesEnum padTypeNumeric = PadTypesEnum.LPAD;
    private String padCharNumeric = DEFAULT_PAD_CHAR_NUMERIC;

    private PadTypesEnum padTypeString = PadTypesEnum.RPAD;
    private String padCharString = DEFAULT_PAD_CHAR_STRING;

    public CTLOptionsBuilder() {
      this.ctlLoaderType = DEFAULT_LOADER_TYPE;
      this.ctlLoaderHeader = DEFAULT_LOADER_HEADER;
      this.ctlLoaderCharSet = DEFAULT_LOADER_CHARSET;
    }

    public CTLOptionsBuilder(
        String ctlLoaderType, String ctlLoaderHeader, String ctlLoaderCharSet) {
      this.ctlLoaderType = ctlLoaderType;
      this.ctlLoaderHeader = ctlLoaderHeader;
      this.ctlLoaderCharSet = ctlLoaderCharSet;
    }

    public CTLOptions build() {
      return new CTLOptions(this);
    }
  }
}
