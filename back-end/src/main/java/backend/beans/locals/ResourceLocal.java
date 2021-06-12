package backend.beans.locals;

import backend.json.RateResourceJSON;
import backend.json.RatingsJSON;
import backend.json.ResourceJSON;
import backend.json.TypesJSON;

import javax.ejb.Local;

@Local
public interface ResourceLocal {
    TypesJSON[] getResourceTypes();
    ResourceJSON[] getResources();
    ResourceJSON getResource(int id);
    RatingsJSON rateResource(int id, RateResourceJSON rrj);
    ResourceJSON incDownloads(int id);
    ResourceJSON delResource(int id);
}
