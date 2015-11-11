package org.zerock.domain;

/**
 * @author LeeSoohoon
 */
public class SampleVO {

    private Integer mno;
    private String firstName;
    private String lastName;

    public Integer getMno() {
        return mno;
    }

    public void setMno(final Integer mno) {
        this.mno = mno;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "SampleVO{" +
                "mno=" + mno +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
