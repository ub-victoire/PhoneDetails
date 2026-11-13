# Session 4 — PhoneDetails

## 1. Understand Question 04

Create two classes in the **same package**, `org.phone`:

| Class | Required instance methods |
|---|---|
| `ExternalStorage` | `size()` |
| `InternalStorage` | `processorName()`, `ramSize()` |

Put the program's `main` method in `InternalStorage`. Inside it:

1. Create an `ExternalStorage` object.
2. Create an `InternalStorage` object.
3. Call `size()` on the external-storage object.
4. Call `processorName()` and `ramSize()` on the internal-storage object.

**Scope:** two objects, three method calls, one package. No inheritance or external libraries are needed.

The question groups processor and RAM information under `InternalStorage`; follow those exact class and method requirements for this exercise, even though a real phone model would likely organize those components differently.

## 2. What changes from Question 03?

| Question 03 | Question 04 |
|---|---|
| Four packages | One package |
| Four classes | Two classes |
| Entry point in `Employee` | Entry point in `InternalStorage` |
| Imports for classes in other packages | No import needed between these two classes |

Both source files begin with:

```java
package org.phone;
```

Sharing a package does not combine the classes into one class or create objects automatically. It simply lets them refer to each other by their simple names without imports.

## 3. A useful lesson from your previous launch command

You ran:

```bash
java -cp out src/org/emp/Employee.java
```

