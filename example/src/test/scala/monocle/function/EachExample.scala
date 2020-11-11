package monocle.function

import monocle.MonocleSuite

import scala.collection.immutable.SortedMap
import cats.data.OneAnd

class EachExample extends MonocleSuite {
  test("Each can be used on Option") {
    assertEquals((Option(3) applyTraversal each modify (_ + 1)), Some(4))
    assertEquals(((None: Option[Int]) applyTraversal each modify (_ + 1)), None)
  }

  test("Each can be used on List, IList, Vector, Stream and OneAnd") {
    assertEquals((List(1, 2) applyTraversal each modify (_ + 1)), List(2, 3))
    assertEquals((Vector(1, 2) applyTraversal each modify (_ + 1)), Vector(2, 3))
    assertEquals((OneAnd(1, List(2, 3)) applyTraversal each modify (_ + 1)), OneAnd(2, List(3, 4)))
  }

  test("Each can be used on Map, IMap to update all values") {
    assertEquals(
      (SortedMap("One" -> 1, "Two" -> 2) applyTraversal each modify (_ + 1)),
      SortedMap("One" -> 2, "Two" -> 3)
    )
  }

  test("Each can be used on tuple of same type") {
    assertEquals(((1, 2) applyTraversal each modify (_ + 1)), ((2, 3)))
    assertEquals(((1, 2, 3) applyTraversal each modify (_ + 1)), ((2, 3, 4)))
    assertEquals(((1, 2, 3, 4, 5, 6) applyTraversal each modify (_ + 1)), ((2, 3, 4, 5, 6, 7)))
  }
}
