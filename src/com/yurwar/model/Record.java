package com.yurwar.model;

import java.util.Objects;

/**
 * Contains fields that describes person.
 * @author Yurii Matora
 */
public class Record {
    private String lastName;
    private String firstName;
    private String patronymic;
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
    }

    public Record() {}

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

    /**
     * @return Table formatted string with class fields.
     */
    @Override
    public String toString() {
        return String.format("%-30s%-30s%-30s%-16s%-20s%-50s",
                lastName, firstName, patronymic, nickname, mobilePhone, email);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
