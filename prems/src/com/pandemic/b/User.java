package com.pandemic.b;

public class User {
    private int id;
    private String name;
    private String password;
    private String role;
    private String orginization;
    private String enterprise;
    private String network;

    // Default constructor
    public User() {
    }

    // Constructor with all fields
    public User(int id, String name, String password, String role, String orginization, String enterprise, String network) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.orginization = orginization;
        this.enterprise = enterprise;
        this.network = network;
    }

    // Constructor without ID (useful for certain operations)
    public User(String name, String password, String role, String orginization, String enterprise, String network) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.orginization = orginization;
        this.enterprise = enterprise;
        this.network = network;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrginization() {
        return orginization;
    }

    public void setOrginization(String orginization) {
        this.orginization = orginization;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", orginization='" + orginization + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", network='" + network + '\'' +
                '}';
    }
}
