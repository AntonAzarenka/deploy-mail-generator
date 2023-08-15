package com.azarenka.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents domain object for properties of application.
 */
public class MailProperty implements Serializable {

    private String header;
    private String toEmail;
    private String ccEmail;
    private String subject;
    private String releaseNotesLink;
    private String monthRelease;
    private String yearRelease;

    public MailProperty(String header, String toEmail, String ccEmail, String subject, String releaseNotesLink, String monthRelease, String yearRelease) {
        this.header = header;
        this.toEmail = toEmail;
        this.ccEmail = ccEmail;
        this.subject = subject;
        this.releaseNotesLink = releaseNotesLink;
        this.monthRelease = monthRelease;
        this.yearRelease = yearRelease;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getCcEmail() {
        return ccEmail;
    }

    public void setCcEmail(String ccEmail) {
        this.ccEmail = ccEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReleaseNotesLink() {
        return releaseNotesLink;
    }

    public void setReleaseNotesLink(String releaseNotesLink) {
        this.releaseNotesLink = releaseNotesLink;
    }

    public String getMonthRelease() {
        return monthRelease;
    }

    public void setMonthRelease(String monthRelease) {
        this.monthRelease = monthRelease;
    }

    public String getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(String yearRelease) {
        this.yearRelease = yearRelease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailProperty that = (MailProperty) o;
        return Objects.equals(header, that.header) && Objects.equals(toEmail, that.toEmail) && Objects.equals(ccEmail, that.ccEmail) && Objects.equals(subject, that.subject) && Objects.equals(releaseNotesLink, that.releaseNotesLink) && Objects.equals(monthRelease, that.monthRelease) && Objects.equals(yearRelease, that.yearRelease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, toEmail, ccEmail, subject, releaseNotesLink, monthRelease, yearRelease);
    }

    @Override
    public String toString() {
        return "MailProperty{" +
                "header='" + header + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", ccEmail='" + ccEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", releaseNotesLink='" + releaseNotesLink + '\'' +
                ", monthRelease='" + monthRelease + '\'' +
                ", yearRelease='" + yearRelease + '\'' +
                '}';
    }
}
