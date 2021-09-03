/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.telnetchatserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juandre
 */
public class ChatServer {
    private ServerSocket serverSocket;
    private List<PrintWriter> allUsers;
    
    public ChatServer(){
        try{
            serverSocket = new ServerSocket(6969);
            allUsers = new ArrayList<>();
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public void runMe(){
        while(true){
            try{
                System.out.println("Listening mode...>>>");
                new Thread(new ChatThread(serverSocket.accept(), allUsers)).start();
            }catch(IOException ex){
                System.err.println(ex.getMessage());
                break;
            }
        }
        if(serverSocket != null){
            try{
                serverSocket.close();
            }catch(IOException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        new ChatServer().runMe();
    }
}
