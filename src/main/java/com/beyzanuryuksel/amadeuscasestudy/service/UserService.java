package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.User;
import com.beyzanuryuksel.amadeuscasestudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    public User findByIdentifyNumber(String identifyNumber) {
        return userRepository.findByIdentifyNumber(identifyNumber);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    
    public User findById(Long userId) throws Exception{
        return userRepository.findById(userId).
                orElseThrow(() -> new Exception("User not found"));
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Page<User> getAllUsers(int pageSize, int pageNumber) {
        Pageable paged= PageRequest.of(pageNumber,pageSize);
        return (Page<User>) userRepository.findAll(paged);
    }
}
