package pe.edu.upc.App.vulnerable.Controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    private final String basePath = "D:/Documentos/Documentos personales/ESTUDIOS/UPC/Cursos/TERCER CICLO/MODULO B/Ethical Hacking/PROYECTO/Archivos-normales";

    @PostMapping(value = "/files", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Resource> getFile(@RequestBody String filename) {
        Path path = Paths.get(basePath, filename).toAbsolutePath();
        System.out.println("Requested Path: "+path.toString());
        Resource resource = new FileSystemResource(path);
        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok().body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
