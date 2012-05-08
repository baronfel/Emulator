# A MIPS Emulator #

## Purpose ##

This emulator was written as an assignment for CS 5394/4398 for Professor Rodion Podorozhny.  It was a proof of concept application that was written in Java in an MVC style by a team of four developers:

* Stuart Jones
* James Hunter
* Chester Husk
* Robert Stephens

Below, the architecture of the system will be explained.

## Architecture ##

The Emulator was writen in an MVC style, as mentioned above, using the Standard Widget Toolkit for the GUI portions.  The Event notification syntax was shamelessly borrowed from the INotifyPropertyChanged class/pattern in C#.  The overall system architecture will be presented in three sections, Instructions, Model, and View.

### Instructions ###

![Instruction Class Diagram](https://github.com/baronfel/Emulator/raw/master/vpproject/diagrams/Instructions.png "Instruction Class Diagram")

The instruction set that was implemented for this processor was a subset of the overall MIPS instruction set, numbering 30 in total.  This selection included a variety of branching operations, arithmetic operations, and memory storage and retrieval operations.

Given a file that contains a listing of MIPS instructions to be executed, the InstructionParser static class is called to parse that file into a list of IInstruction, the the canonical class for an instruction.  This class encapsulates all the different classes of instructions, the I-, J-, and R-Types.  The parser also supports Labals, which are often used in branching instructions as targets for the branch.

### Model ###

![Processor Diagram](https://github.com/baronfel/Emulator/raw/master/vpproject/diagrams/Processor.png0 "Processor Diagram")

The meat of the Emulator implementation come in with the Model.  All elements of the Model implement the IModel class via the AbstractModel base class.  This Interface provides a notifyChanged() method that is used to notify any attached IModelListeners when a property changes on this model.

The largest unit of the model is the Processor, which is created when a Simulation is begun by the UI according to some ProcessorConfiguration.  The ProcessorConfiguration specifies some combination of ALUs and a listing of supported operations and associated processor cycles to complete that instruction.

The processor also contains references to implementations of a Fetch Unit, an Issue Unit, some number of Arithmetic Logic Units, some number of MEmory Access Units, and a single WriteBack unit.  In addition to these functional segments of the Processor, it also contains a Registry with the typical 32 registers of the MIPS processor, as well as a Memory implementation that abstracts away the storage and retrieval of integers from memory.

### View ###

![View Diagram](https://github.com/baronfel/Emulator/raw/master/vpproject/diagrams/Views.png "View Diagram")

The View is implemented using the Standard Widget Toolkit, as mentioned above.  It follows a pattern of using some base class described above as the model, then writing a Controller implementation that provides access to properties of the Model, and then a SWT View that exposes those to the user.

Many Views are composed of smaller, focused sub-Views.  A good example of this is the SimulationView.  It has a ProcessorView, a NavigationView, and a PlayBackView.  The ProcessorView shows the state of the processor at that instant; the NavigationView allows the user to go back to the Simulation Configuration, or forward to the Results View; and the PlaybackView allows the user to play one cycle or run to the end of the Simulation.

The views operate by using their controller to hook up the some notifyChanged event on a model, at which point the modelChanged() method of the IModelListener interface is called for interested Views.

## Difficulties During Implementation ##

