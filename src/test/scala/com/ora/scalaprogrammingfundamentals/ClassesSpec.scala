package com.ora.scalaprogrammingfundamentals

import org.scalatest.{FunSuite, Matchers}

class ClassesSpec extends FunSuite with Matchers {
  test(
    """Create a class, and the class should be
      |  instantiable with the elements, but without a val
      |  I cannot get information. THIS IS WONDERFUL FOR
      |  UNIT TESTING""".stripMargin) {

    val stamp = new Stamp("Jimi Hendrix", 2014, () => 2018)
    stamp.theme should be ("Jimi Hendrix")
    stamp.year should be (2014)
    stamp.age should be (4)
  }

  test(
    """Use the companion object to create the stamp.
      |  This test will fail on Jan 1, 2019.
      |  This is moreso an integration""".stripMargin) {
    val stamp = Stamp("Jimi Hendrix", 2014)
    stamp.theme should be ("Jimi Hendrix")
    stamp.year should be (2014)
    stamp.age should be (4) //fail on 2019
  }

  test(
    """Case classes have automatic functionality for getters, toString,
          equals, hashCode, apply,
          and basic pattern matching""".stripMargin) {
    val computer = Computer("Commodore", "64", 1983)
    computer.make should be ("Commodore")
    computer.model should be ("64")
  }

  test("Preconditions can be made with require and are used in the class") {
    val exception1 = the [IllegalArgumentException] thrownBy {
      Stamp("", 1980)
    }

    exception1.getMessage should be ("requirement failed: Theme cannot be empty")
  }

  test("Subclassing in Scala") {
    val baseballCard = new BaseballCard(1952, "Topps",
      "Mickey Mantle", "American", "Eastern")

    baseballCard.year should be (1952)
    baseballCard.manufacturer should be ("Topps")
    baseballCard.playerName should be ("Mickey Mantle")
  }

  test("Abstract Classes in Scala") {
    val baseballCard = new BaseballCard(1952, "Topps",
      "Mickey Mantle", "American", "Eastern")

    baseballCard shouldBe a [Collectable]
    baseballCard shouldBe a [SportsCard]
    baseballCard shouldBe a [BaseballCard]
  }

  test("Generic Classes in Scala") {
    val box = Box(new BaseballCard(1952, "Topps",
      "Mickey Mantle", "American", "Eastern"))

    val box2 = Box(10)

    box.contents.manufacturer should be ("Topps")
    box2.contents + 20 should be (30)
  }

  test("Generic Classes in Scala with our own map") {
    val box = Box(new BaseballCard(1952, "Topps",
      "Mickey Mantle", "American", "Eastern"))

    val box2 = Box(10)

    val result: Box[String] = box.map(bc => bc.league)
    result should be (Box("American"))


  }
}
