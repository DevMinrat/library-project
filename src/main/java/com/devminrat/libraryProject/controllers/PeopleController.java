package com.devminrat.libraryProject.controllers;

import com.devminrat.libraryProject.dao.PersonDAO;
import com.devminrat.libraryProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(final PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getPeople(final Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/people";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") final Person person) {
        return "people/new";
    }

    @PostMapping
    public String savePerson(@ModelAttribute("person") final Person person) {
        personDAO.createPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable final int id, Model model) {
        final Person person = personDAO.getPerson(id);
        model.addAttribute("person", person);
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") final Person person, @PathVariable final int id) {
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }


}