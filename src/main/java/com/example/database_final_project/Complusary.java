/*Saja*/
package com.example.database_final_project;

public class Complusary extends Insuranse {
    private int engine_size;
    private int id;


    public Complusary() {

    }

    public Complusary(int engine_size, int id) {


        this.engine_size = engine_size;
        this.id = id;
    }


    public int getEngine_size() {
        return engine_size;
    }

    public void setEngine_size(int engine_size) {
        this.engine_size = engine_size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double calculate_price(int engine_size) {
        double price = 0.0;
        if (engine_size <= 1000) {
            return price = 935;
        } else if (engine_size <= 1500 && engine_size > 1000)
            return price = 1035;
        else if (engine_size <= 2000 && engine_size > 1500)
            return price = 1350;
        else if (engine_size > 2000)
            return price = 1690;

        return price;
    }


    @Override
    public String toString() {
        return "Complusary [engine_size=" + engine_size + ", id=" + id + "]";
    }


}
