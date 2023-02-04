/*Saja*/
package com.example.database_final_project;

abstract public class Insuranse {
    private int ins_id;
    private String start_date;
    private String end_date;
    private int car_id;
    private int client_id;

    public Insuranse() {

    }

    public Insuranse(int ins_id, String start_date, String end_date, int car_id, int client_id) {
        super();
        this.ins_id = ins_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.car_id = car_id;
        this.client_id = client_id;
    }

    public int getIns_id() {
        return ins_id;
    }

    public void setIns_id(int ins_id) {
        this.ins_id = ins_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Insurance [ins_id=" + ins_id + ", start_date=" + start_date + ", end_date=" + end_date + ", car_id="
                + car_id + ", client_id=" + client_id + "]";
    }

    abstract public double calculate_price(int engine_size);


}
