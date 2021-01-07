package part1.cliPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.cli.*;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailServicePack.MailService;

import javax.crypto.NoSuchPaddingException;

/**
 * CLIMain to Test the CLI
 * @author Usama Benabdelkrim Zakan
 */
public class CLIMain {
    public static void main(String[] args) {
        CLI cli=new CLI(new MailService());
        cli.getMailService().createMailStore();
        try {
            cli.getMailService().deSerialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommandLineParser parser = new GnuParser();
        Options options = createOptions();

        String[] firstOption;
        String[] secondOption;
        try {
            CommandLine commandLine = parser.parse(options, args);
            if(commandLine.hasOption("h")){		//Prints options description
                helpOption(options);
            }
            if (commandLine.hasOption("c") && commandLine.hasOption("l")) {		//Create user or login, both no
                System.out.println("Only one command");
            }
            else {
                if(!commandLine.hasOption("c") && !commandLine.hasOption("l")) {		//Enter or create user command or login
                    System.out.println("Please, enter a command");
                }
                else {
                    if(commandLine.hasOption("c")) {		//Create user command
                        firstOption=commandLine.getOptionValues("c");
                        createUserOption(cli, firstOption);
                    }
                    else {
                        if(commandLine.hasOption("l")) {		//Log in command
                            firstOption=commandLine.getOptionValues("l");
                            if(cli.logas(firstOption[0])==null){		//user not found
                                userNotFound();
                            }
                            else{
                                System.out.println("Welcome "+cli.getMailBox().getUser().getUsername()+"!");		//user found
                            }
                            if(commandLine.hasOption("se")){		//send option
                                secondOption=commandLine.getOptionValues("se");
                                sendMailOption(cli, secondOption);
                            }
                            if(commandLine.hasOption("u")){		//update option
                                updateOption(cli);
                            }
                            if(commandLine.hasOption("li")){		//list option
                                System.out.println(cli.sortPerDate());
                            }
                            if(commandLine.hasOption("so")){		//sort option
                                secondOption=commandLine.getOptionValues("so");
                                sortMethod(cli, secondOption);
                            }
                            filterOptions(cli, commandLine);		//filter options
                        }
                    }
                }
            }

        } catch (ParseException | java.text.ParseException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        cli.getMailService().serialize();
    }

    private static Options createOptions() {
        Options options = new Options();
        Option option;

        options.addOption("c", "createuser", true, "Sign Up");
        options.addOption("l","logas", true, "Log In");
        options.addOption("se", "send", true, "Send new message (args: subject body receiver)");
        option = Option.builder("u").optionalArg(true).desc("Retrieve messages from the mail store").build();
        options.addOption(option);
        option = Option.builder("li").optionalArg(true).desc("Show messages sorted by sent time").build();
        options.addOption(option);
        options.addOption("so", "sort", true, "Sort messages by some predefined comparators (args: date/sender/receiver)");

        options.addOption("fd", "filterdate", true, "Filter messages for mailbox user per date");
        options.addOption("fn", "filtername", true, "Filter messages for mailbox user per name");
        options.addOption("fun", "filtusername", true, "Filter messages for mailbox user per username");
        options.addOption("fw", "filterword", true, "Filter messages for mailbox user per word");
        options.addOption("fnw", "filternwords", true, "Filter messages for mailbox user per number of words");
        options.addOption("fs", "filtersubject", true, "Filter messages for mailbox user per subject");
        options.addOption("fwn", "filterwordnw", true, "Filter messages for mailbox user per word and number of messages (args: word nWords)");
        options.addOption("h", "help", false, "Help");
        return options;
    }

    private static void updateOption(CLI cli) throws NoSuchPaddingException, NoSuchAlgorithmException {
        cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
        List<Message> messageList=cli.getMailBox().getMailstore().getMessagesList();
        if(messageList!=null && messageList.size()>0){
            System.out.println(cli.getMailBox().getMailstore().getMessagesList());
        }
        else{
            System.out.println("No s'ha trobat cap missatge :(");
        }
    }

    private static void sendMailOption(CLI cli, String[] secondOption) throws java.text.ParseException {
        cli.sendMail(new Message(secondOption[0], secondOption[1], cli.getMailBox().getUser(), cli.getMailService().searchMailBoxUser(secondOption[2]).getUser()));
        System.out.println("Message sent to "+ secondOption[2]);
    }

    private static void userNotFound() {
        System.out.println("User not found. Sign Up!");
        System.out.print("Exiting...");
        System.exit(0);
    }

    private static void createUserOption(CLI cli, String[] firstOption){
        cli.createUser(new User(firstOption[0], firstOption[1], Integer.parseInt(firstOption[2])));
        cli.getMailService().createMailStore();
    }

    private static void helpOption(Options options) {
        for(Option o : options.getOptions()){
            System.out.println(o.getOpt()+": "+o.getDescription());
        }
    }

    private static void filterOptions(CLI cli, CommandLine commandLine) throws NoSuchPaddingException, NoSuchAlgorithmException {
        String[] secondOption;
        if(commandLine.hasOption("fd")){
            secondOption = commandLine.getOptionValues("fd");
            cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
            System.out.println(cli.filterPerDate(secondOption[0]));
        }
        if(commandLine.hasOption("fn")){
            secondOption = commandLine.getOptionValues("fn");
            cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
            System.out.println(cli.filterPerName(secondOption[0]));
        }
        if(commandLine.hasOption("fun")){
            secondOption = commandLine.getOptionValues("fun");
            cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
            System.out.println(cli.filterPerUsername(secondOption[0]));
        }
        if(commandLine.hasOption("fw")){
            secondOption = commandLine.getOptionValues("fw");
            cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
            System.out.println(cli.filterPerWord(secondOption[0]));
        }
        if(commandLine.hasOption("fnw")){
            secondOption = commandLine.getOptionValues("fnw");
            cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
            System.out.println(cli.filterPerLessThanNWords(Integer.parseInt(secondOption[0])));
        }
        if(commandLine.hasOption("fs")){
            secondOption = commandLine.getOptionValues("fs");
            cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
            System.out.println(cli.filterPerSubject(secondOption[0]));
        }
        if(commandLine.hasOption("fwn")){
            secondOption = commandLine.getOptionValues("fwn");
            cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
            System.out.println(cli.filterPerWordAndNWord(secondOption[0], Integer.parseInt(secondOption[1])));
        }
    }

    private static void sortMethod(CLI cli, String[] secondOption) throws NoSuchPaddingException, NoSuchAlgorithmException {

        cli.getMailBox().updateMailStore(cli.getMailBox().getUser().getUsername());
        if(secondOption[0].equals("date")){
            System.out.println(cli.sortPerDate());
        }
        if(secondOption[0].equals("receiver")){
            System.out.println(cli.sortPerReceiver());
        }
        if(secondOption[0].equals("sender")){
            System.out.println(cli.sortPerSender());
        }
    }

}
