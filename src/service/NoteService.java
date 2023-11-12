package service;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import data.Note;

public class NoteService {

    
    public static void createNewNote(String nameNote, String body) {
        Date time = new Date();                       
        Note note = new Note(getId(), nameNote, body, time); 
        writeNote(note);
        

        
    }


    public static void removeNote(int id){

        for (Note note : searchNote()) {
            if (note.getId()==id){
                File file = new File("src\\Json\\note\\"+id+".json");
                file.delete();
                
            }
            
        }
        

    }

    public static void removeAllNote(){

        for (Note note : searchNote()) {
            
            File file = new File("src\\Json\\note\\"+note.getId()+".json");
            file.delete();               
           
            
        }
        String fileNameId = "src\\Json\\id.json";        
        JSONObject idJsonWrite = new JSONObject();
        idJsonWrite.put("id", Integer.toString(0));
        jsonWrite(fileNameId,idJsonWrite);
        

    }

    public void renameNote(int id, String NewNameNote){

        Note note = readNoteById(id);
        removeNote(id);
        Date time = new Date();
        Note renameNote = new Note(note.getId(), NewNameNote, note.getBody() , time);
        writeNote(renameNote);
    }

    public void changeNoteBody(int id, String newBody){
        Note note = readNoteById(id);
        removeNote(id);
        Date time = new Date();
        Note renameNote = new Note(note.getId(), note.getNameNote(), newBody , time);
        writeNote(renameNote);
        
    }


    public static List<Note> searchNote(){
       List<Note> noteList = new ArrayList<Note>(); 
       int id;
       String nameNote;
       String body;
       Date time;
       File folder = new File("src\\Json\\note\\");
        File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    JSONObject jsonObject = jsonRead(file.getPath());
                    id = Integer.parseInt(jsonObject.get("id").toString());
                    nameNote=jsonObject.get("nameNote").toString();
                    body=jsonObject.get("body").toString();
                    time=new Date(Long.parseLong(jsonObject.get("timeL").toString()));
                    
                    Note note = new Note(id, nameNote, body, time);

                    noteList.add(note);        
                    
                }
            }
        return noteList;
    }

    public static List<Note> readNoteByNameNote(String nameNote){
        List<Note> noteList = searchNote();
        List<Note> noteListgenerate = new ArrayList<>();
        for (Note Note : noteList) {
            if (Note.getNameNote().equals(nameNote)){
                noteListgenerate.add(Note);
            }
        }
        return noteListgenerate;
              
            
    }
  
    public static Note readNoteById(int id){
        List<Note> noteList = searchNote();        
        for (Note note : noteList) {                    
            int idRead = note.getId();
            if (idRead==id){
                return note;
            }
        }
        return null;       
             
    }

    private static void writeNote(Note note){
        JSONObject noteOject = new JSONObject();
        String nameFileNote = "src\\Json\\note\\"+note.getId()+".json";        
        noteOject.put("id", note.getId());
        noteOject.put("nameNote", note.getNameNote());
        noteOject.put("body", note.getBody());
        noteOject.put("time", note.getTime().toString());
        noteOject.put("timeL", Long.toString(note.getTime().getTime()));
        

        try(FileWriter writer = new FileWriter(nameFileNote)) {
                writer.write(noteOject.toString());
            } catch (IOException e) {
            System.out.println("Id");
            }             

    }
    
    private static JSONObject jsonRead(String fileName){
        
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(fileName)){
            JSONObject json =(JSONObject) parser.parse(reader);            
            return json;                 
                              
        }
        catch (IOException e) {
            System.out.println("ошибка получения Id");
            return null;
            
        }
        catch (ParseException e){
            System.out.println("ошибка парсинга");
            return null;
        }
        catch (Exception e) {
            System.out.println("ошибка");
            return null;
        }
        
    }

    private static void jsonWrite(String fileName, JSONObject jsonObject){
        try(FileWriter writer = new FileWriter(fileName)) {
                writer.write(jsonObject.toString());
            } catch (IOException e) {
            System.out.println("Id");
            } 
    }
            


    public static Integer getId() {
        String fileNameId = "src\\Json\\id.json";      

        int id = Integer.parseInt((String) jsonRead(fileNameId).get("id"));
        JSONObject idJsonWrite = new JSONObject();
        idJsonWrite.put("id", Integer.toString(id+1));
        jsonWrite(fileNameId,idJsonWrite);
        return ++id;
               
        
    }
    
}