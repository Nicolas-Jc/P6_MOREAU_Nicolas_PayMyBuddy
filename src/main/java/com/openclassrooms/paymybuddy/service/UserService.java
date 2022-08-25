package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger logger = LogManager.getLogger("UserService");
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        User verifUserToAdd = getUserByEmail(user.getEmail());
        // Email inexistant en base. Création User possible
        if (verifUserToAdd == null) {
            User newUser = new User();
            newUser.setLastname(user.getLastname());
            newUser.setFirstname(user.getFirstname());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setBalance(0.00f);

            // Enregistrement + commit en BDD
            userRepository.saveAndFlush(newUser);
            logger.info("User added DDB");
            return newUser;
        }
        logger.info("User exists Not Create");
        return verifUserToAdd;
    }

    // AUTRES METHODES A PREVOIR
    // addContacts
    // deleteContacts
    // updateBalance

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User updateUser(String userEmail, String lastName, String firstName, String password) {
        User userToUpdate = userRepository.findUserByEmail(userEmail);
        // User existant. Seules les informations Lastname, firstname, password
        // peuvent être mises à jour
        if (userToUpdate.getEmail().equals(userEmail)) {
            logger.info("userToUpdate : " + userToUpdate);
            userToUpdate.setLastname(lastName);
            userToUpdate.setFirstname(firstName);
            userToUpdate.setPassword(password);
            userRepository.saveAndFlush(userToUpdate);
        } else {
            logger.info("User not exists");
        }
        logger.info("User updated and saved");
        return userToUpdate;
    }

    public User addContact(User user, User contactToAdd) {
        user.addUserContact(contactToAdd);
        userRepository.saveAndFlush(user);
        logger.info("User added to connection");
        return contactToAdd;
    }

    /*public User removeContact(User user, User contactToRemove) {
        user.removeUserContact(contactToRemove);
        //userRepository.saveAndFlush(user);
        logger.info("User removed from connection");
        return contactToRemove;
    }*/

    public User updateBalance(String userEmail, Float amount) {
        User userToUpdateBalance = userRepository.findUserByEmail(userEmail);
        // User existant.
        if (userToUpdateBalance.getEmail().equals(userEmail)) {
            logger.info("userToUpdateBalance : " + userToUpdateBalance);
            userToUpdateBalance.setBalance(amount);
            userRepository.saveAndFlush(userToUpdateBalance);
        } else {
            logger.info("User not exists");
        }
        logger.info("User Balance updated and saved");
        return userToUpdateBalance;
    }

}
