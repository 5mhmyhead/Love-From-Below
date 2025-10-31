package components.entities;

// TODO ADD NPC SPECIFIC CODE HERE
public abstract class NPC extends Entity {

    // FUNCTION TO ACTIVATE CERTAIN ACTIONS OF NPCS
    public abstract void action();

    protected boolean inDialogue;
    public boolean isInDialogue() { return inDialogue; }
}
