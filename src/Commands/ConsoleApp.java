package Commands;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleApp {
        Scanner scanner = new Scanner(System.in);
        private HashMap<String, Command> commands;
        private boolean isExit;


        public ConsoleApp(){
            this.scanner=new Scanner(System.in);
            this.commands=new HashMap<>();
            this.isExit=false;

        }
        public void inicialization(){
        //TODO commandy
        }
        public void execute(){
            System.out.println((">>"));
            String command = scanner.next();
            command=command.trim().toLowerCase();
            if(commands.containsKey(command)){
                System.out.println(commands.get(command).execute());
                isExit=commands.get(command).exit();
            }else{
                System.out.println("wrong command");
            }

        }
        public void start(){
            inicialization();
            do{
                execute();
            }while(!isExit);
        }
    }


