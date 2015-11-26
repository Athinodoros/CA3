/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Athinodoros
 */
@Entity
@Table(name="CURRENCY")
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    String code;
    String desc;
    double rate;

    public Currency() {
    }

    public Currency(String code, String desc, int rate) {
        this.code = code;
        this.desc = desc;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    
}
