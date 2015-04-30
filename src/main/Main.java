package main;

import main.commands.parser.CommandParser;
import main.commands.parser.Parser;
import main.input.Reader;
import main.input.StdinReader;

public class Main {
  public static void main(String[] args) {
    System.out.println("Please start entering database commands!");
    Reader reader = new StdinReader();
    Parser parser = new CommandParser();
    CommandExecutor executor = new CommandExecutor(parser);

    while (true) {
      String command = reader.readCommand();
      executor.executeCommand(command);
    }
  }
}
