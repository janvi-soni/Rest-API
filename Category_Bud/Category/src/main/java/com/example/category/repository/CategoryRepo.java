package com.example.category.repository;


import com.example.category.modal.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface CategoryRepo extends MongoRepository<Categories,String> {




}
