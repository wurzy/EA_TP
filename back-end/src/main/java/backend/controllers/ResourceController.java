package backend.controllers;

import backend.beans.FileSystemBean;
import backend.beans.ResourceBean;
import backend.dao.Files;
import backend.dao.FilesDAO;
import backend.dao.Ratings;
import backend.dao.Resources;
import backend.json.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.activation.FileTypeMap;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipOutputStream;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/resource")
public class ResourceController {

    @EJB
    ResourceBean rb;
    @EJB
    FileSystemBean fsb;
    // atualizar
    @GetMapping("/")
    public ResourceJSON[] getResources(){
        return rb.getResources();
    }

    @GetMapping("/file/{id}")
    public FileSystemResource getFile(@PathVariable int id){
        File img = fsb.getFile(id);
        return new FileSystemResource(img);
    }
    @GetMapping("/type/{type}")
    public ResourceJSON[] getResourcesOfType(@PathVariable String type){
        return rb.getResourcesOfType(type);
    }

    @PostMapping( "/download/")
    public ResponseEntity<StreamingResponseBody> downloadResources(@RequestBody DownloadJSON ids){
        String time = Long.toString(System.currentTimeMillis());
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"" + time + ".zip\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(out -> {
                    var zipOutputStream = new ZipOutputStream(out);
                    fsb.zipResources(ids,zipOutputStream);
                    for (int id : ids.getIds()) {
                        rb.incDownloads(id);
                    }
                    zipOutputStream.close();
                });
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

    //@PostMapping("/inc_downloads/{id}")
    //public ResourceJSON incDownloads(@PathVariable int id){
    //    return rb.incDownloads(id);
    //}

    @DeleteMapping("/{id}")
    public ResourceJSON deleteResource(@PathVariable int id){
        return rb.delResource(id);
    }

}
