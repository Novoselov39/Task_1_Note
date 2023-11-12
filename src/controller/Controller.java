package controller;


import java.util.Scanner;
import data.Note;
import service.NoteService;
import view.NoteView;

public class Controller {
    NoteService noteService = new NoteService();
    NoteView view = new NoteView();

    public void createNote(String nameNote, String body){
        noteService.createNewNote(nameNote,body);
    }

    public void renameNote(int id, String NewNameNote){
        
        noteService.renameNote(id, NewNameNote);
    }

    public void changeNoteBody(int id, String newBody){

        noteService.changeNoteBody(id, newBody);       
        
    }



    public void removeAllNote(){

       noteService.removeAllNote();

    }

    public void getNoteById(int Id){
      
        
        view.printOnConsole(noteService.readNoteById(Id));
            
             
     

    }

    public void getNoteByNameNote(String nameNote){       
       
        
        for (Note Note : noteService.readNoteByNameNote(nameNote)) {
            view.printOnConsole(Note);
            
        } 
       
    }

    public void removeNoteById(int id){
        noteService.removeNote(id);
    }

    public void getAllNote(){
        noteService.searchNote();
        for (Note note : noteService.searchNote()) {
            view.printOnConsole(note);                    
        }
    }

    public void removeNoteByNameNote(String nameNote){

        int counter = 0; 
        Integer id= null;       
        for (Note note : noteService.readNoteByNameNote(nameNote)) {
            if (note.getNameNote().equals(nameNote)){
                counter++;
                id = note.getId();
            }
        }

        if (counter!=1){
            if (counter==0){
                getAllNote();
            }else{
                getNoteByNameNote(nameNote);
            }
           
            Scanner in = new Scanner(System.in);
            System.out.println("Введи id  для удаления");
            id = in.nextInt();
            in.close();
            counter=1;

        }
        
        if (counter==1){
            removeNoteById(id);
        }
        
    }


}
