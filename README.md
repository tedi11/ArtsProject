# ArtsProject

# Tema Proiectului
Implementation of an application that keeps trak of the museums and expositions of art. Both have a set of art projects, that could be either a painting or a sculpture. Every work of art has an author and is inspired by an artistic movement.

# Clasele Modelului

Class **ArtisticMovement**: This class is used in other classes that represent a work of art.

Class **ArtProject**: This is an abstract class that is used to create the other 2 classes that represent art projects.

Class **Painting**: This is a class that extends ArtProject and has aditional information about specific piecies of art.

Class **Sculpture**: This is a class that extends ArtProject and has aditional information about specific piecies of art.

Class **Author**: This class is used in other classes that represent a work of art.

Class **Address**: This class is used in other classes that represent a location for museums or expositions.

Class **Exposition**: This class represents an ocasional colection of pieces of art that takes place on a specific date and has a price.

Class **Exposition**: This class contains colections of art galeries and is free of charge.

# App Package 
**Main class**: instantiate the menu and run the main program of the application.

**Menu class**: it has a method implemented specific to the interactive menu and instantiates a service object (Class created using a design pattern - singleton).

**Service classes**: there are 3 classes that implement all the methods specified in the menu (Class created using a design pattern - singleton).

**Reader class**: it implements methods for reading data from the keyboard for the purpose of creating new objects (Class created using a design pattern - singleton).

# Menu

```
-----------------------------------------------
Local museum application
1. Add a museum.
2. Add an art project.
3. Add a piece of art to a museum.
4. Sell an art project from a museum.
5. Show every piece of art.
6. Add a new artistic movement.
7. Add a new author.
8. Add a new address.
9. Make a new exposition.
10. What is the average year of appearance for every exposition?
11. Find art projects for a specific author.
12. Check if a painting is from a specific artistic movement.
13. What is the author with the most artworks.
14. What is the heaviest sculpture in a museum.
15. Show museums.
16. Show authors.
17. Show expositions.
18. Show artistic movements.
19. Add a piece of art to an exposition.
0. Exit
-----------------------------------------------
Please insert your option: 
```
