package com.studymatcher.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * This is the User Repository 
 * @author pmandala
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username); 

	User findByUsernameAndEmail(String username, String email);

	Optional<User> findById(Integer id);
	

}
