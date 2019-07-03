package com.studymatcher.profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @author pmandala
 * This is profile repository
 */
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
//	Profile findByUserId(Integer userId);
	Optional<Profile> findByUsername(String username); 
	Optional<Profile>findByUserId(Integer user_id); 

}
