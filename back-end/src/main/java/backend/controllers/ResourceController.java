package backend.controllers;

import backend.beans.FileSystemBean;
import backend.beans.ResourceBean;
import backend.dao.Files;
import backend.dao.FilesDAO;
import backend.dao.Ratings;
import backend.dao.Resources;
import backend.json.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ejb.EJB;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/resource")
public class ResourceController {

    @EJB
    ResourceBean rb;
    @EJB
    FileSystemBean fsb;
    // inserir, atualizar
    @GetMapping("/")
    public ResourceJSON[] getResources(){
        return rb.getResources();
    }

    @GetMapping("/type/{type}")
    public ResourceJSON[] getResourcesOfType(@PathVariable String type){
        return rb.getResourcesOfType(type);
    }

    @GetMapping("/{id}")
    public ResourceJSON getResource(@PathVariable int id){
        return rb.getResource(id);
    }

    @GetMapping("/types")
    public TypesJSON getResourcesTypes(){
        return rb.getResourceTypes();
    }

    @PostMapping("/rate/{id}")
    public RatingsJSON rateResource(@PathVariable int id, @RequestBody RateResourceJSON rrj){
        return rb.rateResource(id,rrj);
    }

    @PostMapping("/")
    public ResourceJSON upload(@RequestParam String title, @RequestParam String description, @RequestParam java.sql.Timestamp registeredAt, @RequestParam boolean visibility, @RequestParam int idUser, @RequestParam String type, @RequestParam("file") MultipartFile[] files){
        Resources r = rb.createResource(new CreateResourceJSON(title,description,registeredAt,visibility,idUser,type));
        Files[] fs = fsb.saveFiles(files,r);
        return new ResourceJSON(r,new Ratings[0], fs, new PostJSON[0]);
    }

    @PostMapping("/inc_downloads/{id}")
    public ResourceJSON incDownloads(@PathVariable int id){
        return rb.incDownloads(id);
    }

    @DeleteMapping("/{id}")
    public ResourceJSON deleteResource(@PathVariable int id){
        return rb.delResource(id);
    }

}
