package ch.sebastianhaeni.edgewars.logic.entities.board.node.state;

import java.util.Arrays;

import ch.sebastianhaeni.edgewars.logic.entities.Player;
import ch.sebastianhaeni.edgewars.logic.entities.board.node.Node;

public class OwnedState extends NodeState {
    private final Player mOwner;

    public OwnedState(Node node, Player owner) {
        super(node);
        mOwner = owner;
        node.setColor(owner.getColor());
    }

    @Override
    public void update(long millis) {
        mOwner.addEnergy(10);
    }

    @Override
    public long getUpdateInterval() {
        return 50;
    }

    @Override
    public String toString() {
        return Arrays.toString(mOwner.getColor());
    }

    public Player getOwner() {
        return mOwner;
    }
}