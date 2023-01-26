package com.bazydanychtest.user.tables;


import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
