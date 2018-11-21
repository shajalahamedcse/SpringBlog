package org.obboylabs.Controller;

import org.obboylabs.model.Blog;
import org.obboylabs.Repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/blog")
    public List<Blog> index(){
        return blogRepository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return blogRepository.findOne(blogId);
    }

    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String>body){
        String  searchTerm = body.get("text");
        return blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){

        String title = body.get("title");
        String content = body.get("content");

        return  blogRepository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id ,@RequestBody Map<String, String>body){
        int blogId = Integer.parseInt(id);
        Blog blog = blogRepository.findOne(blogId);
        String title = body.get("title");
        String content = body.get("content");
        blog.setTitle(title);
        blog.setContent(content);
        return blogRepository.save(blog);
    }

    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        blogRepository.delete(blogId);
        return true;
    }
}
