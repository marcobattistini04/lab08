package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    @Override
    public String getRule(int ruleNumber) {
        throw new IllegalArgumentException("Invalid rule number " + ruleNumber);
    }

    @Override
    public void writeName(String name) {
        throw new NullPointerException("Name passed is null");
    }

    @Override
    public boolean writeDeathCause(String cause) {
        throw new IllegalStateException("THERE IS NO NAME WRITTEN IN DEATHNOTE (or the cause given is null)");
    }

    @Override
    public boolean writeDetails(String details) {
        throw new IllegalStateException("THERE IS NO NAME WRITTEN IN DEATHNOTE (or the details given are null)");
    }

    @Override
    public String getDeathCause(String name) {
        throw new IllegalArgumentException(name + "isn't written in DeathNote(for now ;)");
    }

    @Override
    public String getDeathDetails(String name) {
        throw new IllegalArgumentException(name + "isn't written in DeathNote(for now ;)");
    }

    @Override
    public boolean isNameWritten(String name) {
        throw new IllegalStateException("this method must return an Exception");
    }
    
}
