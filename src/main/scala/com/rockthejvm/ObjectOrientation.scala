package com.rockthejvm

object ObjectOrientation extends App{
  // java equivalent: public static void main(String[] args)
  
  // class and instance
  class Animal {
    // define fields
    val age:Int = 0
    // define methods
    def eat() = println("I'm eating")
  }
  val anAnimail = new Animal

  // inherinatance
  class Dog(val name:String) extends Animal // constructor definition
  val aDog = new Dog("Lassie")

  // constructor arguments are NOT fields: need to put a val before the cunstructor argement
  aDog.name
  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime

  // absract class
  abstract class WalkingAnimal {
    val hasLegs = true // by default public, can redtirct by protect or private
    def walk(): Unit
  }
  // "interface" = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought:String):Unit // valid method name
  }

  // single-class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  val aCroc = new Crocodile

  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = object method argument, only available for methods with ONE argument

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent

  // anonymous classes
  val dinaosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinasour so I can eat pretty much anything")
  }

  /*
   What you tell the compiler:
  * class Canivore_Anonymous_35728 extends Canivore {
  *   override def eat(animal: Animal): Unit = println("I am a dinasour so I can eat pretty much anything")
  * }
  *
  * val dinosaur = new Canivore_Anonymous_35728
  * */

  // singleton object
  object MySingleton {
    val mySpecialValue = 53278
    def mySpecialMethod(): Int = 5327
    def apply(x:Int): Int = x + 1
  }
  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65) // equivalent to MySingleton.apply(65)
  object Animal { // companions - companion object
   // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal different things
    val canLiveIndefintely = false
  }
  val animalsCanLiveForver = Animal.canLiveIndefintely // "static" fields/methods

  /*
  case clase = lightweight data structrues with some boilerplate
  - sensible equals and has code
  - serialization
  - companion with apply
  - pattern matching
   */
  case class Person(name:String, age:Int)
  // may be counstructed without new
  val bob = Person("Bob",54) // Person.apply("Bob",54)

  // exceptions
  try {
    // code that can throw
    val x:String = null
    x.length
  }catch { // catch(Exception e) {...}
    case e: Exception => "Some faulty error message"
  }finally{
    // execute some code no matter what
  }
  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }
  // using a generinc with a concrete type
  val aList : List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head // int
  val rest = aList.tail
  val aStringList = List("hello","Scala")
  val firstString = aStringList.head // string

  // Point #1: in Scala we usaly operate with IMMUTAble values/objects
  // Any modification to an object must return ANOTHER object
  /*
  Benefits:
  1) works miracles is multithreaded/distributed env
  2) helps to make sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse

  // Point "2": Scala is closest to the 00 ideal
}
