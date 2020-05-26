public class Attribute {
    String attributeName;
    String type;
    private String rightSide;

    public Attribute(String attributeName,String type, String rightSide) {
        this.attributeName = attributeName;
        this.type = type;
        this.rightSide = rightSide;
    }

    public Attribute(String attributeName, String type) {
        this.attributeName = attributeName;
        this.type = type;

    }



    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRightSide() {
        return rightSide;
    }

    public void setRightSide(String rightSide) {
        this.rightSide = rightSide;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeName='" + attributeName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}