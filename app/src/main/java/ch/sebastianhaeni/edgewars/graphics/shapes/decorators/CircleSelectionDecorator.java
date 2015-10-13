package ch.sebastianhaeni.edgewars.graphics.shapes.decorators;

import ch.sebastianhaeni.edgewars.graphics.programs.ParticleProgram;
import ch.sebastianhaeni.edgewars.graphics.programs.ShapeProgram;
import ch.sebastianhaeni.edgewars.graphics.shapes.Shape;

public class CircleSelectionDecorator extends DrawableDecorator {
    public CircleSelectionDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(ShapeProgram shapeProgram, ParticleProgram particleProgram) {

    }
}
