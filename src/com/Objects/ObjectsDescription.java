package com.Objects;

import java.io.File;


public abstract class ObjectsDescription {

        String id;
     //   AnimateSprite sprite;       //Спрайт объекта
        protected int x;         //Коор-ты центра объекта
        protected int y;
        protected int xLeft;        //Коор-ты левого верхнего угла объекта
        protected int yLeft;
        protected int width;        //Размеры объекта
        protected int height;
        protected int angle;        //Угол наклона объекта относительно оси OX

        protected boolean visible;

        public ObjectsDescription (String _id, int _x, int _y, int _width, int _height, int _angle, File soundFile) {
            id = _id;
            x = _x;
            y = _y;
            width = _width;
            height = _height;
            angle = _angle;
            xLeft = x - width / 2;
            yLeft = y - height / 2;
            visible = true;
        }

        public ObjectsDescription(ObjectsDescription obj) {
            id = obj.id;
            x = obj.x;
            y = obj.y;
            width = obj.width;
            height = obj.height;
            angle = obj.angle;
            xLeft = obj.xLeft;
            yLeft = obj.yLeft;
            visible = obj.visible;
        }

        public abstract void update ();

        public boolean isVisible() {
            return visible;
        }

        public void setX (int newX) {
            xLeft += newX - x;
            x = newX;
        }

        public void setY (int newY) {
            yLeft += newY - y;
            y = newY;
        }

        public String getId(){
            return id;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public int getLeftX() {
            return xLeft;
        }
        public int getLeftY() {
            return yLeft;
        }
        public int getWidth() {
            return width;
        }
        public int getHeight() {
            return height;
        }

        public abstract void destruction(int value);

    }
