package com.thecop.rpgtest.mech.screen;

/**
 * Created by TheCop on 26.03.2015.
 */
public enum Command {
    ATTACK("a"),SPELL("s"),RUN("r"),BACK("q");

    private String inputString;

    Command(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }

    public static Command getCommand(String input){
        if(input==null)return null;
        for (Command command : Command.values()) {
            if(input.equalsIgnoreCase(command.getInputString())){
                return command;
            }
        }
        return null;
    }
}
