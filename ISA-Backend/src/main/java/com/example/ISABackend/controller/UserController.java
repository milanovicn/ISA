package com.example.ISABackend.controller;

import com.example.ISABackend.model.Medicine;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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
