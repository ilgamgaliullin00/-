package lab6;

/*import com.yworks.yfiles.geometry.RectD;
import com.yworks.yfiles.graph.*;
import com.yworks.yfiles.graph.labelmodels.InteriorLabelModel;
import com.yworks.yfiles.graph.portlocationmodels.FreeNodePortLocationModel;
import com.yworks.yfiles.view.GraphComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visual {

  public Visual(List<Node> nodeList, List<Edge> edgeList) {
    GraphComponent graphComponent = new GraphComponent();
    IGraph graph = graphComponent.getGraph();


    //состаляем узлы
    INode iNode;
    int newLine = 0;
    int positionY = 0;
    int positionX = 0;
    Map<String, INode> mapINodes = new HashMap<>();
    for (int i = 0; i < nodeList.size(); i++) {
      if (!nodeList.get(i).className.getSimpleName().contains("Person")
              && !nodeList.get(i).className.getSimpleName().contains("Section")) {
        iNode = graph.createNode(new RectD(positionX, positionY, 150, 150));

        //добавление в узел полей

        ILabel jLabel1 = graph.addLabel(iNode, nodeList.get(i).className.getSimpleName(), InteriorLabelModel.NORTH);
        mapINodes.put(nodeList.get(i).className.getSimpleName(), iNode);
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < nodeList.get(i).attributeList.length; j++) {
          stringBuilder.append(nodeList.get(i).attributeList[j].attributeName +
                  " : " + nodeList.get(i).attributeList[j].type + "\n");
        }
        ILabel jLabel2 = graph.addLabel(iNode, stringBuilder.toString());


        positionX += 400;
        newLine++;
        if (newLine == 3) {
          positionX = 0;
          positionY += 400;
        }
      }

    }


    //устанавливаем связи

    for (int i = 0; i < edgeList.size(); i++) {
      String sourceString = edgeList.get(i).source.className.getSimpleName();
      INode sourceNode = null;
      INode targetNode = null;
      //вытаскиваем начальный узел
      for (int j = 0; j < mapINodes.size(); j++) {
        if (mapINodes.containsKey(sourceString)) ;
        sourceNode = mapINodes.get(sourceString);

      }
//      System.out.println(sourceString + "=" + sourceNode.toString());
      String targetSource = edgeList.get(i).target.className.getSimpleName();

      //вытаскиваем конечный узел
      for (int j = 0; j < mapINodes.size(); j++) {
        if (mapINodes.containsKey(targetSource)) ;
        targetNode = mapINodes.get(targetSource);
      }
//      System.out.println(targetSource + "=" + targetNode.toString());


      IPort targetPort = null;
      //распределяем ребра, чтобы не наслаивались
      if (edgeList.get(i).relationType.toString().equals("OneToMany")) {
        targetPort = graph.addPort(targetNode, FreeNodePortLocationModel.NODE_LEFT_ANCHORED);
      }
      if (edgeList.get(i).relationType.toString().equals("ManyToOne")) {
        targetPort = graph.addPort(targetNode, FreeNodePortLocationModel.NODE_BOTTOM_ANCHORED);
      }

      if (edgeList.get(i).relationType.toString().equals("OneToOne")) {
        targetPort = graph.addPort(targetNode, FreeNodePortLocationModel.NODE_TOP_LEFT_ANCHORED);
      }

      IPort sourcePort = graph.addPort(sourceNode);
      //задаем ребро
      IEdge iedge = graph.createEdge(sourcePort, targetPort);
      //задаем тип свзяи на ребре
      ILabel edgeLabel = graph.addLabel(iedge, edgeList.get(i).relationType.toString());


    }


    //само представление
    JFrame frame = new JFrame("Diagram");

    //чтобы узлы были в центре
    graphComponent.fitGraphBounds();

    frame.setSize(1500, 800);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(graphComponent, BorderLayout.CENTER);
    frame.setVisible(true);
  }
}*/

