package com.blogs.zhoulei.dao;

import com.blogs.zhoulei.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByname(String name);
}
