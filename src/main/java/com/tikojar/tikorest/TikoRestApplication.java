package com.tikojar.tikorest;

import com.tikojar.tikorest.DAO.Jar;
import com.tikojar.tikorest.DAO.Message;
import com.tikojar.tikorest.DAO.OpeningCondition;
import com.tikojar.tikorest.Repo.JarRepo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TikoRestApplication implements CommandLineRunner  {

//    COMMANDPREFIX("!tiko"),
//    ADDMESSAGE("add"),
//    CREATEJAR("create"),
//    DELETEJAR("delete jar"),
//    DELETEMESSAGE("delete message"),
//    HELLO("hello"),
//    HELP("help"),
//    MESSAGELIMIT("m"),
//    TIMELIMIT("t"),
//    VIEWMESSAGES("view messages");

    @Autowired
    JarRepo jarRepo;

    public static void main(String[] args) {
        SpringApplication.run(TikoRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Jar jar1 = new Jar("12345",new OpeningCondition(true,5,0,"12345"));
        Jar jar2 = new Jar("12345",new OpeningCondition(true,5,0,"12345"));
        Jar jar3 = new Jar("12345",new OpeningCondition(true,5,0,"12345"));
        jar1.addMessage(new Message("12345","12345","ABC"));
        System.out.println(jar1.toString());
        jar1.removeMessageById("ABC");
        System.out.println(jar1.toString());

        printBreak();
        System.out.println("Deleting all Jars from Test Jars Repo");
        jarRepo.deleteAll();  // Clears Repo


        String[] serverIdArray = new String[]{"ABC0","ABC1","ABC2","ABC3","ABC4"};  // Generic Server Id and Channel ID names

        printBreak();
        System.out.println("Posting Generic Jars to DB");
        for (String s : serverIdArray) {
            jarRepo.save(new Jar(s, new OpeningCondition(true, 5, 0, s)));
        }

        printBreak();
        System.out.println("Retrieving Generic Jars from DB");
        List<Jar> jarsByServerId = new ArrayList<Jar>();
        for (String s : serverIdArray) {
            jarsByServerId.add(jarRepo.findByServerID(s));
        }

        printBreak();
        System.out.println("Iterating Over Retrieved Jars by Server ID");
        jarsByServerId.forEach((j)->{
            System.out.println(j.getServerID());
        });

        printBreak();
        System.out.println("Add Message to Jar and Save");
        jarsByServerId.forEach((j)->{
            j.addMessage(new Message(j.getServerID(),j.getServerID()));
            jarRepo.save(j);
        });

        printBreak();
        System.out.println("Retrieving Generic Jars from DB with New Message");
        List<Jar> jarsByServerId2 = new ArrayList<Jar>();
        for (String s : serverIdArray) {
            jarsByServerId2.add(jarRepo.findByServerID(s));
        }

        printBreak();
        System.out.println("Iterating over Jars with new Message");
        jarsByServerId2.forEach((jm)->System.out.println(jm.toString()));

        List<String> messageId = new ArrayList<>();
        jarsByServerId2.forEach(
                jar -> {
                    jar.getMessages().forEach(m->messageId.add(m.getMessageId()));
                }
        );

        printBreak();
        System.out.println("List of message IDs");
        messageId.forEach(System.out::println);

        printBreak();
        System.out.println("Delete Message by MessageID (which is just serverId)");

        for (String value : messageId) {
            for (Jar jar : jarsByServerId2) {
                jar.removeMessageById(value);
            }
        }

        jarsByServerId2.forEach((jm)->System.out.println(jm.toString()));

        printBreak();
        System.out.println("Saving all jars");
        jarRepo.saveAll(jarsByServerId2);

        printBreak();
        System.out.println("Retrieving Generic Jars from DB with New Message");
        List<Jar> jarsByServerId3 = new ArrayList<Jar>();
        for (String s : serverIdArray) {
            jarsByServerId3.add(jarRepo.findByServerID(s));
        }

        printBreak();
        System.out.println("Iterating over Jars with Deleted  Message");
        jarsByServerId3.forEach((jm)->System.out.println(jm.toString()));

        printBreak();
        System.out.println("Deleting All Jars");
        jarRepo.deleteAll();
    }

    private void printBreak() {
        System.out.println("----------------------------------------");
    }

}
