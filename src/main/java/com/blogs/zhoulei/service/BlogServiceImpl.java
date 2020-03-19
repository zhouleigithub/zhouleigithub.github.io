package com.blogs.zhoulei.service;

import com.blogs.zhoulei.NotFoundExceptions;
import com.blogs.zhoulei.dao.BlogRepository;
import com.blogs.zhoulei.po.Blog;
import com.blogs.zhoulei.po.Type;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements  BlogService {
    @Autowired
private BlogRepository blogRepository;
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicateList=new ArrayList<>();
                if(!"".equals(blog.getTitle())&&blog.getTitle()!=null){
                    predicateList.add(cb.like(root.<String>get("title"),blog.getTitle()+"% "));
                }
                if(blog.getType().getId()!=null){
                    predicateList.add(cb.equal(root.<Type>get("type").get("id"),blog.getType().getId()));
                }
                if (blog.isRecommend()){
                    predicateList.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b=blogRepository.findOne(id);
        if(b==null){
            throw new NotFoundExceptions("该博客不存在");
        }
        BeanUtils.copyProperties(b,blog);
        return blogRepository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
 blogRepository.delete(id);
    }
}
