# vm
Implementation of a virtual machine

## Introduction
This code was written for an assignment in a Concurrent and Distributed Programming class. It is an implementation of a virtual machine and uses concepts of [concurrent programming](https://www.educative.io/blog/multithreading-and-concurrency-fundamentals) such as multithreading, lock, mutex, and thread safety. 

## How does it work?
The program reads an input file containing instructions line by line using the ```FileReaderThread``` and stores them into the instructions memory. The ```InstructionsThread``` executes instructions from the instructions memory. There are only 3 tupes of instruction: *create object*, *use object*, *delete object*. The *create object* instruction creates an object and stores it in the object memory and the *delete object* instruction marks an object from the object memory for deletion. The *use object* instruction simply uses an object and marks it as such. Once an instruction is executed, it is automatically deleted from the instructions memory. The ```GarbageCollectionThread``` traverses the object memory and deletes objects that have been marked for deletion.

## Installation
If not installed already, install [Java](https://www.java.com/en/download/help/windows_manual_download.html) on your machine. Download and save the files in the same directory and keep them in the same hierarchy. If you decide to rename the ```assignment 3``` folder to something else then make sure to change the *import* statement on the first line of ```VirtualMachine.java``` as well as the *package* statements on all the other ```.java``` files accordingly. Additionally, always ensure that the *input* file is stored in the *inputs* folder and that the latter is in the same directory as ```VirtualMachine.java```. 

## Usage
To compile the program run the following command on your CLI:
```bash
javac VirtualMachine.java
```

Then to start the program run the following program:
```bash
java VirtualMachine
``` 

## Contributing
Feel free to add anything that you deem interesting or useful. However, for major changes please create an issue first so we can chat about what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
