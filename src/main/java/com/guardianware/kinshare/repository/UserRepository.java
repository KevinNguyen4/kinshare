package com.guardianware.kinshare.repository;

import com.guardianware.kinshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}