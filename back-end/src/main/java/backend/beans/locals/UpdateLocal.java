package backend.beans.locals;


import backend.json.CreateUpdateJSON;
import backend.json.SimpleStateJSON;
import backend.json.UpdateJSON;

import javax.ejb.Local;

@Local
public interface UpdateLocal {
    UpdateJSON[] getUpdatesUser(int idUser);
    UpdateJSON[] getUpdates();
    UpdateJSON createUpdate(CreateUpdateJSON cuj);
    UpdateJSON[] changeState(int idResource, SimpleStateJSON state);
}
