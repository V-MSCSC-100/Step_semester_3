Week 1 --- Strings and Exceptions

In Week 1, I learned the fundamentals of working with Strings in Java. I
learned how to create and use String objects, accept String input from
the user, work with arrays of Strings, and pass Strings as method
parameters. I also learned about escape sequences and the commonly used
built-in String methods for manipulating and examining text. I studied
ASCII codes and how characters can be represented and converted using
their numeric values. Along with Strings, I was introduced to Java
exceptions, including the exception hierarchy, different types of
exceptions, checked exceptions, unchecked exceptions, and
RuntimeException. I also learned the importance of handling exceptions
properly and following good practices when writing programs that may
encounter errors.

Week 2 --- String Manipulation

In Week 2, I focused more deeply on String operations and manipulation
in Java. I used built-in String methods to perform common operations on
text and worked with ASCII values for character conversion and
processing. I also learned the difference between immutable Strings and
mutable string-handling classes such as StringBuilder and StringBuffer.
I understood how StringBuilder and StringBuffer can be used when
repeated modifications are required, and I learned why choosing the
appropriate string class can affect program performance. I also compared
the different approaches to String manipulation and understood when
mutable strings are more suitable than regular String objects.

Week 3 --- Object-Oriented Programming Basics

In Week 3, I learned the basic concepts of Object-Oriented Programming
in Java. I studied classes and objects and understood how a class acts
as a blueprint while an object represents an instance created from that
blueprint. I learned how to define classes, create objects, and access
the members associated with them. I also studied instance members and
class-level static members, understanding the difference between data
and behavior that belongs to an individual object and members that are
shared at the class level. I learned how object-oriented programming
supports concepts such as reusability, security, and extensibility and
how classes and objects provide the foundation for structuring larger
Java programs.

Week 4 --- Constructors and Java Keywords

In Week 4, I learned about constructors and several important Java
keywords used in object-oriented programming. I studied default
constructors and parameterized constructors and learned how constructors
initialize objects when they are created. I learned constructor
overloading, where a class can have multiple constructors with different
parameter lists, and I used this() for constructor chaining so that
one constructor can call another constructor in the same class. I also
learned the this keyword for referring to the current object's
variables and constructors. I studied the final keyword and its use
with variables, methods, and classes, including how it can prevent
reassignment, overriding, or inheritance depending on where it is used.
I also learned the use of static for class-level members and the
instanceof operator for checking an object's type before performing
type-related operations.

Week 5 --- Inheritance and Polymorphism

In Week 5, I learned how inheritance allows classes to establish
relationships and reuse fields and methods from parent classes. I
studied single inheritance, multilevel inheritance, and hierarchical
inheritance and learned how the extends keyword establishes an IS-A
relationship. I learned how super is used to access parent-class
behavior and how super() passes construction responsibility to the
parent constructor. I also studied constructor chaining and understood
that constructors in an inheritance hierarchy execute from the topmost
parent down to the child. I learned method overriding using @Override,
including how a subclass can redefine inherited behavior while retaining
the same method signature, and how super.method() can be used to reuse
the parent's implementation. I then studied polymorphism, including
compile-time polymorphism through method overloading and runtime
polymorphism through method overriding and dynamic method dispatch. I
learned that overloaded methods are selected at compile time based on
their parameter lists, while overridden methods are selected at runtime
based on the actual object. I also learned upcasting and downcasting,
including the importance of using instanceof before a downcast to
avoid an invalid cast and a possible ClassCastException.

Week 6 --- Abstraction and Interfaces

In Week 6, I learned abstraction and how abstract classes and interfaces
are used to design related classes and shared capabilities. I studied
abstract classes, abstract methods, and the rules for implementing
abstract methods in concrete subclasses. I learned that an abstract
class can contain actual state, constructors, concrete methods, and
abstract methods, while it cannot be instantiated directly. I then
learned interfaces as capability-based contracts and how a class can
implement multiple interfaces even though it can extend only one class.
I studied how interfaces provide a way to achieve multiple inheritance
of capabilities and how unrelated classes can implement the same
interface without sharing a parent class. I compared abstract classes
and interfaces and learned when each is appropriate: an abstract class
is useful when closely related classes share state and implementation,
while an interface is useful when different classes need to share a
capability. I also learned the distinction between IS-A relationships
through inheritance and CAN-DO relationships through interfaces. I
applied these concepts with upcasting, polymorphic references, interface
arrays, and instanceof, including checking and safely downcasting
interface references.

Week 7 --- Object Methods, Inner Classes and UML Diagrams

In Week 7, I learned about the methods inherited from Java's Object
class and how they are used when modeling objects. I studied
toString() and learned how overriding it provides a meaningful
representation of an object's state instead of the default object
representation. I learned the difference between == and .equals(),
where == compares object references while .equals() can be
overridden to compare meaningful object data. I also learned that
hashCode() must be overridden consistently with equals() so that
hash-based collections such as HashSet and HashMap work correctly. I
studied getClass() and its difference from instanceof, particularly
when checking an object's exact runtime type or whether it belongs to a
type or one of its subtypes. I then learned object cloning using
clone() and the difference between shallow and deep copying. I
understood that a shallow copy can share references to mutable objects,
while a deep copy creates independent copies of mutable reference data,
and I learned the role of the Cloneable marker interface.

I also learned about inner classes and the different ways helper classes
can be nested inside another class. I studied member inner classes,
which are associated with a specific outer object, static nested
classes, which do not require an outer object, local inner classes that
are defined within a method, and anonymous inner classes that are
unnamed and can provide one-time implementations of an interface or
behavior. Finally, I learned UML diagrams for representing Java designs
visually. I studied class diagrams for showing classes, fields, methods,
inheritance, and interface relationships; object diagrams for showing
specific runtime objects and their current values; and sequence diagrams
for showing the order of method calls and interactions between objects
over time.
