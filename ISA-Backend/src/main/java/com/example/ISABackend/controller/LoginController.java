package com.example.ISABackend.controller;

import com.example.ISABackend.dto.LoginRequest;
import com.example.ISABackend.model.Pharmacist;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.UserRepository;
import com.example.ISABackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("/api/pharmacy")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, @Context HttpServletRequest request) {

        User user = userService.getByEmail(loginRequest.getEmail());
        //Pharmacist pharmacist = pharmacistService.getByEmail(loginRequest.getEmail());
        if (user != null) {
            if (loginRequest.getPassword().equals(user.getPassword())) {
                HttpSession session = request.getSession();
                //moguce je da ce ti se atribut zvati po roli zbog fronta, not sure yet
                session.setAttribute("user", user);

                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            }
        /*} else (pharmacist != null) {
            HttpSession session = request.getSession();
            //moguce je da ce ti se atribut zvati po roli zbog fronta, not sure yet
            session.setAttribute("pharmacist", pharmacist);
*/
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logOut(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/whoIsLoggedIn")
    public Object whoIsLoggedIn(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //Pharmacist pharmacist = (Pharmacist)  session.getAttribute("pharmacist");
        if (user != null){
            return user;
        }
        else {
            return null;
        }
    }

}
