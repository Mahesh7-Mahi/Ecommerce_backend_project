package com.ecommerce.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.amazon.entity.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByPasswordHash(String passwordHash);
	
	
}