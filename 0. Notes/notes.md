# `static` Variables and Methods

In Unit 5 Section 1 we introduced the idea of class variables being different from instance variables, and the primary difference in the code was the keyword `static`. This keyword tells a class that this thing is meant to be utilized overall for the whole class, instead of being used by individual objects.

---

## `static` Variables

We will often refer to **`static` variables** as **`class` variables** because they change the use of the variable to be for the whole class. When creating a variable, the keyword `static` would go between the `public` or `private` indicator and the type of the variable. Here are a couple of examples from the `Person.java` file:

```java
public static String species = "Human";
private static int numHearts = 1;
```

You'll notice that both of these variables are things that would be true of any `Person`, which is the whole point of `static` variables. They describe a value that should not change between individual `Person` objects. Since they wouldn't change between people, it would make sense then not to make a copy of these variables for each `Person` like we normally do with instance variables.

How do we use these variables and how do they interact with `public` versus `private`? First of all, we can access these variables just like we did with things from the `Math` `class` from Unit 2 Section 9. Instead of making an object, we can just use the class name and the dot operator, so for this it would look like `Person.species` (since this is a variable, there are no parentheses like there were with methods). Here is an example from the `NotesStaticVariable1.java` file that demonstrates this:

```java
System.out.println(Person.species);
System.out.println(Person.numHearts);
```

There is an issue though, as this file has an error with `Person.numHearts` reading "The field Person.numHearts is not visible". This is the same error we've been seeing throughout this unit with access to `private` data, and if you look at our declarations above, we declared `numHearts` as `private`. That means in order to access `numHearts`, we would need a method, but to use a method, we need an object, right?

---

## `static` Methods

Referencing our use of the `Math` `class` from Unit 2 Section 9 again, we were calling methods (actions) from the `Math` `class` without an object, because they were **`static` methods**. Just like variables can be made `static` and therefore not made to be used by individual objects, so too can methods! This would help us tremendously with the `numHearts` issue we were running into above. Here is a `static` accessor method to ensure we can access `numHearts` to print out its value from the `Person.java` file:






















# Writing Methods

In order to write methods to accomplish different tasks, there are a lot of minor interactions we need to understand. These minor interactions can have major impacts on programs when not acknowledged, and so are important for us to go through.

---

## Pass by Value

In Unit 5 Section 2, we discussed the idea of **pass by value**, which is a rule Java follows when it uses parameters. When you call a method (or constructor) that has parameters, you provide information for those parameters in the form of values. Java is careful in how it handles this, and it makes copies of these values that you provide to fill in its parameter variables with to try and protect the original information.

To demonstrate the interactions, I have created the `TempMethods` class in the `TempMethods.java` file. Here is one example from the `TempMethods.java` file:

```java
public void modifyInt(int i) {
    i += 1;
    System.out.println(i);
}
```

When we read this method, it is `void`, so it will not `return` any information. Instead, it takes an `int` parameter nicknamed `i`, increments its value by `1`, and then prints out the value. The behavior of this `print` statement is pretty straight-forward: if `i` was `5` originally, it would print out `6`, or if `i` was `15`, it would print out `16`. The interaction we have to be worried about though, is whether this would change the value we used when we called this method. Here is an example from the `NotesPass1.java` file:

```java
TempMethods temp = new TempMethods();
int number = 5;

temp.modifyInt(number);

System.out.println(number); // What will this produce?
```

Here, we make an object out of our class to be able to use it, and we make a variable called `number` to store our sample value in. We know from reading the method above that the call `temp.modifyInt(number)` will initialize `i` with the value `number` has, which is `5`, and so ultimately prints out `6`. The question though, is if the value of `number` was also incremented by `1` in this process. The answer is no, it did not, since `i` is the primitive type `int`, as shown in the output:

```
6
5
```

In Unit 1, when we introduced variables and the primitive types, we made a special point about how primitive types are stored. The value is stored directly in the variable. This means that when Java copies the *value* for a parameter, it copies the data itself. So it made a copy of the value `5` in this example to work with, leaving the original `5` stored in `number` untouched. This interaction is consistent for primitive data type parameters: they will never be able to modify the original because the value was copied.

This copy behavior takes on another form when we discuss objects though. Objects are not stored directly in their variables: instead, their memory location is stored in the variable. This means that if we copy the value of an object from a variable, we will copy its location. This means that the original variable and our new parameter will both point to the same memory location: the original object, making them **aliases** of each other. This means changes made by either one *could* affect the other!

How do we know the when it can affect the other? This goes back to Unit 2 when we introduced the `String` object, and the fact that it is **immutable**, meaning it couldn't be changed, just completed replaced. Objects in Java can either be **mutable** or **immutable**, meaning they can be changed, or they cannot be changed. `String` objects are immutable, which means that you can't actually modify their value, just replace them with a whole new `String`, even if it is based on the old value. The classes we are creating and working with are mutable, which means they can be modified, and so our aliases can have an impact.

The great news is we can use objects from our new classes as parameters in methods to learn more about this interaction. Here are another two methods from `TempMethods.java` that will put this to the test:

```java
public void modifyString(String s) {
    s += "!";
    System.out.println(s);
}

public void modifyPerson(Person p) {
    p.setName("New Name");
    System.out.println(p.getName());
}
```

