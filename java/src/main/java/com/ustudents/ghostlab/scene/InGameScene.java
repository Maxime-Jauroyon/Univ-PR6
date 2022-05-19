package com.ustudents.ghostlab.scene;

import java.io.IOException;

import com.ustudents.application.graphic.ImGuiManager;
import com.ustudents.ghostlab.client.Client;
import com.ustudents.ghostlab.other.Player;
import com.ustudents.ghostlab.other.Utils;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;

public class InGameScene extends Scene{
    
    public InGameScene(Client client){
        super(client);
    }

    /*public void inGameOption() throws IOException{
        ImGui.setNextWindowPos(0, 0);
        ImGui.setNextWindowSize(ImGui.getIO().getDisplaySizeX(), ImGui.getIO().getDisplaySizeY() * 0.6f);
        ImGui.begin("Ghostlab Client", ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.MenuBar);
        ImGui.pushFont(ImGuiManager.firaCode);

        char[][] maze = client.getGameModel().getMaze();
        for(int i = 0; i < maze.length; i++){
            String line = "";
            for(int j = 0; j < maze[i].length; j++){
                line += maze[i][j];
            }
            if(i > 0){
                ImGui.setCursorPosY(ImGui.getCursorPosY() - 6);
            }
            ImGui.textUnformatted(line);
        }

        ImGui.popFont();

        if(ImGui.button("Move")){
            client.setLastPressedButton(SceneData.BUTTON_MAZEMOVE);
            client.setCurrentScene(SceneData.SCENE_USERCHOICE);
        }

        ImGui.sameLine();
        
        if(ImGui.button("Player list")){
            client.getSender().send("GLIS?***");
        }

        ImGui.sameLine();
        
        if(ImGui.button("Send Everyone Message")){
            client.setLastPressedButton(SceneData.BUTTON_ALLMESSAGE);
            client.setCurrentScene(SceneData.SCENE_USERCHOICE);
        }

        ImGui.sameLine();

        if(ImGui.button("Send Private Message")){
            client.setLastPressedButton(SceneData.BUTTON_PRIVATEMESSAGE);
            client.setCurrentScene(SceneData.SCENE_USERCHOICE);
        }

        ImGui.sameLine();

        if(ImGui.button("Quit Game")){
            client.getSender().send("IQUIT***");
        }

        ImGui.end();
    
    }*/  


    public void inGameOption() throws IOException{
        ImGui.setNextWindowPos(0, 0);
        ImGui.setNextWindowSize(ImGui.getIO().getDisplaySizeX(), ImGui.getIO().getDisplaySizeY() * 0.6f);
        ImGui.begin("Ghostlab Client", ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.MenuBar);
        
        if(ImGui.button("Send Everyone Message")){
            client.setLastPressedButton(SceneData.BUTTON_ALLMESSAGE);
            client.setCurrentScene(SceneData.SCENE_USERCHOICE);
        }

        ImGui.sameLine();

        if(ImGui.button("Send Private Message")){
            client.setLastPressedButton(SceneData.BUTTON_PRIVATEMESSAGE);
            client.setCurrentScene(SceneData.SCENE_USERCHOICE);
        }

        ImGui.sameLine();

        if(ImGui.button("Quit Game")){
            client.getSender().send("IQUIT***");
        }

        if(ImGui.collapsingHeader("Map")){
            ImGui.pushFont(ImGuiManager.firaCode);
            char[][] maze = client.getGameModel().getMaze();
            for(int i = 0;  maze != null && i < maze.length; i++){
                String line = "";
                for(int j = 0; j < maze[i].length; j++){
                    line += maze[i][j];
                }
                if(i > 0){
                    ImGui.setCursorPosY(ImGui.getCursorPosY() - 6);
                }
                ImGui.textUnformatted(line);
            }

            ImGui.popFont();
        }

        if(ImGui.collapsingHeader("Move")){
            ImGui.text(Utils.makeSpace(12));
            ImGui.sameLine();
            if(ImGui.button("Up")){
                client.setLastPressedButton(SceneData.BUTTON_MAZEMOVE_TOWARD_UP);
                client.setCurrentScene(SceneData.SCENE_USERCHOICE);
            }
            if(ImGui.button("Left")){
                client.setLastPressedButton(SceneData.BUTTON_MAZEMOVE_TOWARD_LEFT);
                client.setCurrentScene(SceneData.SCENE_USERCHOICE);
            }
            ImGui.sameLine();
            ImGui.text(Utils.makeSpace(10));
            ImGui.sameLine();
            if(ImGui.button("Right")){
                client.setLastPressedButton(SceneData.BUTTON_MAZEMOVE_TOWARD_RIGHT); 
                client.setCurrentScene(SceneData.SCENE_USERCHOICE);    
            }
            ImGui.text(Utils.makeSpace(9));
            ImGui.sameLine();
            if(ImGui.button("Down")){
                client.setLastPressedButton(SceneData.BUTTON_MAZEMOVE_TOWARD_DOWN);
                client.setCurrentScene(SceneData.SCENE_USERCHOICE);
            }
        }    
        
        if(ImGui.collapsingHeader("Players")){
            if(ImGui.button("Reload Players Data")){
                client.getSender().send("GLIS?***");
            } 

            for(Player player : client.getGameModel().players()){
                if(ImGui.collapsingHeader(player.getUsername())){
                    ImGui.text("- Position x : " + player.getPosX());
                    ImGui.text("- Position y : " + player.getPosY());
                    ImGui.text("- Score : " + player.getScore());
                }
                
            }
            
        }

        ImGui.end();


    }

    public void display() throws IOException{ 
        inGameOption();
        mainContainer();
        
    }
}
