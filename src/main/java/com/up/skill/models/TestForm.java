package com.up.skill.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class TestForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    private static final String MUST_EMAIL = "{email.message}";
    private static final String MUST_MOBILE = "{mobile.message}";
    private static final String MUST_VALID_NAME = "{fullname.message}";

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = TestForm.NOT_BLANK_MESSAGE)
    @Pattern(regexp = "(^$|^.*[a-zA-Z])", message = TestForm.MUST_VALID_NAME)
    private String fullname;

    @NotBlank(message = TestForm.NOT_BLANK_MESSAGE)
    @Email(message = TestForm.MUST_EMAIL)
    @Column(unique = true)
    private String email;

    @NotBlank(message = TestForm.NOT_BLANK_MESSAGE)
    @Pattern(regexp = "(^$|[0-9]{11})", message = TestForm.MUST_MOBILE)
    private String mobile;

    @NotBlank(message = TestForm.NOT_BLANK_MESSAGE)
    private String radio;

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
