package com.smart.smartcontact.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.smart.smartcontact.dao.ContactRepository;
import com.smart.smartcontact.dao.UserRepository;
import com.smart.smartcontact.entity.Contact;
import com.smart.smartcontact.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ContactRepository contactRepository;

    @ModelAttribute
    public void addcommon(Model m, Principal principal) {
        String username = principal.getName();
        System.out.println("username is " + username);
        User user = userRepository.getByUserName(username);
        System.out.println("USER" + user);
        m.addAttribute("user", user);

    }

    @GetMapping("/addcontact")
    public String addcontact(Model model) {
        model.addAttribute("title", "Add Contact");
        model.addAttribute("contact", new Contact());
        return "normal/add_contact_form";

    }

    @PostMapping("/processcontact")
    public String processContact(
            @ModelAttribute Contact contact,
            @RequestParam("profileimage") MultipartFile file,
            Principal principal) {

        try {
            String name = principal.getName();
            if (file.isEmpty()) {
                System.out.println("nobody upload shit");
                contact.setImage("contact.png");
            } else {
                System.out.println("everything ok file uploaded");
                contact.setImage(file.getOriginalFilename());
                File savefile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image is uploaded");
            }
            User user = this.userRepository.getByUserName(name);
            contact.setUser(user);
            user.getContacts().add(contact);
            this.userRepository.save(user);
            System.out.println("added to Database" + contact);
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
            e.printStackTrace();

        }

        return "redirect:/user/addcontact?success";

    }

    @GetMapping("/showcontact/{page}")
    public String showcontact(@PathVariable("page") Integer page, Model m, Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.getByUserName(username);
        Pageable pageable = PageRequest.of(page, 10);
        Page<Contact> contacts = this.contactRepository.findContactbyUser(user.getId(), pageable);
        m.addAttribute("contacts", contacts);
        m.addAttribute("currentPage", page);
        m.addAttribute("totalPages", contacts.getTotalPages());

        m.addAttribute("title", "Contact Page");
        return "normal/showcontact";
    }

    @GetMapping("/{cId}/contact")
    public String showDetails(@PathVariable("cId") Integer cId, Model model, Principal principal) {
        Optional<Contact> contact = this.contactRepository.findById(cId);
        Contact contacts = contact.get();
        String username = principal.getName();
        User user = this.userRepository.getByUserName(username);
        if (user.getId() == contacts.getUser().getId()) {
            model.addAttribute("title", contacts.getName());
            model.addAttribute("contacts", contacts);
        }
        return "normal/contact_details";

    }

    @GetMapping("/{cId}/deletecontact")
    public String deletecontact(@PathVariable("cId") Integer cid, Model model, Principal principal,
            RedirectAttributes redirectAttributes) {
        Optional<Contact> contactOptional = this.contactRepository.findById(cid);
        Contact contact = contactOptional.get();
        String userName = principal.getName();
        User user = this.userRepository.getByUserName(userName);
        if (user.getId() == contact.getUser().getId()) {
            User cont = this.userRepository.getByUserName(principal.getName());
            cont.getContacts().remove(contact);
            this.userRepository.save(cont);
        }
        redirectAttributes.addFlashAttribute("message", "Deleted Successfully");
        return "redirect:/user/showcontact/0";
    }

    @PostMapping("/{cId}/editcontact")
    public String editcontact(@PathVariable("cId") Integer cid, Model model) {
        model.addAttribute("title", "update page");
        Contact optional = this.contactRepository.findById(cid).get();
        model.addAttribute("contact", optional);

        return "normal/update_contact";
    }

    @PostMapping("/updatecontact")
    public String updatecontact(@ModelAttribute Contact contact, @RequestParam("profileimage") MultipartFile file,
            Model model, RedirectAttributes redirectAttributes, Principal principal) {
        System.out.println("CID" + contact.getcId());
        // old pic getting
        Contact oldcontactdetails = this.contactRepository.findById(contact.getcId()).get();
        try {
            if (!file.isEmpty()) {
                // old file deleting
                File deletefile = new ClassPathResource("static/img").getFile();
                File file2 = new File(deletefile, oldcontactdetails.getImage());
                file2.delete();

                // updating file
                File savefile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image is uploaded");
                contact.setImage(file.getOriginalFilename());
            } else {
                contact.setImage(oldcontactdetails.getImage());
            }

            User user = this.userRepository.getByUserName(principal.getName());
            contact.setUser(user);
            this.contactRepository.save(contact);

        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "Updated Successfully");

        return "redirect:/user/" + contact.getcId() + "/contact";

    }

    @GetMapping("/profile")
    public String profile(Model model) {

        return "normal/profile";

    }

}
