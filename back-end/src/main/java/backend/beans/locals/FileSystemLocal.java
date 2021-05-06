package backend.beans.locals;

import org.springframework.web.multipart.MultipartFile;

import javax.ejb.Local;
import java.io.File;

@Local
public interface FileSystemLocal {
    String savePicture(MultipartFile file, int id);
    File getImage(String name);
}
