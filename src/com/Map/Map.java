package com.Map;

//import Main.General.Objects.ObjectsDescription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.Objects.ObjectsDescription;

public class Map extends JTable {

    private int mapSize = 20;
    private int cellSize = 20;
    private HashMap<String, ObjectsDescription> objects;  //Список объектов поля
   // private ArrayList<ObjectsDescription> listDelObject;

    private Graph graph;

    public  Map(){

        objects = new HashMap<>(100);
        this.setModel(new DefaultTableModel(this.mapSize, this.mapSize));
        this.setShowHorizontalLines(true);
        this.setShowVerticalLines(true);
        this.setGridColor(Color.black);
        this.setRowHeight(this.cellSize);
        for (int i = 0; i < this.mapSize; i++){
            this.getColumnModel().getColumn(i).setMinWidth(this.cellSize);
            this.getColumnModel().getColumn(i).setMaxWidth(this.cellSize);
        }

        graph = new Graph(this.mapSize * this.mapSize);
        for (int i = 0; i < this.mapSize; i++)
            for (int j = 0; j < this.mapSize; j++) {
                if (i > 0)
                    graph.addEdge((i - 1) * this.mapSize + j, i * this.mapSize + j, this.cellSize);
                if (i < this.mapSize - 1)
                    graph.addEdge(i* this.mapSize + j, (i + 1)* this.mapSize + j, this.cellSize);
                if (j > 0)
                    graph.addEdge(i * this.mapSize +  j - 1, i * this.mapSize + j, this.cellSize);
                if (j < this.mapSize - 1)
                    graph.addEdge(i * this.mapSize + j, i * this.mapSize + j + 1, this.cellSize);
            }

            Controls ctrl = new Controls();
       //    String str =  graph.toString();
       //    System.out.println(str);
    }

    //Добавление нового объекта на поле
    public void AddEntity(String id, ObjectsDescription ent) {
    objects.put(id, ent);

        //Заполнить клетки объектом
        int firstI = ent.getLeftX() / this.cellSize;
        int endI = firstI + ent.getWidth() / this.cellSize; //(ent.getLeftX() + ent.getWidth()) / scaleOfSquare;
        int firstJ = ent.getLeftY() / this.cellSize;
        int endJ = firstJ + ent.getHeight() / this.cellSize;//(ent.getLeftY() + ent.getHeight()) / scaleOfSquare;

        if (firstI < 0)
            firstI = 0;
        if (firstJ < 0)
            firstJ = 0;
        if (endI >= this.mapSize)
            endI = this.mapSize - 1;
        if (endJ >= this.mapSize)
            endJ = this.mapSize - 1;
        for (int i = firstI; i <= endI; i++)
            for (int j = firstJ; j <= endJ; j++) {
               addObjectInSquare(j, i, ent);
                /*squares[j][i].addObject(ent);
                if (ent.getClass() != Tank.class) {
                    if (j > 0)
                        graph.removeEdge((j - 1) * countSquareInW + i, j * countSquareInW + i);
                    if (j < countSquareInH - 1)
                        graph.removeEdge(j* countSquareInW + i, (j + 1)* countSquareInW + i);
                    if (i > 0)
                        graph.removeEdge(j * countSquareInW +  i - 1, j * countSquareInW + i);
                    if (i < countSquareInW - 1)
                        graph.removeEdge(j * countSquareInW + i, j * countSquareInW + i + 1);
                }*/

            }
    }

    public void addInRemoveList (ObjectsDescription ent) {
 //       listDelEntity.add(ent);
    }

    //Удаление объекта из игрового поля
    public void RemoveEntity(String id, ObjectsDescription ent) {

        //Заполнить клетки объектом
        int firstI = ent.getLeftX() / this.cellSize;
        int endI = firstI + ent.getWidth() / this.cellSize; //(ent.getLeftX() + ent.getWidth()) / scaleOfSquare;
        int firstJ = ent.getLeftY() / this.cellSize;
        int endJ = firstJ + ent.getHeight() / this.cellSize;//(ent.getLeftY() + ent.getHeight()) / scaleOfSquare;

        firstI--;
        firstJ--;
        endI++;
        endJ++;
        if (firstI < 0)
            firstI = 0;
        if (firstJ < 0)
            firstJ = 0;
        if (endI >= this.mapSize)
            endI = this.mapSize - 1;
        if (endJ >= this.mapSize)
            endJ = this.mapSize - 1;

        System.out.println("------------------\n");
        for (int i = firstI; i <= endI; i++)
            for (int j = firstJ; j <= endJ; j++) {
               removeObjectOutSquare(j, i, ent);
                //System.out.println(j + "  " + i);
                /*squares[j][i].addObject(ent);
                if (ent.getClass() != Tank.class) {
                    if (j > 0)
                        graph.removeEdge((j - 1) * countSquareInW + i, j * countSquareInW + i);
                    if (j < countSquareInH - 1)
                        graph.removeEdge(j* countSquareInW + i, (j + 1)* countSquareInW + i);
                    if (i > 0)
                        graph.removeEdge(j * countSquareInW +  i - 1, j * countSquareInW + i);
                    if (i < countSquareInW - 1)
                        graph.removeEdge(j * countSquareInW + i, j * countSquareInW + i + 1);
                }*/

            }

      objects.remove(id, ent);
    }

    public void removeObjectOutSquare(int iY, int jX, ObjectsDescription ent) {
        if (iY < 0 || jX < 0 || iY >= this.mapSize || jX >= this.mapSize)
            return;
        if (ent == null)
            return;
       //squares[iY][jX].removeObject(ent);
      //  if (ent.getClass() != Tank.class && ent.getClass() != Bonus.class && squares[iY][jX].getSize() == 0) {
            if (iY > 0)
                graph.addEdge((iY - 1) * this.mapSize + jX, iY * this.mapSize + jX, this.cellSize);
            if (iY < this.mapSize - 1)
                graph.addEdge(iY* this.mapSize + jX, (iY + 1)* this.mapSize + jX, this.cellSize);
            if (jX > 0)
                graph.addEdge(iY * this.mapSize +  jX - 1, iY * this.mapSize + jX, this.cellSize);
            if (jX < this.mapSize - 1)
                graph.addEdge(iY * this.mapSize + jX, iY * this.mapSize + jX + 1, this.cellSize);
        }
  //  }

    public void addObjectInSquare(int iY, int jX, ObjectsDescription ent) {
        if (iY < 0 || jX < 0 || iY >= this.mapSize || jX >= this.mapSize)
            return;
        if (ent == null)
            return;
       // squares[iY][jX].addObject(ent);
        //System.out.println(ent.getId() + "   " + mainTank);
       // if (ent.getClass() != Tank.class && ent.getClass() != Bonus.class) {
            if (iY > 0)
                graph.removeEdge((iY - 1) * this.mapSize + jX, iY * this.mapSize + jX);
            if (iY < this.mapSize - 1)
                graph.removeEdge(iY* this.mapSize + jX, (iY + 1)* this.mapSize + jX);
            if (jX > 0)
                graph.removeEdge(iY * this.mapSize +  jX - 1, iY * this.mapSize + jX);
            if (jX < this.mapSize - 1)
                graph.removeEdge(iY * this.mapSize + jX, iY * this.mapSize + jX + 1);
        }
    //}
}
