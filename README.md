# range-grouper

testing workstation

## Important
+ Compiled for JRE 9 (Initially compiled for JRE 17 by default in Eclipse Jupiter)
+ Coded and Tested using Eclipse Jupiter
+ Unit Tests written, compiled and run using JUnit 4.13.2 (In eclipse). NOT tested from the command line
+ JAR file included in the project root (Execution instructions to follow)

## Execution
### From binaries
#### Loading a delimited number sequence from a file
`java -cp bin ranger.Ranger -f path/to/file "delim"`

Example: `java -cp bin ranger.Ranger -f res/testtest.txt ","`

#### Generating a random sequence of numbers
`java -cp bin ranger.Ranger -g min max`

Example: `java -cp bin ranger.Ranger -g 1 25`

### From JAR
#### Loading a delimited number sequence from a file
`java -jar ranger.jar -f path/to/file "delim"`

Example: `java -jar ranger.jar -f res/testtest.txt ","`

#### Generating a random sequence of numbers
`java -jar ranger.jar -g min max`

Example: `java -jar ranger.jar -g 1 25`

## Assumptions
+ The main assumption made when attempting this project was to define two numbers: A and B, as sequential if and only if B = A + 1
+ The next assumpion is that we DO NOT consider two numbers to be sequential if B = A - 1 (reverse)

## Implemented Features
+ Generation of a random number sequence between the range MIN - MAX (inclusive). This generated sequence can be used as input to the program to group sequential ranges
+ The program can also load a delimted number sequence from a file (See the *Execution* section above)
