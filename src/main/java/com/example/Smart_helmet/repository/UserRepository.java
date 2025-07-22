package com.example.Smart_helmet.repository;
import com.example.Smart_helmet.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByEmail(String email);
    Optional<UserInfo> findByUuid(String uuid);
}
