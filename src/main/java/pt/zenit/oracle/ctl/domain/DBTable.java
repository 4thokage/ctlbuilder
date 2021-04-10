package pt.zenit.oracle.ctl.domain;

/**
 * Represents an Oracle DB table.
 *
 * @author jsilvaro
 * @version 2018-04-24
 */
public class DBTable {
  private String owner;
  private String name;
  private String status;

  public DBTable(String owner, String name, String status) {
    this.owner = owner;
    this.name = name;
    this.status = status;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
