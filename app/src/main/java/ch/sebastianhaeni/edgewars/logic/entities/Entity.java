package ch.sebastianhaeni.edgewars.logic.entities;

import android.databinding.BaseObservable;

import ch.sebastianhaeni.edgewars.logic.Game;

/**
 * An entity is a thing in the game and it may wants to be updated.
 */
public abstract class Entity extends BaseObservable {
    private long mInterval;

    /**
     * Constructor for entities that want to be updated.
     *
     * @param interval the interval to be updated in
     */
    public Entity(long interval) {
        mInterval = interval;
        Game.getInstance().register(this);
    }

    /**
     * Constructor for entities that don't want to be updated.
     */
    public Entity() {
        // no op
    }

    /**
     * Update the entity.
     *
     * @param millis time passed since last update
     */
    public abstract void update(long millis);

    /**
     * Sets the interval this object wants to be updated in.
     *
     * @param interval milliseconds
     */
    public void setUpdateInterval(long interval) {
        mInterval = interval;
        Game.getInstance().register(this);
    }

    /**
     * @return gets the update interval in milliseconds
     */
    public long getInterval() {
        return mInterval;
    }

}
