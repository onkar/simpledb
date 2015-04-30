package main;

import main.commands.impl.Command;
import main.commands.parser.Parser;
import main.core.Container;

/**
 * Container is passed to every command so that we can support new commands as and when needed
 * without changing the command execution code.
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
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
