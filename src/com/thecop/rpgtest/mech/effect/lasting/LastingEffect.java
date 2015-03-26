package com.thecop.rpgtest.mech.effect.lasting;

import com.thecop.rpgtest.mech.effect.Effect;

/**
 * Created by TheCop on 25.03.2015.
 */
public abstract class LastingEffect extends Effect {
    protected int length;
    protected String description;



    public LastingEffect(String name, int length, String description) {
        super(name);
        this.length = length;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public void tick(){
        if(length==-1){
            return;
        }
        length=length-1;
    }

    public boolean ended(){
        return length==0;
    }

    public String getDescription() {
        return description;
    }
}
