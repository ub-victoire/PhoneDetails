# PhoneDetails: A Java Classes and Objects Lesson

This lesson introduces Java packages, classes, objects, instance methods, and the program entry point. You will build and run a small program that displays sample phone specifications.

> **Note:** The processor, RAM, and storage values in this exercise are fictional examples. The program prints fixed text; it does not detect a phone's actual hardware.

**By Ushindi B. Victoire**<br>
Software Q.A Engineer | Requirement Specialist | @ub-victoire @ub-victor

## Learning goals

By the end of the lesson, you should be able to:

- Explain the relationship between a Java package, class, and object.
- Declare classes and instance methods in separate files.
- Create objects and call their methods.
- Explain why classes in the same package do not need to import one another.
- Compile and run a packaged Java program from the command line.

## 1. Understand the program

The exercise uses two classes in the `org.phone` package:

| Class | Method | Purpose |
|---|---|---|
| `ExternalStorage` | `size()` | Prints the sample external-storage capacity. |
| `InternalStorage` | `processorName()` | Prints the sample processor name. |
| `InternalStorage` | `ramSize()` | Prints the sample RAM capacity. |

The `main` method is in `InternalStorage`. It creates one object of each class and calls the three methods.

The exercise groups the processor and RAM methods under `InternalStorage` because that is the required design here. Real applications may model hardware components differently.

## 2. Set up the project structure

The folder names must match the package name, and each public class must be in a file with the same name:

```text
PhoneDetails/
└── src/
    └── org/
        └── phone/
            ├── ExternalStorage.java
            └── InternalStorage.java
```

In this example, both source files start with:

```java
package org.phone;
```

A package groups related classes and helps Java locate them. Because both classes are in the same package, they can refer to each other by class name without an `import` statement.

## 3. Create the `ExternalStorage` class

In `src/org/phone/ExternalStorage.java`, write:

```java
package org.phone;

public class ExternalStorage {
    public void size() {
        System.out.println("External Storage: 128 GB");
    }
}
```

What each part means:

- `public class ExternalStorage` declares a class named `ExternalStorage`. Its public class name matches the filename.
- `public void size()` declares a public instance method named `size`. `void` means the method does not return a value.
- `System.out.println(...)` prints the supplied text and moves to a new line.

This class does not need a `main` method. The program will start from `InternalStorage`.

## 4. Create the `InternalStorage` class

In `src/org/phone/InternalStorage.java`, write:

```java
package org.phone;

public class InternalStorage {
    public void processorName() {
        System.out.println("Processor Name: Example Octo-Core Processor");
    }

    public void ramSize() {
        System.out.println("RAM Size: 8 GB");
    }

    public static void main(String[] args) {
        var externalStorage = new ExternalStorage();
        var internalStorage = new InternalStorage();

        externalStorage.size();
        internalStorage.processorName();
        internalStorage.ramSize();
    }
}
```

The `processorName()` and `ramSize()` methods print the processor and RAM examples. The `main` method is where this application begins running.

### The `main` method

The standard Java entry point is:

```java
public static void main(String[] args)
```

- `public` allows the Java launcher to access the method.
- `static` lets Java start the method without first creating an `InternalStorage` object.
- `void` means it returns no value.
- `String[] args` can hold command-line arguments. This lesson does not use any.

Although `main` is static, the other methods are instance methods. The program therefore creates an object before calling them.

### Creating objects and calling methods

These lines create two separate objects:

```java
var externalStorage = new ExternalStorage();
var internalStorage = new InternalStorage();
```

`new` constructs an object. Each variable refers to the object created from its class. Java infers the variable's type from the expression on the right: `externalStorage` is an `ExternalStorage`, and `internalStorage` is an `InternalStorage`.

The same declarations can be written with explicit types:

```java
ExternalStorage externalStorage = new ExternalStorage();
InternalStorage internalStorage = new InternalStorage();
```

`var` is local-variable type inference, available in local variables since Java 10. It does not make the variable untyped; the compiler still determines and checks its type.

The dot operator calls a method on a particular object:

```java
externalStorage.size();
internalStorage.processorName();
internalStorage.ramSize();
```

Each object can call methods defined by its class. Being in the same package does not give one class the methods of another class. For example, `externalStorage.processorName()` would not compile because `ExternalStorage` does not define that method.

## 5. Compile and run the program

Open a terminal in the `PhoneDetails` project root. With JDK 25 installed, compile both source files:

```bash
javac --release 25 -d out src/org/phone/ExternalStorage.java src/org/phone/InternalStorage.java
```

Here:

- `javac` is the Java compiler.
- `--release 25` compiles against the Java 25 language and platform API.
- `-d out` places the compiled class files in the `out` directory, preserving the package structure.

Then run the class containing `main`:

```bash
java -cp out org.phone.InternalStorage
```

`-cp out` tells the Java launcher to look for compiled classes in `out`. Use the fully qualified class name (`org.phone.InternalStorage`), not a source filename or a `.class` filename.

After compilation, the output directory contains:

```text
out/
└── org/
    └── phone/
        ├── ExternalStorage.class
        └── InternalStorage.class
```

Expected output:

```text
External Storage: 128 GB
Processor Name: Example Octo-Core Processor
RAM Size: 8 GB
```

You can combine compilation and execution with `&&`:

```bash
javac --release 25 -d out src/org/phone/ExternalStorage.java src/org/phone/InternalStorage.java && java -cp out org.phone.InternalStorage
```

The program runs only if compilation succeeds.

## 6. Common mistakes to check

- **Package and folder do not match:** `package org.phone;` should be in files under `src/org/phone/`.
- **Public class and filename differ:** `public class InternalStorage` must be in `InternalStorage.java`, including capitalization.
- **Wrong method called on an object:** Call `size()` on an `ExternalStorage` object; call `processorName()` and `ramSize()` on an `InternalStorage` object.
- **Running from the wrong directory:** Run the commands from the project root so the source paths resolve.
- **Using a source path to launch compiled output:** After compiling, launch `org.phone.InternalStorage` with `-cp out`.
- **JDK version is too old:** `--release 25` requires JDK 25. Check the installed version with `java -version` and `javac -version`.

## 7. Check your understanding

Try explaining these questions in your own words:

1. What is the difference between a class and an object?
2. Why can these two classes refer to one another without an `import` statement?
3. Why can `externalStorage` call `size()` but not `processorName()`?
4. Why does `main` create an `InternalStorage` object before calling its instance methods?
5. What is the difference between compiling a `.java` file and launching `org.phone.InternalStorage` from the compiled output?

### Optional practice

1. Change one of the sample values and run the program again.
2. Add a `batteryCapacity()` instance method to `InternalStorage` and call it from `main`.
3. Predict the output order before running the program. Then compare your prediction with the result.

## Summary

This program places two classes in one package, creates an object from each class, and calls instance methods through those objects. The `main` method provides the entry point, while the Java compiler turns the source files into class files that the Java launcher can run.
