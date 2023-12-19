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

```java
public static int getNumHearts() {
    return numHearts;
}
```

This functions exactly like the other accessor methods we discussed in Unit 5 Section 4, its just static now. Just like static variables, we call these static methods by using the class name and the dot operator, like this example from the NotesStaticMethod1.java file:

```java
System.out.println(Person.getNumHearts());
```

This gives us the ability to print out the value of `numHearts` like we tried to do earlier.

`static` methods aren't just for accessor or mutator methods for `static` variables, they can do anything just like any other method. While it is a strength that these are "higher up" than regular methods since the class runs them instead of an object of the class, this ends up being a weakness as well. Regular methods can access `public` or `private` `static` variables when needed, but this relationship doesn't happen the other way around. `static` methods cannot access instance variables, since if the class is calling the method, they haven't been set up for an object yet (or if they have been set up, there might be 50 versions if there have been 50 objects).

Additionally, methods have the ability to call other methods. Here is an example of such a combination from the `Person.java` file:

```java
public int getYearBorn() {
    return 2023 - age;
}

public void sayYearBorn() {
    System.out.println("I was born in " + getYearBorn());
}
```

This combination allows the `sayYearBorn` method to not worry about the work of calculating the year the `Person` was born in, and instead utilize another method that does that heavy lifting. Since we are already in the class, we can just write the name of the method with parentheses (and parameters if needed), no need for an object or the class name and the dot operator.

`static` methods have a weakness here as well, which is that `static` methods can only call other `static` methods, just like they only have access to other `static` variables. This is especially because normal methods might utilize instance variables, which we already established `static` methods wouldn't have access to.

Here is an example from the `Person.java` file of some `static` methods referencing each other:

```java
public static String hearts() {
    return "All " + species + " have " + numHearts + " heart. ";
}

public static void howManyHearts(int h) {
    if (h == numHearts) {
        System.out.print("Correct! ");
    } else {
        System.out.print("Incorrect! ");
    }
    System.out.println(hearts());
}
```

The `static` `howManyHearts` method is essentially a trivia game, where you guess how many hearts a `Person` has. Notice that at the end, it calls the `static` `hearts` method to print out the fact after they've established whether the answer was correct or incorrect. Here is a sample from the `NotesStaticMethod2.java` file of what this would look like:

```java
Person.howManyHearts(5);
Person.howManyHearts(1);
```

This produces the following outputs:

```
Incorrect! All Human have 1 heart. 
Correct! All Human have 1 heart. 
```

A final important interaction when talking about calling methods with methods: while `static` methods cannot call regular methods, this can work the other way around, so any regular methods can access both `static` variables and `static` methods! Here is an updated `toString` method (this is not a `static` method) that uses both a `static` method and a `static` variable:

```java
public String toString() {
    return hearts() + name + " is a " + age + " year old " + species;
}
```

We can see this in action when we print an object (forcing the `toString` method call automatically). Here is an example from the `NotesStaticMethod3.java` file:

```java
Person person1 = new Person("Mr. G", 25);
Person person2 = new Person("Sam", 33);

System.out.println(person1);
System.out.println(person2);
```

Doing this with two different `Person` objects helps us see the `static` calls come through, as parts of this should be the same every time:

```
All Human have 1 heart. Mr. G is a 25 year old Human
All Human have 1 heart. Sam is a 33 year old Human
```

---

## Assignment

Now that you have gone through the notes for this section, you can check out the `Try.md` and `Try.java` files to try a short assignment using this material.
