/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "BSM_V_GASTENBOEK_JAAR")
@NamedQueries({
    @NamedQuery(name = "BsmVGastenboekJaar.findAll", query = "SELECT b FROM BsmVGastenboekJaar b"),
    @NamedQuery(name = "BsmVGastenboekJaar.findOrderedJaren", query = "SELECT b FROM BsmVGastenboekJaar b order by b.jaar desc")})
public class BsmVGastenboekJaar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "JAAR")
    @Id
    private String jaar;

    public BsmVGastenboekJaar() {
    }

    public String getJaar() {
        return jaar;
    }

    public void setJaar(String jaar) {
        this.jaar = jaar;
    }

}
