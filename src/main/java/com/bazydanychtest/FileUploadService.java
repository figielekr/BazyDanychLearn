package com.bazydanychtest;

import com.bazydanychtest.user.tables.User;
import com.bazydanychtest.user.tables.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGeneratorFactory;

@Service
public class FileUploadService {
    /*public void uploadFile(MultipartFile multipartFile) throws IOException {
        multipartFile.transferTo(new File("C:\\web projects\\java first\\bazydanychsecurity\\" + multipartFile.getOriginalFilename()));
    }*/


    public String uploadImage(MultipartFile multipartFile) throws IOException {


        String fileName = multipartFile.getOriginalFilename();
        //Path uploadDirectory = Paths.get("src/main/resources/static/uploads/");
        final String uploadLocation = getClass().getClassLoader().getResource("static/uploads").toString();
        //we should get rid of file:/, hence the substring
        final Path uploadDirectory = Paths.get(uploadLocation.substring(6, uploadLocation.length()) );
        //Path uploadDirectory = Paths.get("target\\classes\\static\\uploads\\");


        try (InputStream inputStream = multipartFile.getInputStream()) {
            String path = UUID.randomUUID()+ "-" + fileName;
            Path filePath = uploadDirectory.resolve(path);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (IOException ioException){
            throw new IOException("Error saving uploaded file: " + fileName, ioException);
        }
    }
}











    /*@Autowired
    UserRepository repo;

    public void  uploadImage(*//*String fileName, MultipartFile multipartFile) throws IOException {


        Optional<User> user = repo.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (repo.existsByUserName(SecurityContextHolder.getContext().getAuthentication().getName())){
            String fileName = multipartFile.getOriginalFilename();
            Path uploadDirectory = Paths.get("src/main/resources/uploads");
            try (InputStream inputStream = multipartFile.getInputStream()) {
                String path = UUID.randomUUID()+ "-" + fileName;
                Path filePath = uploadDirectory.resolve(path);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                user.get().setPath(path);
                repo.save(user.get());
            } catch (IOException ioException){
                throw new IOException("Error saving uploaded file: " + fileName, ioException);
            }
        } else {
            System.out.println("Nie znaleziono uzytkownika.");
        }
    }*/




/*
    String path = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
    String path = Paths.get("files-upload");
    Path path = Paths.get("files-upload");
        multipartFile.transferTo(new File(path + multipartFile.getOriginalFilename()));  drugi sposob
                System.out.println(path);
                user.get().setPath(path);
                repo.save(user.get());*/


    //generowanie alphanumeric randomstringutils, ale trzeba dependency common lang 3