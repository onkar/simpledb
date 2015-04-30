package main.commands.parser;

import main.commands.CommandType;
import main.commands.impl.Command;
import main.commands.impl.CommandBegin;
import main.commands.impl.CommandCommit;
import main.commands.impl.CommandEnd;
import main.commands.impl.CommandGet;
import main.commands.impl.CommandInvalid;
import main.commands.impl.CommandNumEqualTo;
import main.commands.impl.CommandRollback;
import main.commands.impl.CommandSet;
import main.commands.impl.CommandUnset;

public class CommandParser implements Parser {
  public static final String delimiter = " ";

  public Command getCommand(String cmd) {
    if (cmd == null)
      return new CommandInvalid("Command does not exist");
    cmd = cmd.trim().toLowerCase();
    CommandType command;
    String[] args = new String[0];
    if (cmd.contains(delimiter)) {
      int spacePosition = cmd.indexOf(delimiter);
      String type = cmd.substring(0, spacePosition);
      command = CommandType.commandTypeFromString(type);
      String arguments = cmd.substring(spacePosition + 1);
      args = arguments.trim().split(" ");
    } else {
      command = CommandType.commandTypeFromString(cmd);
    }

    if (command != null) {
      switch (command) {
        case SET:
          if (args.length == 2)
            return new CommandSet(args[0], args[1]);
          return new CommandInvalid("Incorrect number of arguments");
        case GET:
          if (args.length == 1)
            return new CommandGet(args[0]);
          return new CommandInvalid("Incorrect number of arguments");
        case UNSET:
          if (args.length == 1)
            return new CommandUnset(args[0]);
          return new CommandInvalid("Incorrect number of arguments");
        case NUMEQUALTO:
          if (args.length == 1)
            return new CommandNumEqualTo(args[0]);
          return new CommandInvalid("Incorrect number of arguments");
        case END:
          return new CommandEnd();
        case BEGIN:
          return new CommandBegin();
        case COMMIT:
          return new CommandCommit();
        case ROLLBACK:
          return new CommandRollback();
        default:
          break;
      }
    }

    return new CommandInvalid("Command does not exist!");
  }
}
