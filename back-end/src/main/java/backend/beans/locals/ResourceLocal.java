package backend.beans.locals;

import backend.dao.Resources;
import backend.json.*;

import javax.ejb.Local;

@Local
public interface ResourceLocal {
    Resources createResource(CreateResourceJSON crj);
    Resources updateResource(UpdateResourceJSON crj, int idUser);
    TypesJSON[] getResourceTypes();
    ResourceJSON[] getResources();
    ResourceJSON[] getResourcesOfType(String t);
    ResourceJSON getResource(int id);
    RatingsJSON rateResource(int id, int idUser, RateResourceJSON rrj);
    ResourceJSON incDownloads(int id);
    ResourceJSON delResource(int id, int idUser);
}
