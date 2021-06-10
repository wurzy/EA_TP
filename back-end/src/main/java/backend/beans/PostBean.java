package backend.beans;

import backend.beans.locals.PostLocal;
import backend.dao.*;
import backend.json.*;
import org.springframework.stereotype.Component;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless(name = "PostBeanEJB")
@Local(PostLocal.class)
@Component
public class PostBean {

    public PostJSON createPost(CreatePostJSON cpj) {
        try{
            Posts p = new Posts();
            p.setTitle(cpj.getTitle());
            p.setBody(cpj.getBody());
            p.setCreatedAt(cpj.getCreatedAt());
            p.setIdResource(ResourcesDAO.getResourcesByORMID(cpj.getIdResource()));
            p.setIdUser(UsersDAO.getUsersByORMID(cpj.getIdUser()));
            PostsDAO.save(p);
            return new PostJSON(p, new CommentJSON[0]);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public PostJSON getPost(int id){
        try {
            Posts u = PostsDAO.getPostsByORMID(id);
            Comments[] ps = CommentsDAO.listCommentsByQuery("idPost="+id,null);
            CommentJSON[] s = new CommentJSON[ps.length];
            for(int i = 0; i < ps.length; i++){
                s[i] = new CommentJSON(ps[i]);
            }
            if(u!=null){
                return new PostJSON(u,s);
            }
            else {
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public PostJSON addComment(int id, SimpleCommentJSON cj){
        try{
            Posts p = PostsDAO.getPostsByORMID(id);
            Users u = UsersDAO.getUsersByORMID(cj.getIdUser());
            Comments c = new Comments();
            c.setBody(cj.getBody());
            c.setIdPost(p);
            c.setCreatedAt(cj.getCreatedAt());
            c.setIdUser(u);
            CommentsDAO.save(c);
            Comments[] ps = CommentsDAO.listCommentsByQuery("idPost="+id,null);
            CommentJSON[] s = new CommentJSON[ps.length];
            for(int i = 0; i < ps.length; i++){
                s[i] = new CommentJSON(ps[i]);
            }
            return new PostJSON(p,s);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
