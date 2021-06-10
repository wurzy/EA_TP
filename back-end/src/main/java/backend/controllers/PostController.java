package backend.controllers;

import backend.beans.PostBean;
import backend.json.*;
import org.springframework.web.bind.annotation.*;

import javax.ejb.EJB;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/post")
public class PostController {
    @EJB
    PostBean pb;

    @GetMapping("/{id}")
    public PostJSON getPost(@PathVariable int id){
        return pb.getPost(id);
    }

    @PostMapping("/")
    public PostJSON create(@RequestParam String title, @RequestParam String body, @RequestParam java.sql.Timestamp createdAt, @RequestParam int idUser, @RequestParam int idResource){
        return pb.createPost(new CreatePostJSON(title,body,createdAt,idUser,idResource));
    }

    @PostMapping("/comment/{id}")
    public PostJSON commentPost(@PathVariable int id, @RequestBody SimpleCommentJSON scj){
        return pb.addComment(id,scj);
    }
}
