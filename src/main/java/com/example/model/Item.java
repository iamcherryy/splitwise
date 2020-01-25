package com.example.model;

import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
//@Table(name = "service")
@Table(name = "spendings")
public class Item {
    //private String i_name_of_friend;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "spend_id")
    public int id;

    @Column(name = "spend_name")
    @NotEmpty(message = "*Podaj nazwę wydatku")
    private String i_name;

    @Column(name = "spend_date")
    @NotEmpty(message = "*Podaj date transakcji")
    private String i_date;

    @Column(name = "spend_price")
    @NotNull(message = "*Podaj kwotę")
    private Double i_price;


    @Column(name = "spend_user_1")
    @NotNull(message = "*Podaj adresata")
    private int i_user_1;

    @Column(name = "spend_user_2")
    @NotNull(message = "*Podaj adresata")
    private int i_user_2;

    @Column(name = "paid_debt")
    @NotNull(message = "*Czy zapłacone?")
    private int i_if_paid;
/*
    @OneToOne(mappedBy="user2",cascade=CascadeType.ALL, fetch=FetchType.LAZY)

    @Formula("(select u.name from user u where u.user_id = spend_user_2)")
    private String i_name_of_user_2;

    public String getName() {
        return i_name_of_user_2;
    }

    public void setName(String i_name_of_user_2) {
        this.i_name_of_user_2= i_name_of_user_2;
    }
*/
    public int getId() {
        return id;
    }

    public void setId(int i_id) {
        this.id = i_id;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public Double getI_price() {
        return i_price;
    }

    public void setI_price(Double i_price) {
        this.i_price = i_price;
    }

    public String getI_date() {
        return i_date;
    }

    public void setI_date(String i_date) {
        this.i_date = i_date;
    }

    public int getI_user_1() {
        return i_user_1;
    }

    public void setI_user_1(int i_user_1) {
        this.i_user_1 = i_user_1;
    }

    public int getI_user_2() {
        return i_user_2;
    }

    public void setI_user_2(int i_user_2) {
        this.i_user_2 = i_user_2;
    }

    public int getI_paid() {
        return i_if_paid;
    }

    public void setI_paid(int i_if_paid) {
        this.i_if_paid = i_if_paid;
    }
   // public String getI_name_of_friend() {return i_name_of_friend; }

    //public void setI_name_of_friend(String i_name_of_friend) {this.i_name_of_friend = i_name_of_friend;}
}

 /*   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="service_id")
    public int id;

    @Column(name = "service_name")
    @NotEmpty(message = "*Podaj nazwę usługi")
    private String i_name;

    @Column(name = "service_date")
    @NotEmpty(message = "*Podaj date usługi")
    private String i_date;

    @Column(name="service_case_id")
    @NotNull(message = "*Podaj wartość")
    private int i_case_id;


    @Column(name="service_price")
    @NotNull(message = "*Podaj wartość")
    private BigDecimal i_price;

//    @Column(name="service_inner_price")
//    @NotNull(message = "*Podaj wartość")
//    private BigDecimal inner_price;

    public int getId() {
        return id;
    }

    public void setId(int i_id) {
        this.id = i_id;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public BigDecimal getI_price() {
        return i_price;
    }

    public void setI_price(BigDecimal i_price) {
        this.i_price = i_price;
    }

    public BigDecimal getInner_price() {
        return inner_price;
    }

    public void setInner_price(BigDecimal inner_price) {
        this.inner_price = inner_price;
    }

    public String getI_date() {
        return i_date;
    }

    public void setI_date(String i_date) {
        this.i_date = i_date;
    }

    public int getI_case_id() {
        return i_case_id;
    }

    public void setI_case_id(int i_case_id) {
        this.i_case_id = i_case_id;
    }

  */

