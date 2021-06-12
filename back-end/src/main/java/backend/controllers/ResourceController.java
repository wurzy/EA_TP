package backend.controllers;

import backend.beans.ResourceBean;
import backend.json.RateResourceJSON;
import backend.json.RatingsJSON;
import backend.json.ResourceJSON;
import backend.json.TypesJSON;
import org.springframework.web.bind.annotation.*;

import javax.ejb.EJB;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/resource")
public class ResourceController {

    @EJB
    ResourceBean rb;
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

    /*
    @PostMapping("/")
    public ResourceJSON upload(){
        return rb.incDownloads(id);
    }
    */

    @PostMapping("/inc_downloads/{id}")
    public ResourceJSON incDownloads(@PathVariable int id){
        return rb.incDownloads(id);
    }

    @DeleteMapping("/{id}")
    public ResourceJSON deleteResource(@PathVariable int id){
        return rb.delResource(id);
    }

}
