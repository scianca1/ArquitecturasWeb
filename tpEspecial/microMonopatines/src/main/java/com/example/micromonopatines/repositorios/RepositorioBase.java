package com.example.micromonopatines.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface RepositorioBase<T,ID extends Serializable> extends MongoRepository<T, ID> {

}