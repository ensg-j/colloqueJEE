/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensg.tpspringhibernate.controller;

import eu.ensg.tpspringhibernate.exception.ResourceNotFoundException;
import eu.ensg.tpspringhibernate.model.Evenement;
import eu.ensg.tpspringhibernate.model.Participant;
import eu.ensg.tpspringhibernate.repository.EvenementRepository;
import eu.ensg.tpspringhibernate.repository.ParticipantRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class GlobalController {

    @Autowired
    private ParticipantRepository participantsRepository;
    @Autowired
    private EvenementRepository eventRepository;
    
    @GetMapping(path = "/participants/all")
    public String getAllParticipants(Model model) {

        model.addAttribute("participants", participantsRepository.findAll());
        model.addAttribute("allEvents", eventRepository.findAll());
        return "allParticipants";
    }
    
    @GetMapping("/participants/addParticipant")
    public String showAddPersonPage(Model model) {
        Participant participant = new Participant();
        model.addAttribute("personForm", participant);
        model.addAttribute("eventForm", new Evenement());
        model.addAttribute("evenements", eventRepository.findAll());
        return "newParticipants";
    }
    
    @PostMapping("/participants/addParticipant")
    public String savePerson(Model model, //
            @ModelAttribute("personForm") Participant participant,
            @ModelAttribute("eventForm") Evenement event) {
 
        String firstName = participant.getNom();
        String lastName = participant.getPrenom();
        Evenement eventToAttend = eventRepository.findById(event.getId()).get();
        participant.setEvenement(eventToAttend);
 
        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            participantsRepository.save(participant);
 
            return "redirect:/participants/all";
        }
 
        model.addAttribute("errorMessage", "Erreur");
        return "newParticipants";
    }
    
    @GetMapping("/participants/delete/{id}")
    public String removePerson(@PathVariable int id){
        Participant part = participantsRepository.findById(id).get();
        participantsRepository.delete(part);
        return "redirect:/participants/all";
    }
    
    @GetMapping("/participants/updateForm/{id}")
    public String showUpdatePersonPage(Model model,@PathVariable int id) {
        Participant participant = participantsRepository.findById(id).get();
        model.addAttribute("personForm", participant); 
        return "updateParticipant";
    }
    
    @PostMapping("/participants/update/{id}")
    public String updatePersonn(Model model, //
            @ModelAttribute("personForm") Participant participant,
            @PathVariable int id){
        Participant part = participantsRepository.findById(id).get();
        part.setNom(participant.getNom());
        part.setPrenom(participant.getPrenom());
        part.setEmail(participant.getEmail());
        participantsRepository.save(part);
        return "redirect:/participants/all";
    }
    
    @GetMapping(path = "/events/all")
    public String getAllEvents(Model model) {

        model.addAttribute("allEvents", eventRepository.findAll());
        return "allEvents";
    }
    
    @GetMapping("/events/addEvent")
    public String showAddEventPage(Model model) {
        model.addAttribute("eventForm", new Evenement());
        return "newEvents";
    }
    
    @PostMapping("/events/addEvent")
    public String saveEvent(Model model, //
            @ModelAttribute("eventForm") Evenement event) {
 
        String name = event.getIntitule();
        String theme = event.getTheme();
 
        if (name!= null && name.length() > 0 //
                && theme != null && theme.length() > 0) {
            eventRepository.save(event);
 
            return "redirect:/events/all";
        }
 
        model.addAttribute("errorMessage", "Erreur");
        return "newEvents";
    }
}
