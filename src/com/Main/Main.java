package com.Main;
import com.Map.Starter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Это сделал не я");
        try {
            Starter startingPoint = new Starter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
