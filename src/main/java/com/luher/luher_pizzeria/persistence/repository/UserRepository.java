package com.luher.luher_pizzeria.persistence.repository;

import com.luher.luher_pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
