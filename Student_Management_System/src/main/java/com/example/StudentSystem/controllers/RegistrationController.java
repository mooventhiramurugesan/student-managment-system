package com.example.StudentSystem.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.StudentSystem.Exception.RecordNotFoundException;
import com.example.StudentSystem.models.Registration;
import com.example.StudentSystem.services.RegistrationServiceInt;


@RestController
@RequestMapping("/welcome")
public class RegistrationController {

    @Autowired
    private RegistrationServiceInt registrationServiceInt;



    /*
    * Here using post method to return the first page
    */
    @RequestMapping(value = {"/index"},method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("first");
        return modelAndView;
    }

    /*
     * Here using post method to storing data's in database
     */
    @PostMapping(value = "/store")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ModelAttribute("registration") @RequestBody Registration registration) {
        registrationServiceInt.save(registration);
    }




    @GetMapping("/list")
    public List<Registration> list(Model model) {
        return registrationServiceInt.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") long id) {
        return registrationServiceInt.findById(id)
                .map(registration -> {
                    try {
                        return ResponseEntity.ok()
                                .location(new URI("/" + registration.getId()))
                                .body(registration);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    Registration registration(@RequestBody Registration newRegistration, @PathVariable Long id) {

        return registrationServiceInt.findById(id)
                .map(student -> {
                    student.setRegno(newRegistration.getRegno());
                    student.setName(newRegistration.getName());
                    student.setDept(newRegistration.getDept());
                    student.setYear(newRegistration.getYear());

                    return registrationServiceInt.save(student);
                })
                .orElseGet(() -> {
                    newRegistration.setId(id);
                    return registrationServiceInt.save(newRegistration);
                });
    }

    /*
    * Here using GET method to show all data's from database
    */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView message() {
        ModelAndView mav = new ModelAndView("/getting");
        mav.addObject("registrations", registrationServiceInt.findAll());
        return mav;
    }
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String messages(Model model) {
        model.addAttribute("registrations", registrationServiceInt.findAll());
        return "/reg/getting";
    }

    @ModelAttribute("/registration")
    public List<Registration> messages() {
        return registrationServiceInt.findAll();
    }



    /*
    * This below code for show data's from database for delete page
    */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteMethod() {
        ModelAndView mav = new ModelAndView("/deleting");
        mav.addObject("deletes", registrationServiceInt.findAll());
        return mav;
    }
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String deleteMethods(Model model) {
        model.addAttribute("deletes", registrationServiceInt.findAll());
        return "/del/deleting";
    }

    @ModelAttribute("/delete")
    public List<Registration> deleteMethods() {
        return registrationServiceInt.findAll();
    }


    /*
    * Below this code for deleting a particular id
    */
    @RequestMapping(path = "/delete/{id}")
    public ModelAndView deleteEmployeeById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        ModelAndView modelAndView = new ModelAndView("succesfullyDelete");
        registrationServiceInt.deleteEmployeeById(id);
        return modelAndView;
    }

}