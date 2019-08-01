package com.edu.account.dao.mapper;

import com.edu.account.model.entity.Parent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ParentMapper {
    void insert(Parent parent);
}
