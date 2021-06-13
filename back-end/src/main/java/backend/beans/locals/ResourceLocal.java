package backend.beans.locals;

import backend.dao.Resources;
import backend.json.*;

import javax.ejb.Local;

@Local
public interface ResourceLocal {
    Resources createResource(CreateResourceJSON crj);
    TypesJSON[] getResourceTypes();
    ResourceJSON[] getResources();
    ResourceJSON[] getResourcesOfType(String t);
    ResourceJSON getResource(int id);
    RatingsJSON rateResource(int id, RateResourceJSON rrj);
    ResourceJSON incDownloads(int id);
    ResourceJSON delResource(int id);
}
