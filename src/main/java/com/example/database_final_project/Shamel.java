/*Saja*/
package com.example.database_final_project;


public class Shamel extends Complusary {

    private int engine_size;
    private int id;

    public Shamel() {

    }

    public Shamel(int engine_size, int id) {
        super(engine_size, id);
        // TODO Auto-generated constructor stub
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

    public double calculate_price(int engine_size, int price_engine, int carprice) {
        double c = 1.75 * carprice;
        double en = engine_size * price_engine;
        double cost = en + c;
        if (c < 1000)
            en += 1000;
        else
            c += 1000;
        return cost;

    }

    @Override
    public String toString() {
        return "Shamel [engine_size=" + engine_size + ", id=" + id + "]";
    }

}
