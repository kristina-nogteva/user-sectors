package com.kristina.user.sectors.repository;

import com.kristina.user.sectors.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {
}
