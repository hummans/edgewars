package ch.sebastianhaeni.edgewars.logic.entities.board.units.state;

import ch.sebastianhaeni.edgewars.logic.Constants;
import ch.sebastianhaeni.edgewars.logic.entities.Player;
import ch.sebastianhaeni.edgewars.logic.entities.board.Edge;
import ch.sebastianhaeni.edgewars.logic.entities.board.node.Node;
import ch.sebastianhaeni.edgewars.logic.entities.board.units.Unit;

/**
 * State for unit waiting on an edge to proceed because there's another unit blocking the way.
 */
public class WaitState extends OnEdgeState {

    /**
     * Constructor
     *
     * @param unit              the unit having this state
     * @param node              target node
     * @param player            owning player
     * @param edge              edge this unit is on
     * @param travelledDistance distance travelled on edge
     */
    public WaitState(Unit unit, Node node, Player player, Edge edge, float travelledDistance) {
        super(unit, node, player, edge, travelledDistance);
    }

    @Override
    protected void onFreeWay() {
        if (getUnit().getState() instanceof WaitState) {
            getUnit().setState(new MovingState(getUnit(), getNode(), getPlayer(), getEdge(), getTravelledDistance()));
        }
    }

    @Override
    public long getUpdateInterval() {
        return Constants.WAIT_STATE_UPDATE_INTERVAL;
    }
}
