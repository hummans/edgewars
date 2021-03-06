package ch.sebastianhaeni.edgewars.logic.entities.board.node.state;

import java.util.Arrays;

import ch.sebastianhaeni.edgewars.logic.Constants;
import ch.sebastianhaeni.edgewars.logic.entities.Player;
import ch.sebastianhaeni.edgewars.logic.entities.board.node.Node;

/**
 * The state a node is in when it is owned by a player.
 */
public class OwnedState extends NodeState {
    private final Player mOwner;

    /**
     * Constructor
     *
     * @param node  the node that has this state
     * @param owner the player that owns the node
     */
    public OwnedState(Node node, Player owner) {
        super(node);
        mOwner = owner;
        node.clearUnitsAndLevels();
        node.setColor(owner.getColor());

        node.hideNodeMenu();

        node.getTankFactory().deactivate();
        node.getMeleeFactory().deactivate();
        node.getSprinterFactory().deactivate();
    }

    @Override
    public void update() {
        mOwner.addEnergy(Constants.OWNED_STATE_ENERGY_GAIN);
        getNode().addHealth(Constants.OWNED_STATE_HEALTH_GAIN);
    }

    @Override
    public long getUpdateInterval() {
        return Constants.OWNED_STATE_UPDATE_INTERVAL;
    }

    @Override
    public String toString() {
        return Arrays.toString(mOwner.getColor());
    }

    public Player getOwner() {
        return mOwner;
    }
}
