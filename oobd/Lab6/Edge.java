package lab6;

public class Edge {
  Node source;
  Node target;
  RelationType relationType;

  public Edge(Node source, Node target, RelationType relationType) {
    this.source = source;
    this.target = target;
    this.relationType = relationType;
  }

  @Override
  public String toString() {
    return "Edge{" +
            "source=" + source +
            ", target=" + target +
            ", relationType=" + relationType +
            '}';
  }
}
