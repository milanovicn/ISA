package com.example.ISABackend.controller;

import com.example.ISABackend.model.ConfirmationToken;
import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.ConfirmationTokenRepository;
import com.example.ISABackend.repository.UserRepository;
import com.example.ISABackend.service.EmailService;
import com.example.ISABackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.Properties;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;


    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody User newUser, @Context HttpServletRequest request) {

        User user = userService.registerUser(newUser);
        if(user != null) {
            Properties props = new Properties();
            props.put("mail.mime.address.strict", "false");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getDefaultInstance(props);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("ISA.tim66@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/api/user/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);


            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("User with email is already registered", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount(@RequestParam("token")String confirmationToken, @Context HttpServletRequest request)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUserEntity().getEmail());
            user.setPrviPutLogovan(false);
            userRepository.save(user);
            request.getSession().setAttribute("user", user);
            return new ResponseEntity<String>("Registration successful", HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<String>("Registration not successful", HttpStatus.METHOD_NOT_ALLOWED);
        }


    }


    @PutMapping(value = "/edit")
    public ResponseEntity updateUser(@RequestBody User updatedUser, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        User user=userService.updateUser(updatedUser);
        request.getSession().setAttribute("user", user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/allergies/{user_id}")
    public Object getMyAllergies(@PathVariable("user_id") Long user_id, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
      return  userService.getById(user_id).getAllergies();
    }


    @PutMapping(value = "/allergy/{medicine_id}")
    public ResponseEntity<?> addNewAllergy(@RequestBody User updatedUser, @PathVariable("medicine_id") Long medicine_id, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        User u = userService.addAllergy(updatedUser.getId(), medicine_id);
        if (u == null) {
            return new ResponseEntity<String>("This allergy is already added!", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<User>(u, HttpStatus.CREATED);
    }

    private User authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }


}
