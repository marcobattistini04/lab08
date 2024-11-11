package it.unibo.deathnote.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{
    private final static int MINIMUM_RULE = 1;
    public final static String DEFAULT_DEATH_CAUSE = "heart attack";
    private final static long SHORT_WAITING_TIME = 40L;
    private final static long LONG_WAITING_TIME = 6040L;
    private long Time;
    private String lastNameWritten;
    List<String> humans = new LinkedList<>();
    Map<String, String> deathCause = new HashMap<>();
    Map<String, String> deathDetails = new HashMap<>();
    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < MINIMUM_RULE || ruleNumber > DeathNote.RULES.size()) {
            throw new IllegalArgumentException("Invalid rule number " + ruleNumber);
        }
        return DeathNote.RULES.get(ruleNumber);
    }

    @Override
    public void writeName(String name) {
        if(name == null) {
            throw new NullPointerException("Name passed is null");
        }
        humans.add(name);
        this.lastNameWritten = name;
        Time = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(this.humans.isEmpty()) {
            throw new IllegalStateException("THERE IS NO NAME WRITTEN IN DEATHNOTE");
        }
        if(cause == null) {
            throw new IllegalStateException("the cause given is null");
        }
        Time = System.currentTimeMillis() - Time;
        if(Time <= SHORT_WAITING_TIME) {
            this.deathCause.put(lastNameWritten, cause);
            Time = System.currentTimeMillis();
            return true;
        }

        return false;
    }

    @Override
    public boolean writeDetails(String details) {
        if(this.humans.isEmpty()) {
            throw new IllegalStateException("THERE IS NO NAME WRITTEN IN DEATHNOTE");
        }
        if(details == null) {
            throw new IllegalStateException("the details given are null");
        }
        Time = System.currentTimeMillis() - Time;
        if(Time <= LONG_WAITING_TIME) {
            this.deathDetails.put(this.lastNameWritten, details);
            return true;
        }
        return false;
    }

    @Override
    public String getDeathCause(String name) {
        if(! this.humans.contains(name)) {
            throw new IllegalArgumentException(name + "isn't written in DeathNote(for now ;)");
        }

        if(this.deathCause.containsKey(name)) {
            return this.deathCause.get(name);
        } else {
            return DEFAULT_DEATH_CAUSE;
        }       
        
    }

    @Override
    public String getDeathDetails(String name) {
        if(! this.humans.contains(name)) {
            throw new IllegalArgumentException(name + "isn't written in DeathNote(for now ;)");
        }

        if(this.deathDetails.containsKey(name)) {
            return this.deathDetails.get(name);
        } else {
            return "";
        }
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.humans.contains(name);
    }
    
}
