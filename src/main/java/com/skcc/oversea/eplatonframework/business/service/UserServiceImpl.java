package com.skcc.oversea.eplatonframework.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skcc.oversea.user.entity.User;
import com.skcc.oversea.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * User Service Implementation for SKCC Oversea
 * 
 * Provides user management operations
 * using Spring Boot and modern Java patterns.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        try {
            logger.info("Finding all users");
            List<User> users = userRepository.findAll();
            logger.info("Found {} users", users.size());
            return users;
        } catch (Exception e) {
            logger.error("Error finding all users", e);
            return List.of();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String userId) {
        try {
            logger.info("Finding user by ID: {}", userId);
            Optional<User> user = userRepository.findByUserId(userId);
            if (user.isPresent()) {
                logger.info("Found user: {}", userId);
                return user.get();
            } else {
                logger.warn("User not found: {}", userId);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error finding user by ID: {}", userId, e);
            return null;
        }
    }

    @Override
    @Transactional
    public User save(User user) {
        try {
            logger.info("Saving user: {}", user.getUserId());
            User savedUser = userRepository.save(user);
            logger.info("User saved successfully: {}", savedUser.getUserId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error saving user: {}", user.getUserId(), e);
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(String userId) {
        try {
            logger.info("Deleting user by ID: {}", userId);
            Optional<User> user = userRepository.findByUserId(userId);
            if (user.isPresent()) {
                userRepository.delete(user.get());
                logger.info("User deleted successfully: {}", userId);
            } else {
                logger.warn("User not found for deletion: {}", userId);
            }
        } catch (Exception e) {
            logger.error("Error deleting user: {}", userId, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(String userId) {
        try {
            logger.debug("Checking if user exists: {}", userId);
            boolean exists = userRepository.existsByUserId(userId);
            logger.debug("User exists: {} = {}", userId, exists);
            return exists;
        } catch (Exception e) {
            logger.error("Error checking if user exists: {}", userId, e);
            return false;
        }
    }
}