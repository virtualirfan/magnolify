package magnolia.test

import com.google.protobuf.ByteString
import org.joda.time.Instant
import org.scalacheck._

object Simple {
  case class Integers(i: Int, l: Long)
  case class Numbers(i: Int, l: Long, f: Float, d: Double, bi: BigInt, bd: BigDecimal)
  case class Required(b: Boolean, i: Int, s: String)
  case class Nullable(b: Option[Boolean], i: Option[Int], s: Option[String])
  case class Repeated(b: List[Boolean], i: List[Int], s: List[String])
  case class Nested(b: Boolean, i: Int, s: String, r: Required)
  case class Custom(b: ByteString, i: Instant)

  object Custom {
    implicit val arbByteString: Arbitrary[ByteString] =
      Arbitrary(Gen.alphaNumStr.map(ByteString.copyFromUtf8))
    implicit val arbInstant: Arbitrary[Instant] =
      Arbitrary(Gen.chooseNum(0, Int.MaxValue).map(new Instant(_)))
    implicit val coByteString: Cogen[ByteString] = Cogen(_.hashCode())
    implicit val coInstant: Cogen[Instant] = Cogen(_.getMillis)
  }
}
