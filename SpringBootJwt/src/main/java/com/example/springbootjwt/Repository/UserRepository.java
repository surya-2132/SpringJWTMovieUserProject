package com.example.springbootjwt.Repository;

import com.example.springbootjwt.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    //getUserByEmail
    //getUserById
    //getUserLessThanAge
    //getUserByNameOrEmail
    //getUserByAgeSortByName


    //SELECT * FROM user WHERE email = :email
    @Query("{email: \"?0\"}")
    List<User> getUserByEmail(String email);

    //SELECT * FROM user WHERE id = :id
    @Query("{id:?0}")
    Optional<User> getUserById(ObjectId id);

    //SELECT * FROM user WHERE age < :age
    @Query("{age: {$lt: ?0}}")
    List<User> getUserLessThanAge(int age);

    //SELECT * FROM user WHERE name = :name or email = :email
    @Query("{$or:[{name: ?0},{email: ?1}]}")
    List<User> getUserByNameOrEmail(String name, String email);

    //SELECT * FROM user WHERE age=:age ORDER BY name ASC
    @Query(value="{age:?0}", sort="{name: 1}") //1 for Asc and -1 for Desc
    List<User> getUserByAgeSortByName(int age);
}
