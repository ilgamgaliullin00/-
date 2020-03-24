package lab6;

import java.util.Arrays;

public class Node {
  Class className;
  Attribute[] attributeList;


  public Node(Class className, Attribute[] attributeList) {
    this.className = className;
    this.attributeList = attributeList;
  }

  @Override
  public String toString() {
    return "Node{" +
            "className=" + className +
            ", attributeList=" + Arrays.toString(attributeList) +
            '}';
  }
}
