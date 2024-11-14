package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private final String name = "Mario Rossi";
    private final String name2 = "Franco Bianchi";
    private final String deathCause = "karting accident";
    private final String deathDetails = "ran for too long";

    private static final String EMPTY_STRING = "";
    private static final int RULE = 3;
    private static final int ZERO_RULE = 0;
    private static final int NEGATIVE_RULE = -1;
    private static final long SHORT_SLEEPING_TIME = 100L;
    private static final long LONG_SLEEPING_TIME = 6100L;
    private static final String OK = "OK! Catched correct Exception!";
    private DeathNote dn ;

    @BeforeEach
    public void setUp() {
        this.dn = new DeathNoteImplementation();
    }

    @Test
    public void ConsistenceRule() {
        try {
            dn.getRule(ZERO_RULE);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage()!= null && e.getMessage()!="");
            System.out.println(OK);
        }
        
        try {
            dn.getRule(NEGATIVE_RULE);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage()!= null && e.getMessage()!="");
            System.out.println(OK);
        }
        assertEquals(DeathNote.RULES.get(RULE), this.dn.getRule(RULE));
    }

    @Test
    public void AllRules() {
        for(final String elem : DeathNote.RULES) {
            assertNotEquals("", elem);
            assertNotEquals(null, elem);
        }
    }

    @Test
    public void HumanDeath() {
        assertEquals(false, dn.isNameWritten(name));
        dn.writeName(name);
        assertEquals(true, dn.isNameWritten(name));
        
        assertEquals(false, dn.isNameWritten(name2));

        assertEquals(false, dn.isNameWritten(EMPTY_STRING));

    }

    @Test
    public void DeathCauseCorrectTiming() {
        try {
            dn.writeDeathCause(deathCause);
        } catch (IllegalStateException e) {
            System.out.println(OK);
        }

        dn.writeName(name);
        assertEquals(DeathNoteImplementation.DEFAULT_DEATH_CAUSE, dn.getDeathCause(name));
        dn.writeName(name2);
        assertTrue(dn.writeDeathCause(deathCause));
        assertEquals(deathCause, dn.getDeathCause(name2));
        try {
            Thread.sleep(SHORT_SLEEPING_TIME);
        } catch (InterruptedException e) {
            System.out.println(OK);
        }
        
        dn.writeDeathCause(DeathNoteImplementation.DEFAULT_DEATH_CAUSE);
        assertEquals(deathCause, dn.getDeathCause(name2));

    }

    @Test
    public void DeathDetailsCorrectTiming () {
        try {
            dn.writeDetails(deathDetails);
        } catch (IllegalStateException e) {
            System.out.println(OK);
        }
        dn.writeName(name);
        assertEquals(EMPTY_STRING, dn.getDeathDetails(name));
        assertTrue(dn.writeDetails(deathDetails));
        assertEquals(deathDetails, dn.getDeathDetails(name));

        dn.writeName(name2);
        try {
            Thread.sleep(LONG_SLEEPING_TIME);
        } catch (InterruptedException e) {
            System.out.println(OK);
        }

        dn.writeDetails(deathDetails);
        assertEquals(EMPTY_STRING, dn.getDeathDetails(name2));


    }
}