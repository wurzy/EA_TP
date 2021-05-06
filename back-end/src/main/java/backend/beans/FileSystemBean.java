package backend.beans;

import backend.beans.locals.FileSystemLocal;
import backend.dao.Users;
import backend.dao.UsersDAO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Stateless(name = "FileSystemEJB")
@Local(FileSystemLocal.class)
@Component
public class FileSystemBean {
    private final String uploadDirectory = System.getProperty("user.dir")+"\\uploads\\images";

    public String savePicture(MultipartFile file, int id){
        String[] arr = file.getOriginalFilename().split("[.]");
        String time = Long.toString(System.currentTimeMillis());
        String name = arr[0] + time + "." + arr[1];
        Path fileNameAndPath = Paths.get(uploadDirectory, name);
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
        return new File(uploadDirectory + "\\" + name);
    }

}
