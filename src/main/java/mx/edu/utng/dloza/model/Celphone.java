package mx.edu.utng.dloza.model;

/**
 * Created by Vianey on 11/04/2018.
 */

public class Celphone {
    private long id;
    private String mark;
    private String model;
    private String color;
    private String memory;
    private String cost;
    private String company;


    public Celphone() {
    }

    public Celphone(String mark, String model, String color, String memory, String cost, String company) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.memory = memory;
        this.cost = cost;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
