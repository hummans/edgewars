package ch.bfh.edgewars.logic.entities.board.node.state;

import ch.bfh.edgewars.logic.entities.Player;
import ch.bfh.edgewars.logic.entities.board.node.Node;

public class OwnedState extends NodeState {
    private final Player mOwner;

    public OwnedState(Node node, Player owner) {
        super(node);
        mOwner = owner;
        node.setColor(owner.getColor());
    }

    @Override
    public void update(long millis) {
        // TODO adjust value
        mOwner.addEnergy(10);
    }

    @Override
    public long getUpdateInterval() {
        return 50;
    }

}
