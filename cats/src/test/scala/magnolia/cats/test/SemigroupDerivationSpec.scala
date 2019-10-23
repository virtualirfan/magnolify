package magnolia.cats.test

import java.net.URI
import java.time.Duration

import cats._
import cats.instances.all._
import cats.kernel.laws.discipline._
import magnolia.cats.auto._
import magnolia.scalacheck.auto._
import magnolia.test.SerializableUtils
import magnolia.test.Simple._
import org.scalacheck._

import scala.reflect._

object SemigroupDerivationSpec extends Properties("SemigroupDerivation") {
  private def test[T: Arbitrary : ClassTag : Eq : Semigroup]: Unit = {
    SerializableUtils.ensureSerializable(implicitly[Semigroup[T]])
    val name = classTag[T].runtimeClass.getSimpleName
    include(SemigroupTests[T].semigroup.all, s"$name.")
  }

  test[Integers]

  {
    implicit val eqArray: Eq[Array[Int]] = Eq.by(_.toList)
    implicit val sgArray: Semigroup[Array[Int]] = Semigroup.instance(_ ++ _)
    test[Collections]
  }

  {
    implicit val sgBool: Semigroup[Boolean] = Semigroup.instance(_ ^ _)
    test[Required]
    test[Nullable]
    test[Repeated]
    test[Nested]
  }

  {
    import Custom._
    implicit val eqUri: Eq[URI] = Eq.by(_.toString)
    implicit val eqDuration: Eq[Duration] = Eq.by(_.toMillis)
    implicit val sgUri: Semigroup[URI] =
      Semigroup.instance((x, y) => URI.create(x.toString + y.toString))
    implicit val sgDuration: Semigroup[Duration] = Semigroup.instance(_ plus _)
    test[Custom]
  }
}
