package com.nobrain.spring.kotlinexample.repository

import com.nobrain.spring.kotlinexample.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>