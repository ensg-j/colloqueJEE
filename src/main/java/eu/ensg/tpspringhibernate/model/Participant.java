/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.tpspringhibernate.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author formation
 */
@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment",strategy="increment")
    @Column(name = "num_pers")
    private int num_personn;
    @Column(name="nom",nullable = false)
    private String nom;
    @Column(name="prenom",nullable=false)
    private String prenom;
    @Column(name="email",nullable=false)
    private String email;
    @Column(name="birthdate")
    private LocalDate date_naiss;
    @Column(name="organisation")
    private String organisation;
    @Column(name="observations")
    private String observations;
    
    @ManyToOne
    public Evenement evenement;
    
    public Participant(){
        nom = "default";
        prenom = "default";
        email = "mail@mail";
    }

    public Participant(String n, String p, String m, String d) {
        nom = n;
        prenom = p;
        email = m;
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        date_naiss = LocalDate.parse(d, formatter);
    }
    
    public void setPrenom(String p){
        prenom = p;
    }

    public int getNum_personn() {
        return num_personn;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate_naiss() {
        return date_naiss;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getObservations() {
        return observations;
    }

    public void setNum_personn(int num_personn) {
        this.num_personn = num_personn;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate_naiss(LocalDate date_naiss) {
        this.date_naiss = date_naiss;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public void setEvenement(Evenement ev){
        evenement = ev;

    }

    public Evenement getEvenement(Evenement ev){
        return(evenement);

    }
    public Evenement getParticipants() {
        return evenement;
    }
}
