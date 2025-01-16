package com.devminrat.libraryProject.controllers;

import com.devminrat.libraryProject.dao.PersonDAO;
import com.devminrat.libraryProject.models.Person;
import com.devminrat.libraryProject.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//I used DAO and Service together to simplify project.

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonService personService, final PersonDAO personDAO) {
        this.personService = personService;
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
    public String savePerson(@ModelAttribute("person") @Valid final Person person, final BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

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
    public String updatePerson(@ModelAttribute("person") @Valid final Person person, BindingResult bindingResult, @PathVariable final int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable final int id, Model model) {
        final Person person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "people/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable final int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }


}
