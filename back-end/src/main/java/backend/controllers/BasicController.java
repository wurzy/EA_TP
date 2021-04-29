package backend.controllers;

import backend.beans.AuthenticationBean;
import backend.dao.Users;
import backend.dao.UsersDAO;
import backend.json.UserJSON;
import backend.beans.locals.AuthenticationLocal;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

@RestController
public class BasicController {
    @EJB
    AuthenticationBean ab;

    @GetMapping("/test")
    public Object helloWorld() throws Exception{
        Context c = new InitialContext();
        //AuthenticationBean aBean = (AuthenticationBean) c.lookup("java:module/AuthenticationEJB");
        Users u = UsersDAO.getUsersByORMID(13);
        ab.login(u.getEmail(),u.getPassword());
        return new UserJSON(u);
    }

}
