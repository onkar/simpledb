package main.commands.parser;

import main.commands.impl.Command;

/**
 * Interface that all command line parsers should implement
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public interface Parser {
  public Command getCommand(String cmd);
}
