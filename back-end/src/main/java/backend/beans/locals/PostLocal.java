package backend.beans.locals;
import backend.json.CreatePostJSON;
import backend.json.PostJSON;
import backend.json.SimpleCommentJSON;

import javax.ejb.Local;

@Local
public interface PostLocal {
    PostJSON[] getPosts();
    PostJSON createPost(CreatePostJSON cpj);
    PostJSON getPost(int id);
    PostJSON addComment(int id, SimpleCommentJSON cj);
}
