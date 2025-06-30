package com.smart.smartcontact.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.smartcontact.dao.UserRepository;
import com.smart.smartcontact.entity.User;
import com.smart.smartcontact.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Controller
public class homeController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Home-Smart Contact Manager");
        return "home";
    }
    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About-Smart Contact-Manager");
        return "about";
    }
     @RequestMapping("/signup") 
    public String signup(Model model){
        model.addAttribute("title", "Register-Smart Contact Manager");
        model.addAttribute("user",new User());
        return "signup";
    }
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
   public String register(@Valid @ModelAttribute("user") User user, BindingResult result1,
                       @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
                       Model model, HttpSession session)

 {
        try {
            if (!agreement) {
                System.out.println("You have Not agree on term and conditon");
                throw new Exception("You have Not agree on term and conditon");
            }
            if (result1.hasErrors()) {
                System.out.println("error"+toString());
                model.addAttribute("user", user);
                return "signup";
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            System.out.println("agreement" + agreement);
            System.out.println("User" + user);
            User savedUser = this.userRepository.save(user);
            System.out.println("result" + savedUser);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Succesfully Register!!", "alert-success"));
            return "signup";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something Went Wrong!!" + e.getMessage(), "alert-danger"));
            return "signup";
        }
    }
    @GetMapping("/signin")
    public String login(Model model){
        model.addAttribute("title","Welcome To Login");

        return "login";
    }

 
    
    
}
