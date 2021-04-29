package backend.beans.locals;

import javax.ejb.Local;

@Local
public interface AuthenticationLocal {
    boolean register(String email, String password);
    boolean login(String email, String password);
}
