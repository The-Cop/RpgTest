package com.thecop.rpgtest.mech.screen;

/**
 * Created by TheCop on 26.03.2015.
 */
public enum FightCommand {
    ATTACK("a"),SPELL("s"),RUN("r"),BACK("q");

    private String inputString;

    FightCommand(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }

    public static FightCommand getCommand(String input){
        if(input==null)return null;
        for (FightCommand command : FightCommand.values()) {
            if(input.equalsIgnoreCase(command.getInputString())){
                return command;
            }
        }
        return null;
    }
}
