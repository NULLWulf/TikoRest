/*
Authors:
Matthew Brown
Nathan Wolf
 */

package com.tikojar.tikorest.DAO;

import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDate;

public class Message {

    private String userID;
    private String datePosted;
    private String messageContent;
    private String messageId;

    public Message(){}  // Empty Constructor necessary for Jackson

    // Primary Constructor for Messages, called when addMessage is called
    public Message(String userID, String messageContent) {
        this.userID = userID;
        this.datePosted = LocalDate.now().toString(); // Gets server time and conver to String for easy database store
        this.messageContent = messageContent;
        this.messageId = RandomStringUtils.randomAlphanumeric(10);  // Unique message id to perform things like deletes etc.
    }

    public Message(String userID, String messageContent, String messageId) {
        this.userID = userID;
        this.datePosted = LocalDate.now().toString(); // Gets server time and conver to String for easy database store
        this.messageContent = messageContent;
        this.messageId = messageId;  // Unique message id to perform things like deletes etc.
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageId() { return this.messageId;}

    public void setMessageId(String messageId) { this.messageId = messageId;}



    @Override
    public String toString() {
        return "Message{" +
                "userID='" + userID + '\'' +
                ", datePosted='" + datePosted + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
