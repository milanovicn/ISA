package com.example.ISABackend.service;

import com.example.ISABackend.model.Medicine;
import com.example.ISABackend.model.User;
import com.example.ISABackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineService medicineService;

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return  userRepository.findById(id).orElseGet(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User updatedUser) {
        User fromRepository =  getByEmail(updatedUser.getEmail());

        fromRepository.setAddress(updatedUser.getAddress());
        fromRepository.setFirstName(updatedUser.getFirstName());
        fromRepository.setLastName(updatedUser.getLastName());
        fromRepository.setCity(updatedUser.getCity());
        fromRepository.setCountry(updatedUser.getCountry());
        fromRepository.setPhoneNumber(updatedUser.getPhoneNumber());
        fromRepository.setPassword(updatedUser.getPassword());

        userRepository.save(fromRepository);

        return fromRepository;
    }

    @Override
    public User addAllergy(Long user_id, Long medicine_id) {
        User u = getById(user_id);
        Medicine m = medicineService.getById(medicine_id);
        Set<Medicine> myAllergies = u.getAllergies();

        if(!myAllergies.contains(m)) {
            myAllergies.add(m);
            userRepository.save(u);
        return u;
        }
        else {
            return null;
        }

    }


}
