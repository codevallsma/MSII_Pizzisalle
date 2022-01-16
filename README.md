# PizziSalle - Eric Vallsmadella

* IMPORTANT!!! EXECUTE THE DATABASE_STRUCTURE.sql FILE ON MYSQL BEFORE EXECUTING THE JAVA MAIN
* ALSO EDIT THE VALUES OF THE assets/.env file

<b>Patterns used:</b>
- <b>Singleton:</b>
  - For the database connectors
  - For the model
- <b>Strategy:</b>
  - For the delegation model class
  - For the rest of the classes that implement ModifyRamClasses
- <b>State:</b>
  - For the controller classes
- <b>Factory Method</b>
  - For the delegation model class
  - Mapper subclasses, that select the specific repository according to the database table that<br> the user wants to interact with
  - To select the appropiate database connector 
- <b>Observer:</b>
  - Model classes and repository classes
- <b>Flyweight:</b>
  - Mapper abstract class.