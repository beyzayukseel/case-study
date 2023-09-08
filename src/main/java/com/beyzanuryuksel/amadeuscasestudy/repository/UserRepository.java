package com.beyzanuryuksel.amadeuscasestudy.repository;

import com.beyzanuryuksel.amadeuscasestudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdentifyNumber(String identifyNumber);

    User findByUsername(String userName);

    User findByEmail(String email);
}
