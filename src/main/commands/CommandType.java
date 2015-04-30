package main.commands;

public enum CommandType {
  SET("set"), UNSET("unset"), GET("get"), NUMEQUALTO("numequalto"), END("end"), BEGIN("begin"), COMMIT(
      "commit"), ROLLBACK("rollback"), INVALID("invalid");

  private String command;

  private CommandType(String command) {
    this.command = command;
  }

  public String getCommand() {
    return this.command;
  }

  public static CommandType commandTypeFromString(String type) {
    for (CommandType c : CommandType.values()) {
      if (c.getCommand().equals(type)) {
        return c;
      }
    }
    return null;
  }
}