These methods look similar in design to our first example, as they just modify the parameters and print them, but this interaction is dangerous, as the parameters will be an alias for the original objects! For the first method `modifyString`, we don't have to worry, since `String` objects are immutable, so instead of modifying the `String` `s`, it is completely replaced with its old text and an exclamation point, now with a different memory location. For the second method `modifyPerson` on the other hand, `Person` is a mutable object, so using `setName` will change the original object, and we'll see that reflected in our test. We can do a similar test to our first one, as demonstrated in the `NotesPass2.java` file:


```java
TempMethods temp = new TempMethods();
String str = "Hello, World";

temp.modifyString(str);
System.out.println(str); // What will this produce?

Person person1 = new Person("Mr. G", 25);

temp.modifyPerson(person1);
System.out.println(person1.getName()); // What will this produce?
```

For the `String` example, our output should print `Hello, World!` when the method runs and then `Hello, World` after the method, since it replaced the `String` and couldn't modify the original. For the `Person` example, our output should print `New Name` when the method runs, and then `New Name` after the method, since it modified the original object and the name change stayed. Here is the output:

```
Hello, World!
Hello, World
New Name
New Name
```

In summary, **pass by value** means that a method cannot modify parameters that are based on primitive values or immutable objects, but can modify parameters that are based on mutable objects.

---

## Access to `private` Data

As demonstrated with accessor methods in Unit 5 Section 4 and mutator methods in Unit 5 Section 5, we can use methods to access things like variables that are marked as `private`. This isn't always the case though, as we start to mix classes together with different methods. We can see this in action going back to our `modifyPerson` method from the `TempMethods.java` file:

```java
public void modifyPerson(Person p) {
    p.setName("New Name");
    System.out.println(p.getName());
}
```

Following our earlier line of reasoning, it would make sense to say that since this is a method, we should just be able to use `p.name = "New Name";` instead of `p.setName("New Name");`. Unfortunately, if we do that, we get an error like we saw in the last section that reads "The field `Person.name` is not visible". So even though this is a method, it doesn't have access to `private` data. This comes down entirely to what class we are in. This method is in the `TempMethods` class, not the `Person` class, so it cannot access `private` data from a `Person` object. Any method in the `Person` class though, would have access to this data *even if* it was a parameter. Here is a new example from the `Person.java` file:

```java
public void copyName(Person p) {
    name = p.name;
}
```

While this works, there is some new confusion that can arise. Whose name is `name` referring to versus whose name is `p.name` referring to? Even though we only see the `Person` `p` here, we have to remember that this method would have to be called by a `Person`, which is who `name` is for, while the parameter `p` is responsible for `p.name`. Let's look at this in an example from the `NotesPrivate1.java` file:

```java
Person person1 = new Person("Mr. G", 25);
Person person2 = new Person("Sam", 34);
System.out.println(person1);
System.out.println(person2);
System.out.println();

person1.copyName(person2);

System.out.println(person1);
System.out.println(person2);
```

In this example, we create two people referred to as `person1` and `person2`. When we print those people out, it calls the `toString` method and prints a sentence with their name and age. Then we have `person1.copyName(person2);`, which calls `copyName` as `person1` with `person2` as a parameter. This means that looking back at the method code, `name` is for `person1` and `p.name` is for `person2`. This means that `person1`'s `name` is changed to whatever `person2`'s `name` is, so it should be changed to `"Sam"`. This should be reflected in the `toString` printed outputs afterwards. Here is the output:

```
Mr. G is 25 years old.
Sam is 34 years old.

Sam is 25 years old.
Sam is 34 years old.
```

Because this method is in the `Person` class, we can get direct access to `private` instance variables of a `Person`, even when they are parameters in the method!

An important note about good programming practices: With this interaction, we could easily have modified the `Person` object `p` in our method, maybe copying the names in the other direction. It is considered poor practice to modify mutable objects that have been passed in as parameters unless required by the specifications of the project. 

---

## General Methods

With these interactions in tow, we can make a method to do whatever we want, to a certain extent. We can use information provided by the header of a method to determine what to do. For example, here is an incomplete method from the `TempMethods.java` file:

```java
public int addNumbers(int a, int b) {
    // Not Shown.
}
```

While oftentimes you are given way more information than this, we can totally figure out what this method needs to do using the accompanying information. Here is what we can pull out of this method header to determine what to do:
- The `return` type is `int`, so they expect us to `return` an `int` value at some point.
- The name of the method is `addNumbers`, so we can expect that we should be adding numbers together. When combined with our `return` type, it would make sense to return an `int` final answer after we have added numbers together
- The parameter list is `(int a, int b)`, so we are given two numbers to work with. When combined with the previous two pieces of information, it seems like these are the values that should be added together and then returned as an answer!

Using this information, we could fill in this method with the following:

```java
public int addNumbers(int a, int b) {
    return a + b;
}
```

We could consider these general ideas when trying to determine how to fill in a method:
- The `return` type indicates whether or not you have a "final answer". `void` would mean there is no "final answer", or that it is printed, while any other type is a clue as to what you are supposed to do.
- The name of the method could indicate what kind of process is going on. As programmers, we try to name our methods to describe their uses, so that you aren't remembering really arbitary names like `duck` for a method that adds numbers together.
- The parameter list can tell you more about that process you are working out. Adding numbers is great, but returning an `int` only makes sense if the numbers being added are `int` values as well, if they are `double` values, this might change your approach. You could also not have parameters, indicating that this might be more reliant on instance variables in the class as opposed to parameters.

---

## Assignment

Now that you have gone through the notes for this section, you can check out the `Try.md` and `Try.java` files to try a short assignment using this material.
