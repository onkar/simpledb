package main;

import main.commands.impl.Command;
import main.commands.parser.Parser;
import main.core.Container;

public class CommandExecutor {
  private Parser parser;
  private Container container;

  public CommandExecutor(Parser parser) {
    this.parser = parser;
    this.container = new Container();
  }

  public void executeCommand(String command) {
    Command internalCommand = parser.getCommand(command);
    internalCommand.execute(container);
  }
}
