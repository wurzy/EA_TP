package backend.beans;

import backend.beans.locals.AuthenticationLocal;
import backend.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless(name = "AuthenticationEJB")
@Local(AuthenticationLocal.class)
@Component
public class AuthenticationBean implements AuthenticationLocal {
    //@Autowired
    //private static JWTUtil jwtUtil;

    public AuthenticationBean() {
    }

    @Override
    public boolean register(String email, String password) {
        System.out.println("register " + email + password);
        return false;
    }

    @Override
    public boolean login(String email, String password) {
        System.out.println("login " + email + password);
        return false;
    }
}
