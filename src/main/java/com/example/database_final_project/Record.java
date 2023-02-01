package com.example.database_final_project;

public class Record {

    public String clientId = "";
    public String clientName = "";
    public String carID = "";
    public String carModel = "";
    public String insuranceId = "";
    public String insuranceType = "";

    public String getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "Record{" +
                "clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", carID='" + carID + '\'' +
                ", carModel='" + carModel + '\'' +
                ", insuranceId='" + insuranceId + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                '}';
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }
}
