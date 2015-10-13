package ch.sebastianhaeni.edgewars.graphics.shapes.decorators;

import ch.sebastianhaeni.edgewars.graphics.GameRenderer;
import ch.sebastianhaeni.edgewars.graphics.programs.ParticleProgram;
import ch.sebastianhaeni.edgewars.graphics.programs.ShapeProgram;
import ch.sebastianhaeni.edgewars.graphics.shapes.Shape;

public class NearDeathDecorator extends DrawableDecorator {
    public NearDeathDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(GameRenderer renderer, ShapeProgram shapeProgram, ParticleProgram particleProgram) {

    }
}
