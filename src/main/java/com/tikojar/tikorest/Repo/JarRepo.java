package com.tikojar.tikorest.Repo;

import com.tikojar.tikorest.DAO.Jar;
import com.tikojar.tikorest.DAO.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface JarRepo extends MongoRepository<Jar, String> {

//    @Query("{$push: {messages: ?0}}")
//    void addMessage(String serverId, Message message);

    // Pulls Jar by Passing in ServerID
    Jar findByServerID(String serverId);
}
