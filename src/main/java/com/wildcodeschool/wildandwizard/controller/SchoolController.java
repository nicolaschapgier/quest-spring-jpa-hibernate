package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SchoolController {


    @Autowired
    private SchoolRepository repository;

    @GetMapping("/schools")
    public String getAll(Model model) {
        // TODO : find all schools
        model.addAttribute("schools",repository.findAll());

        return "schools";
    }
    // TODO : get school repository by dependency injection

    @GetMapping("/school")
    public String getSchool(Model model,
// TODO : find one school by id
                            @RequestParam(required = false) Long id) {
        School school = new School();
        if(id !=null){
            Optional<School> optionalSchool = repository.findById(id);
            if (optionalSchool.isPresent()){
                school = optionalSchool.get();
            }
        }
        model.addAttribute("school", school);


        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@ModelAttribute School school) {
        repository.save(school);
        // TODO : create or update a school

        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {
        repository.deleteById(id);
        // TODO : delete a school

        return "redirect:/schools";
    }
}
