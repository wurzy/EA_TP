package backend.controllers;

import backend.dao.Users;
import backend.dao.UsersDAO;
import backend.json.UserJSON;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

    @GetMapping("/test")
    public UserJSON helloWorld() throws Exception{
        return new UserJSON(UsersDAO.getUsersByORMID(1));
    }

}
