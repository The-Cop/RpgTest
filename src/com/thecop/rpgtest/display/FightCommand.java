package com.thecop.rpgtest.display;

/**
 * Created by TheCop on 26.03.2015.
 */
public enum FightCommand {
    ATTACK("A","Usual attack"),SPELL("S", "Cast a spell"),RUN("R","Run away"),BACK("Q","Back to the previous menu");

    private String inputString;
    private String description;


    FightCommand(String inputString, String description) {
        this.inputString = inputString;
        this.description = description;
    }

    public String getInputString() {
        return inputString;
    }

    public String getDescription() {
        return description;
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
