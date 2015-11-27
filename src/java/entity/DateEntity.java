/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Athinodoros
 */
@Entity
public class DateEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @OneToMany (cascade = CascadeType.ALL)
    private List<Currency> currency  = new  ArrayList<Currency>();

    public List<Currency> getCurrency() {
        return currency;
    }
    public boolean addToTheList(Currency cur) {
       int i1 = currency.size();
        currency.add(cur);
       int i2 = currency.size();
       
       return i1<i2;
    }

    public void setCurrency(ArrayList<Currency> currency) {
        this.currency = currency;
    }

    public DateEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
