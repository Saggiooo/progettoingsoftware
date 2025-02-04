package com.dashboard.DashboardApp.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private boolean sex;



    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Client{" +
                ", nation='" + nation + '\'' +
                ", sex=" + sex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return sex == client.sex && nation.equals(client.nation);
    }

    @Override
    public int hashCode() {
        return Objects.hash( nation, sex);
    }
}
