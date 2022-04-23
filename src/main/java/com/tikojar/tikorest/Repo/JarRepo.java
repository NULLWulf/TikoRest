package com.tikojar.tikorest.Repo;

import com.tikojar.tikorest.DAO.Jar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JarRepo extends MongoRepository<Jar, String> {
}
