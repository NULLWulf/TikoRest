package com.tikojar.tikorest;

import com.tikojar.tikorest.DAO.Jar;
import com.tikojar.tikorest.DAO.Message;
import com.tikojar.tikorest.Repo.JarRepo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.List;

@SpringBootApplication
public class TikoRestApplication implements CommandLineRunner  {

    @Autowired
    JarRepo jarRepo;

    public static void main(String[] args) {
        SpringApplication.run(TikoRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Test");
        for (Jar jar : jarRepo.findAll()) {
            System.out.println(jar.toString());
        }

        List<Jar> jarList = jarRepo.findAll();
        for(Jar jar : jarList){
            for (Message message : jar.getMessages()){
                System.out.println(message.getMessageContent());
            }
        }

    }
}
