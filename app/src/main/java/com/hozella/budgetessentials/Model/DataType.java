package com.hozella.budgetessentials.Model;

public class DataType {

    private int id;
    private String label;
    private double amount;
    private String note;
    private String occurrence;

    public DataType(int id, String label, double amount, String note, String occurrence) {
        this.id = id;
        this.label = label;
        this.amount = amount;
        this.note = note;
        this.occurrence = occurrence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }
}
