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
    private final String deathDetails = "fall from a great height";
    private static final String DEFAULTDEATHCAUSE = "heart attack";
    private static final String EMPTYSTRING = "";
    private static final int RULE = 3;
    private static final int ZERORULE = 0;
    private static final int NEGATIVERULE = -1;
    private static final long SHORTSLEEPINGTIME = 100L;
    private static final long LONGSLEEPINGTIME = 6100L;
    private static final String OK = "OK! Catched correct Exception!";
    private DeathNote dn ;

    @BeforeEach
    public void setUp() {
        this.dn = new DeathNoteImplementation();
    }

    @Test
    public void ConsistenceRule() {
        try {
            dn.getRule(ZERORULE);
        } catch (IllegalStateException e) {
            System.out.println(OK);
        }
        try {
            dn.getRule(NEGATIVERULE);
        } catch (IllegalStateException e) {
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

        assertEquals(false, dn.isNameWritten(EMPTYSTRING));

    }

    @Test
    public void DeathCauseCorrectTiming() {
        try {
            dn.writeDeathCause(deathCause);
        } catch (IllegalStateException e) {
            System.out.println(OK);
        }

        dn.writeName(name);
        assertEquals(DEFAULTDEATHCAUSE, dn.getDeathCause(name));
        dn.writeName(name2);
        assertTrue(dn.writeDeathCause(deathCause));
        assertEquals(deathCause, dn.getDeathCause(name2));
        try {
            Thread.sleep(SHORTSLEEPINGTIME);
        } catch (InterruptedException e) {
            System.out.println(OK);
        }
        
        dn.writeDeathCause(DEFAULTDEATHCAUSE);
        assertEquals(deathCause, dn.getDeathCause(name2));

    }

    @Test
    public void DeathDetailsCorrectTiming () {
        try {
            dn.writeDeathCause(deathDetails);
        } catch (IllegalStateException e) {
            System.out.println(OK);
        }
        dn.writeName(name);
        assertEquals(EMPTYSTRING, dn.getDeathDetails(name));
        assertTrue(dn.writeDetails("run for too long"));
        assertEquals("run for too long", dn.getDeathDetails(name));

        dn.writeName(name2);
        try {
            Thread.sleep(LONGSLEEPINGTIME);
        } catch (InterruptedException e) {
            System.out.println(OK);
        }

        dn.writeDetails(deathDetails);
        assertEquals("run for too long", dn.getDeathDetails(name2));


    }
}