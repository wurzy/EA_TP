package backend.beans.locals;

import backend.dao.Resources;
import org.springframework.web.multipart.MultipartFile;

import javax.ejb.Local;
import java.io.File;
import java.util.List;

@Local
public interface FileSystemLocal {
    backend.dao.Files[] saveFiles(MultipartFile[] files, Resources r);
    String savePicture(MultipartFile file, int id);
    File getImage(String name);
}
