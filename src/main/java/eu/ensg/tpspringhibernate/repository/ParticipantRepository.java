/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.tpspringhibernate.repository;

import eu.ensg.tpspringhibernate.model.Evenement;
import eu.ensg.tpspringhibernate.model.Participant;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author formation
 */
public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
    List<Participant> findByEvenement(Evenement ev);
    
}
