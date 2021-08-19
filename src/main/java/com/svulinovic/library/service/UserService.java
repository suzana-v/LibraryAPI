package com.svulinovic.library.service;

import com.svulinovic.library.model.UserRequest;
import com.svulinovic.library.model.UserResponse;
import com.svulinovic.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUser(Integer id) {
        return userRepository.getUser(id);
    }

    public void createUser(UserRequest request) {
        Map<String, Object> inParam = inParam(request);
        userRepository.createUser(inParam);
    }

    public void updateUser(Integer id, UserRequest request) {
        Map<String, Object> inParam = inParam(request);
        inParam.put("id", id);
        userRepository.updateUser(inParam);
    }

    public UserResponse getUserByLongestOverdueDate() {
        return userRepository.getUserByLongestOverdueDate();
    }

    private Map<String, Object> inParam(UserRequest user) {
        Map<String, Object> inParam = new HashMap<>();
        inParam.put("firstName", user.getFirstName());
        inParam.put("lastName", user.getLastName());
        inParam.put("dateOfBirth", user.getDateOfBirth());
        return inParam;
    }
}
