package lab6;

public class Attribute {
  String attributeName;
  String type;

  public Attribute(String attributeName, String type) {
    this.attributeName = attributeName;
    this.type = type;
  }

  @Override
  public String toString() {
    return "Attribute{" +
            "attributeName='" + attributeName + '\'' +
            ", type='" + type + '\'' +
            '}';
  }
}
