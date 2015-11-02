package ch.sebastianhaeni.edgewars.logic.entities.board.factories;

import ch.sebastianhaeni.edgewars.BR;
import ch.sebastianhaeni.edgewars.logic.entities.board.node.Node;

/**
 * A concrete factory that builds melee units.
 */
public class TankFactory extends Factory {

    /**
     * Constructor
     *
     * @param node the node this factory is at
     */
    public TankFactory(Node node) {
        super(node);
    }

    @Override
    public void produceUnit() {
        getNode().addTankUnit();
        notifyPropertyChanged(BR.tankCount);
    }

    @Override
    public int getUpgradeCost() {
        switch (getLevel()) {
            case 1:
                return 90;
            case 2:
                return 130;
            case 3:
                return 150;
            default:
                throw new IllegalStateException("Level is not 1, 2 or 3");
        }
    }

    @Override
    public int getUnitCost() {
        return 50;
    }

    @Override
    protected long getProducingDuration() {
        switch (getLevel()) {
            case 1:
                return 15000;
            case 2:
                return 10000;
            case 3:
                return 5000;
            default:
                throw new IllegalStateException("Level is not 1, 2 or 3");
        }
    }
}