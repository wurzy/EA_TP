package backend.beans;

import backend.beans.locals.FileSystemLocal;
import backend.dao.*;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "FileSystemEJB")
@Local(FileSystemLocal.class)
@Component
public class FileSystemBean {
    private final String imagesDirectory = System.getProperty("user.dir")+"/uploads/images";
    private final String resourceDirectory = System.getProperty("user.dir")+"/uploads/files";

    public backend.dao.Files[] saveFiles(MultipartFile[] files, Resources r) {
        try{
            for (MultipartFile file : files) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String time = Long.toString(System.currentTimeMillis());
                String name = arr[0] + time + "." + arr[1];
                Path fileNameAndPath = Paths.get(resourceDirectory, name);
                Tika tika = new Tika();
                String mimeType = tika.detect(file.getOriginalFilename());
                long size = file.getSize();
                Files.write(fileNameAndPath, file.getBytes());
                backend.dao.Files f = new backend.dao.Files();
                f.setName(file.getOriginalFilename());
                f.setMimetype(mimeType);
                f.setIdResource(r);
                f.setPath(name);
                f.setSize(size);
                FilesDAO.save(f);
            }
            return FilesDAO.listFilesByQuery("idResource="+r.getIdResource(),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new backend.dao.Files[0];
    }

    public String savePicture(MultipartFile file, int id){
        String[] arr = file.getOriginalFilename().split("[.]");
        String time = Long.toString(System.currentTimeMillis());
        String name = arr[0] + time + "." + arr[1];
        Path fileNameAndPath = Paths.get(imagesDirectory, name);
        try {
            Files.write(fileNameAndPath, file.getBytes());
            Users u = UsersDAO.getUsersByORMID(id);
            u.setPicture(name);
            UsersDAO.save(u);
            return "{\"image\":\"" + name + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getImage(String name){
        return new File(imagesDirectory + "\\" + name);
    }

}
