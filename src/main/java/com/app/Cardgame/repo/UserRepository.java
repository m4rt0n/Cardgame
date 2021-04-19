package com.app.Cardgame.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.Cardgame.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
