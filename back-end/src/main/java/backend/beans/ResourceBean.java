package backend.beans;

import backend.beans.locals.ResourceLocal;
import backend.dao.*;
import backend.json.*;
import org.springframework.stereotype.Component;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless(name = "PostBeanEJB")
@Local(ResourceLocal.class)
@Component
public class ResourceBean {

    public ResourceJSON[] getResources() {
        try{
            Resources[] us = ResourcesDAO.listResourcesByQuery(null,null);
            ResourceJSON[] s = new ResourceJSON[us.length];
            for(int i = 0; i < us.length; i++){
                Ratings[] rs = RatingsDAO.listRatingsByQuery("idResource="+us[i].getIdResource(),null);
                Files[] fs = FilesDAO.listFilesByQuery("idResource="+us[i],null);
                s[i] = new ResourceJSON(us[i],rs,fs);
            }
            return s;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResourceJSON getResource(int id){
        try {
            Resources u = ResourcesDAO.getResourcesByORMID(id);
            if(u!=null){
                Ratings[] rs = RatingsDAO.listRatingsByQuery("idResource="+id,null);
                Files[] fs = FilesDAO.listFilesByQuery("idResource="+id,null);
                return new ResourceJSON(u,rs,fs);
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

    public TypesJSON getResourceTypes(){
        try{
            Resourcetypes[] rs = ResourcetypesDAO.listResourcetypesByQuery(null,null);
            String[] s = new String[rs.length];
            for(int i = 0; i < rs.length; i++){
                s[i] = rs[i].getType();
            }
            return new TypesJSON(s);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public RatingsJSON rateResource(int id, RateResourceJSON rrj){
        try{
            Ratings r = RatingsDAO.loadRatingsByQuery("idUser=" + rrj.getIdUser(),null);
            if(r==null){
                r = new Ratings();
                r.setIdResource(ResourcesDAO.getResourcesByORMID(id));
                r.setIdUser(UsersDAO.getUsersByORMID(rrj.getIdUser()));
            }
            r.setRating(rrj.getRating());
            RatingsDAO.save(r);
            return new RatingsJSON(r);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResourceJSON incDownloads(int id){
        try{
            Resources r = ResourcesDAO.getResourcesByORMID(id);
            r.setnDownloads(r.getnDownloads()+1);
            ResourcesDAO.save(r);
            Ratings[] rs = RatingsDAO.listRatingsByQuery("idResource="+id,null);
            Files[] fs = FilesDAO.listFilesByQuery("idResource="+id,null);
            return new ResourceJSON(r,rs,fs);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResourceJSON delResource(int id){
        // FILESYSTEM BEAN REMOVER OS FICHEIROS DO FILESYSTEM
        try{
            Resources r = ResourcesDAO.getResourcesByORMID(id);
            Ratings[] rs = RatingsDAO.listRatingsByQuery("idResource="+id,null);
            Updates[] us = UpdatesDAO.listUpdatesByQuery("idResource="+id,null);
            Files[] fs = FilesDAO.listFilesByQuery("idResource="+id,null);
            for (Ratings rr : rs){
                RatingsDAO.delete(rr);
            }
            for (Updates rr : us){
                UpdatesDAO.delete(rr);
            }
            for (Files rr : fs){
                FilesDAO.delete(rr);
            }
            ResourcesDAO.delete(r);
            return new ResourceJSON(r,rs,fs);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
