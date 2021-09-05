package com.yelzhan.tgbot;



import java.sql.Timestamp;

public class Currency {

    private Long id;

    private String name;

    private double kzt_value;

    private Timestamp date;

    private double changes;

    public Currency() {

    }

    public Currency(Long id, String name, double kzt_value, Timestamp date, double changes) {
        this.id = id;
        this.name = name;
        this.kzt_value = kzt_value;
        this.date = date;
        this.changes = changes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKzt_value() {
        return kzt_value;
    }

    public void setKzt_value(double kzt_value) {
        this.kzt_value = kzt_value;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getChanges() {
        return changes;
    }

    public void setChanges(double changes) {
        this.changes = changes;
    }
}