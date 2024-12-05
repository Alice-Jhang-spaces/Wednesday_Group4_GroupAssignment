package com.pandemic.b;

public enum UserType {

    User("User", 0),
    Patient("Patient", 1),
    Doctor("Doctor", 2),
    Nurse("Nurse", 3),
    Pharmacist("Pharmacist", 4),
    Emsstaff("EMS Staff", 5),
    Volunteer("Volunteer", 6),
    TransportationCompany("TransportationCompany", 7),
    SeniorManagement("Senior Management", 8),
    FDA("FDA", 9),
    WHO("WHO", 10),
    HHS("HHS", 11),
    CDC("CDC", 12),
    Pfizer("Pfizer", 13),
    Merck("Merck", 14);

    private String name;
    private int index;

    private UserType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