That is a valid **source-file launch**, not a launch of `out/org/emp/Employee.class`. With a `.java` source-file argument, the launcher compiles source in memory and runs it; `-cp out` supplies an additional classpath. [Oracle: The Java 25 java Command](https://docs.oracle.com/en/java/javase/25/docs/specs/man/java.html)

For the compile-then-run workflow we are practicing, use a class name after compilation:

```bash
java -cp out org.emp.Employee
```

For this new project, that becomes:

```bash
java -cp out org.phone.InternalStorage
```

Neither launch style is inherently wrong. Keep them distinct so you know whether you are testing the source-launch workflow or the compiled output in `out/`.

## 4. Create the sibling project

Starting from your current `EmployeeInformation` directory:

```bash
cd ..
pwd
mkdir -p PhoneDetails/src/org/phone PhoneDetails/.vscode
cd PhoneDetails
code .
```

Source structure:

```text
PhoneDetails/
├── .gitignore
├── README.md
├── .vscode/
│   └── settings.json
└── src/
    └── org/
        └── phone/
            ├── ExternalStorage.java
            └── InternalStorage.java
```

Keep using your installed JDK 25 and existing VS Code Java tools.

### VS Code settings

Create `.vscode/settings.json`:

```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "out"
}
```

### Ignore build output and backup files

Create `.gitignore`:

```gitignore
# Java bytecode and build output
*.class
out/
bin/
target/

# Editor backups used in these exercises
*~
*.java.save

# Logs
*.log
```

The `*.java.save` rule addresses the backup filename pattern seen in your previous project without broadly ignoring every file ending in `.save`.

## 5. Implement ExternalStorage

Create `src/org/phone/ExternalStorage.java`:

```java
package org.phone;

public class ExternalStorage {

    public void size() {
        System.out.println("External Storage: 128 GB");
    }
}
```

This class has one required instance method. It does not need a `main` method because it is not our entry point.

The storage value is a fictional demonstration value, not a detected property of your computer or phone.

## 6. Implement InternalStorage

Create `src/org/phone/InternalStorage.java`:

```java
package org.phone;

public class InternalStorage {

    public void processorName() {
        System.out.println("Processor Name: Example Octa-Core Processor");
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

No `import org.phone.ExternalStorage;` is needed: both classes belong to `org.phone`.

We keep Java 25 as our target, use `var` where the initializer makes the local type clear, and retain the conventional static entry point so both exercise objects are created explicitly.

## 7. Understand the object references

```java
var externalStorage = new ExternalStorage();
var internalStorage = new InternalStorage();
```

| Variable | Compiler-inferred type | Available required methods |
|---|---|---|
| `externalStorage` | `ExternalStorage` | `size()` |
| `internalStorage` | `InternalStorage` | `processorName()`, `ramSize()` |

For these declarations, the explicit-type equivalents are:

```java
ExternalStorage externalStorage = new ExternalStorage();
InternalStorage internalStorage = new InternalStorage();
```

Use one form or the other, not both in the same scope.

### Why not call every method on one object?

This is correct:

```java
externalStorage.size();
internalStorage.processorName();
```

This would fail to compile:

```java
externalStorage.processorName();
```

`ExternalStorage` does not define or inherit a `processorName()` method. Being in the same package does not give each class the other class's methods.

### Why create an InternalStorage object inside InternalStorage?

`main` is static, but `processorName()` and `ramSize()` are instance methods. The explicit `internalStorage` object provides the instance on which we invoke those methods.

The object is created inside `main`, not inside an `InternalStorage` constructor or field initializer. Creating another instance of the same class is normal here; it does not automatically call `main` again.

## 8. Compile and run

Save both source files and run from the `PhoneDetails` project root:

```bash
javac --release 25 -d out src/org/phone/*.java
java -cp out org.phone.InternalStorage
```

Here, Bash expands `src/org/phone/*.java` into the Java source files directly inside that directory. This wildcard is sufficient because both classes share that one source directory.

`-d out` puts the generated classes under the package directory inside `out`. [Oracle: The Java 25 javac Command](https://docs.oracle.com/en/java/javase/25/docs/specs/man/javac.html)

Expected generated files:

```text
out/org/phone/ExternalStorage.class
out/org/phone/InternalStorage.class
```

Expected console output:

```text
External Storage: 128 GB
Processor Name: Example Octa-Core Processor
RAM Size: 8 GB
```

### Compile and run safely in one command

```bash
javac --release 25 -d out src/org/phone/*.java && java -cp out org.phone.InternalStorage
```

The second command runs only if compilation succeeds.

Your earlier `$(find src -name "*.java")` approach works for these simple paths too. The directory wildcard is shorter for this particular layout. Unlike `find`, this wildcard does not recursively search subdirectories.

## 9. Add the README

Create `README.md`:

````markdown
# PhoneDetails

Core Java OOP — Question 04.

## Objective

Create ExternalStorage and InternalStorage objects inside
InternalStorage.main, then call all three required instance methods.

Both classes belong to org.phone.

## Classes and methods

- ExternalStorage: size()
- InternalStorage: processorName(), ramSize()

## Requirements

JDK 25. No external dependencies or build tools are required.

## Compile and run

From the project root:

```bash
javac --release 25 -d out src/org/phone/*.java
java -cp out org.phone.InternalStorage
```

## Expected output

```text
External Storage: 128 GB
Processor Name: Example Octa-Core Processor
RAM Size: 8 GB
```

## Concepts practiced

- Multiple classes in one package
- Object creation and references
- Instance-method calls
- Local-variable type inference with var
- Compiling and launching a packaged Java program

Hardware details are fictional demonstration values.
````

## 10. Commit and publish

First check repository boundaries:

```bash
git rev-parse --show-toplevel
```

- If it reports **not a git repository**, initialize below.
- If it prints `PhoneDetails`, it is already initialized; skip `git init`.
- If it prints a parent directory, resolve the repository layout before creating a nested repository.

For a new independent repository:

```bash
git init -b main
```

Stage only the intended files, then review:

```bash
git add src/org/phone/ExternalStorage.java src/org/phone/InternalStorage.java .vscode/settings.json .gitignore README.md
git diff --cached --stat
git diff --cached
```

Confirm that no bytecode or backup files appear. Then commit:

```bash
git commit -m "Complete PhoneDetails same-package OOP exercise"
```

Create an empty GitHub repository named `PhoneDetails` under your account. If using the same account as your earlier projects, connect and push with:

```bash
git remote add origin https://github.com/ub-victoire/PhoneDetails.git
git push -u origin main
git status
```

Use those commands only after creating that remote repository and only add `origin` if it does not already exist. Inspect the hosted files before submitting the repository URL.

## 11. Optional cleanup for Question 03

Your previous commit output explicitly shows that `src/org/company/Company.java.save` was committed. Ignore rules do not affect an already tracked file.

If that file is only an unwanted editor backup, you can stop tracking it while keeping the local copy. Run the following **inside EmployeeInformation**, not PhoneDetails:

1. Add `*.java.save` to that project's `.gitignore` if it is not already present.
2. Review the backup and confirm that the intended source is in `Company.java`.
3. Run:

```bash
git rm --cached -- src/org/company/Company.java.save
git add .gitignore
git diff --cached
git commit -m "Stop tracking Java editor backup"
git push origin main
```

`--cached` removes the file from Git's index, not from your working directory. This cleanup affects the current repository state, not earlier commits. It is optional housekeeping, not a prerequisite for Question 04.

## 12. Final checklist

- [ ] Project is named `PhoneDetails` and has its own repository.
- [ ] Both classes declare `package org.phone;`.
- [ ] Public class names match their filenames exactly.
- [ ] `ExternalStorage` defines public instance method `size()`.
- [ ] `InternalStorage` defines public instance methods `processorName()` and `ramSize()`.
- [ ] `main` is inside `InternalStorage`.
- [ ] Both objects are explicitly created inside `main`.
- [ ] All three methods are called through the appropriate objects.
- [ ] Compile-then-class-launch succeeds and prints three lines.
- [ ] No generated files or editor backups are staged.
- [ ] README and source are pushed to the remote repository.

## 13. Understanding checkpoint

Before moving on, explain:

1. Why is no import needed between these two classes?
2. Why can `externalStorage` not call `processorName()`?
3. Why do we create an `InternalStorage` object even though `main` is already inside that class?
4. What is the difference between launching `InternalStorage.java` and launching `org.phone.InternalStorage`?

**Next checkpoint:** share your two `.java` files and the compile/run output. The output verifies the displayed behavior; the source lets us verify the required object creation and class structure.
