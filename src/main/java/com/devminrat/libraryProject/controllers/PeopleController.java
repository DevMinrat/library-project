package com.devminrat.libraryProject.controllers;

import com.devminrat.libraryProject.models.Person;
import com.devminrat.libraryProject.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getPeople(final Model model) {
        model.addAttribute("people", peopleService.findAll());
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

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable final int id, Model model) {
        final Person person = peopleService.findById(id);
        model.addAttribute("person", person);
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid final Person person, BindingResult bindingResult, @PathVariable final int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable final int id, Model model) {
        final Person person = peopleService.findById(id);
        model.addAttribute("person", person);
        return "people/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable final int id) {
        peopleService.deleteById(id);
        return "redirect:/people";
    }


}
