
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import controller.Controller;


public class Program {
    public static void main(String[] args) throws IOException {
        help();

        Controller controller = new Controller();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       
      
        while (true) {

            String input;
            String exit = "EXIT";

            input = in.readLine();
            if (exit.equals(input.toUpperCase())) break;          


            switch (input.toUpperCase()) {
                case "ADD":
                    System.out.println("Введи новое имя заметки");
                    String name = in.readLine();
                    System.out.println("Введи новый текст заметки");
                    String body = in.readLine();
                    controller.createNote(name, body);
                    break;

                case "DEL":
                    System.out.println("Введи id заметки");
                    int id = Integer.parseInt(in.readLine());
                    controller.removeNoteById(id);
                    break;

                case "RENAME":
                    System.out.println("Введи id заметки");
                    id = Integer.parseInt(in.readLine());
                    System.out.println("Введи новое имя заметки");
                    name = in.readLine();
                    controller.renameNote(id,name);
                    
                    break;
            
                case "CHANGE":
                    System.out.println("Введи id заметки");
                    id = Integer.parseInt(in.readLine());
                    System.out.println("Введи новый текст заметки");
                    body = in.readLine();
                    controller.changeNoteBody(id, body);
                    break;
                
                case "GETBYID":
                    System.out.println("Введи id заметки");
                    id = Integer.parseInt(in.readLine());
                    controller.getNoteById(id);
                    break;

                case "GETBYNAME":
                    System.out.println("Введи имя заметки");
                    name = in.readLine();
                    controller.getNoteByNameNote(name);
                    break;

                case "GETALL":
                    controller.getAllNote();
                    break;

                case "HELP":
                    help();
                    break;

                case "DELLALL":
                    controller.removeAllNote();
                    break;
                

                default:
                    System.out.println("Команда не распознана");
                    break;

                
            }
           
        }
        


    }


    public static void help(){
        System.out.println("----------------------------------------------");
        System.out.println("Команды:");
        System.out.println("add - добавить заиметку");
        System.out.println("del - удалить заметку");
        System.out.println("rename - переименовать заметку");
        System.out.println("change - изменить заметку");
        System.out.println("getById - найти заметку по id");
        System.out.println("getByName - найти заметку по имени");
        System.out.println("getAll - вывести все заметки");
        System.out.println("help - вывести команды");
        System.out.println("exit - выход");
        System.out.println("dellAll - удалить все");
        System.out.println("----------------------------------------------");
    }
}
