package com.yurwar.model;

import java.util.Objects;

public class Record {
    private String lastName;
    private String firstName;
    private String patronymic;
    private String fullName;
    private String nickname;
    private String mobilePhone;
    private String email;

    public Record(String lastName, String firstName, String patronymic, String nickname, String mobilePhone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.nickname = nickname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.fullName = lastName + " " + firstName.charAt(0) + '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return lastName.equals(record.lastName) &&
                firstName.equals(record.firstName) &&
                patronymic.equals(record.patronymic) &&
                nickname.equals(record.nickname) &&
                mobilePhone.equals(record.mobilePhone) &&
                email.equals(record.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, patronymic, nickname, mobilePhone, email);
    }

    @Override
    public String toString() {
        return String.format("%-30s%-30s%-30s%-35s%-16s%-15s%-50s",
                lastName, firstName, patronymic, fullName, nickname, mobilePhone, email);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.fullName = lastName + " " + firstName.charAt(0) + '.';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.fullName = lastName + " " + firstName.charAt(0) + '.';
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
