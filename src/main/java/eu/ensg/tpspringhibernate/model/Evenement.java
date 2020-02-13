/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.tpspringhibernate.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author formation
 */
@Entity
@Table(name = "evenement")
public class Evenement {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment",strategy="increment")
    @Column(name = "id")
    private int id;
    @Column(name="intitule",nullable = false)
    private String intitule;
    @Column(name="theme",nullable=false)
    private String theme;
    @Column(name="date_debut")
    private LocalDate date_debut;
    @Column(name="length")
    private int duree;
    @Column(name="nb_part_max")
    private int max;
    @Column(name="description")
    private String descr;;
    @Column (name="type_event")
    private String type;
    
    @OneToMany
    private List<Participant> participants = new ArrayList<>();
    
    public Evenement(String name,String th){
        intitule = name;
        theme = th;
    }
    
    public void addParticipant(Participant participant){
        participants.add(participant);
        participant.setEvenement(this);

    }
    
    public Evenement(){
        this.intitule = "nom0";
        this.theme = "theme0";
    }

    public Evenement(String name) {
        this.intitule = name;
        this.theme="notheme";
    }
    


    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getTheme() {
        return theme;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public int getDuree() {
        return duree;
    }

    public int getMax() {
        return max;
    }

    public String getDescr() {
        return descr;
    }

    public String getType() {
        return type;
    }
    
     

    public void setId(int id) {
        this.id = id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
