# CS3500_Assignment5

This README provides an overview of our design and the assumptions we made in the creation of the Easy Animator Part 1.

The output() method in the cs3500.animator.model.AnimationModelImpl class will produce the required textual description of the model as defined in the assignment description (2.1).

Our model has 3 main interfaces to represent an animation of shapes across a 2D plane.

Interface #1:

//cs3500.animator.model.AnimationModel1

Classes implementing this interface are suppose to represent the overall data model of the shapes in the animation and also the transformations that are applied to those shapes

  --A user of this interface should be able to add/remove shapes to the animation (i.e. addShape, removeShape)
  --A user of this interface should be able to add/remove transformations to a specific shape (i.e. addTransformation, removeTransformation)
  --A user of this interface should be able to view the Shapes and Transformations that exist in the animation (i.e. getShapeList, getTransformationList)
  --A user of this interface should be able to view a textual description of the animation as specified in the assignment description, section 2.1
  (i.e. output)


Interface #2:

Shape

This interface represents a 2D shape that can be animated via Transformations that are applied to it. We currently support animation for 2 types of shapes, Ellipses and Rectangles.

Classes implementing this interface should be able to:

--add/remove transformations for *this* shape (i.e. add/removeTransformation)
--view the transformations applied to this shape (i.e. getTransformations)
--view a textual description of the shape and the transformations applied to it (i.e. getShapeInfo)


Interface #3:

Transformation

This interface represents the possible animation "steps" that can be applied to a shape. We currently support 3 types of transformations: Recoloring, cs3500.animator.model.Scaling, and cs3500.animator.model.Motion. 

A transformation always has to have a shape that is to be applied to.

A large majority of the invariants we thought of are related to the creation of shapes and transformations:

INVARIANTS:
1.No position can be negative (this is enforced in the concrete Shape and Transformation classes)
2.Dimensions of a shape cannot be negative
3.Times cannot be negative, endTime has to be greater than startTime (can't go back in time)
4.Dimensions of a shape cannot be zero


ASSIGNMENT 6 UPDATES:

   Fixed the transformation interface and implementation from an abstract class to a concrete class
   All transformations contain all fields. This is to ease the implementation of the different view types

ASSIGNMENT 7 UPDATES:

    Controller was added using the animation controller interface, which was implemented using an ActionListener class
      -- Classes implementing this interface aim to handle user-input from mouse & keyboard actions
      -- Mouse-clicks are "listened" to by the controller which will then fire off the appropriate operation to the view

    EditorAnimationView interface which extends the original view interface (AnimationView)
      -- Classes implementing this new interface should be able to offer the functionality as required in assignment 7 (i.e. play, restart, speed-up)
      -- Added "editor" view as an option for creation in the main() method
    