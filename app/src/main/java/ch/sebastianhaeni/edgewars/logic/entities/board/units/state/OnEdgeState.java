package ch.sebastianhaeni.edgewars.logic.entities.board.units.state;

import ch.sebastianhaeni.edgewars.graphics.drawables.shapes.Shape;
import ch.sebastianhaeni.edgewars.logic.Game;
import ch.sebastianhaeni.edgewars.logic.entities.Entity;
import ch.sebastianhaeni.edgewars.logic.entities.Player;
import ch.sebastianhaeni.edgewars.logic.entities.board.Edge;
import ch.sebastianhaeni.edgewars.logic.entities.board.node.Node;
import ch.sebastianhaeni.edgewars.logic.entities.board.units.Unit;
import ch.sebastianhaeni.edgewars.util.Position;

/**
 * State of a unit that's traversing and edge and is rendered.
 */
public abstract class OnEdgeState extends UnitState {
    private final Node mNode;
    private final Player mPlayer;
    private final Edge mEdge;
    private final Position mTargetPosition;
    private final Position mStartingPosition;
    private float mTravelledDistance;

    /**
     * Constructor
     *
     * @param unit              the unit having this state
     * @param node              target node
     * @param player            owning player
     * @param edge              edge this unit is on
     * @param travelledDistance distance travelled on edge
     */
    OnEdgeState(Unit unit, Node node, Player player, Edge edge, float travelledDistance) {
        super(unit);

        mNode = node;
        mPlayer = player;
        mEdge = edge;
        mTravelledDistance = travelledDistance;

        Node mStartingNode = edge.getTargetNode().equals(mNode)
                ? edge.getSourceNode()
                : edge.getTargetNode();

        mTargetPosition = mNode.getPosition();
        mStartingPosition = mStartingNode.getPosition();

        unit.show(getPosition(), player.getColor());
    }

    /**
     * @return gets the position of the unit
     */
    protected Position getPosition() {
        double dx = getTargetPosition().getX() - getStartingPosition().getX();
        double dy = getTargetPosition().getY() - getStartingPosition().getY();

        double distance = Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));

        dx /= distance;
        dy /= distance;

        float x = (float) (getStartingPosition().getX() + (mTravelledDistance * dx));
        float y = (float) (getStartingPosition().getY() + (mTravelledDistance * dy));

        return new Position(x, y);
    }

    @Override
    public void update(long millis) {
        Node reached = getReachedNode();

        if (reached != null) {
            onEntityReached(reached);
            return;
        }

        Unit encountered = getEncounteredUnit();

        if (encountered != null) {
            onEntityReached(encountered);
        } else {
            onFreeWay();
        }
    }

    /**
     * Called when the way is free.
     */
    protected void onFreeWay() {
        // no op
    }

    /**
     * Called when the unit reached another entity on the edge.
     *
     * @param entity reached entity
     */
    protected void onEntityReached(Entity entity) {
        // no op
    }

    /**
     * Checks if the target node is reached and returns it.
     *
     * @return the reached node or <code>null</code> if nothing reached yet
     */
    private Node getReachedNode() {
        return getUnit().getShape().getPosition().isAboutTheSame(mTargetPosition) ? mNode : null;
    }

    /**
     * Finds units on the same edge and checks if this unit is colliding with one.
     * If so, the encountered unit is returned. <code>null</code> otherwise
     *
     * @return an encountered unit or null if nothing encountered
     */
    public Unit getEncounteredUnit() {
        for (Unit u : Game.getInstance().getUnitsOnEdge(mEdge)) {
            if (u.equals(getUnit())) {
                continue;
            }

            Shape otherShape = u.getShape();
            if (otherShape == null) {
                continue;
            }
            if (otherShape.getPosition().isAboutTheSame(getUnit().getShape().getPosition())) {
                return u;
            }
        }

        return null;
    }

    /**
     * @return gets the edge
     */
    public Edge getEdge() {
        return mEdge;
    }

    /**
     * @return gets the owning player
     */
    protected Player getPlayer() {
        return mPlayer;
    }

    /**
     * @return get the target node
     */
    protected Node getNode() {
        return mNode;
    }

    /**
     * @return gets target position
     */
    protected Position getTargetPosition() {
        return mTargetPosition;
    }

    /**
     * @return gets starting position
     */
    protected Position getStartingPosition() {
        return mStartingPosition;
    }

    /**
     * @return gets travelled distance
     */
    protected float getTravelledDistance() {
        return mTravelledDistance;
    }

    /**
     * Sets the travelled distance on the node.
     *
     * @param travelledDistance the distance travelled
     */
    protected void setTravelledDistance(float travelledDistance) {
        mTravelledDistance = travelledDistance;
    }

}
