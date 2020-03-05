import org.scalacheck.Arbitrary

object Test {
  // 45s
  /*def scalacheckS: Unit = {
    import org.scalacheck._
    import org.scalacheck.ScalacheckShapeless._
    implicitly[Arbitrary[Outer1]]
    implicitly[Arbitrary[Outer2]]
    implicitly[Arbitrary[Outer3]]
    implicitly[Arbitrary[Outer4]]
    implicitly[Arbitrary[Outer5]]
  }*/

  // 3s
  /*def scalacheckM: Unit = {
    import org.scalacheck._
    import magnolify.scalacheck.auto._
    implicitly[Arbitrary[Outer1]]
    implicitly[Arbitrary[Outer2]]
    implicitly[Arbitrary[Outer3]]
    implicitly[Arbitrary[Outer4]]
    implicitly[Arbitrary[Outer5]]
  }*/

  // 59s
  /*def datatypeS: Unit = {
    import shapeless.datatype.avro._
    import shapeless.datatype.bigquery._
    import shapeless.datatype.datastore._
    import shapeless.datatype.tensorflow._
    AvroType[Outer1].fromGenericRecord(null)
    AvroType[Outer2].fromGenericRecord(null)
    AvroType[Outer3].fromGenericRecord(null)
    AvroType[Outer4].fromGenericRecord(null)
    AvroType[Outer5].fromGenericRecord(null)
    BigQueryType[Outer1].fromTableRow(null)
    BigQueryType[Outer2].fromTableRow(null)
    BigQueryType[Outer3].fromTableRow(null)
    BigQueryType[Outer4].fromTableRow(null)
    BigQueryType[Outer5].fromTableRow(null)
    DatastoreType[Outer1].fromEntity(null)
    DatastoreType[Outer2].fromEntity(null)
    DatastoreType[Outer3].fromEntity(null)
    DatastoreType[Outer4].fromEntity(null)
    DatastoreType[Outer5].fromEntity(null)
  }*/

  // 8s
  /*def datatypeM1: Unit = {
    import magnolify.avro._
    import magnolify.bigquery._
    AvroType[Outer1]
    AvroType[Outer2]
    AvroType[Outer3]
    AvroType[Outer4]
    AvroType[Outer5]
    implicit val intTrf = TableRowField.from[Long](_.toInt)(_.toLong)
    implicit val floatTrf = TableRowField.from[Double](_.toFloat)(_.toDouble)
    TableRowType[Outer1]
    TableRowType[Outer2]
    TableRowType[Outer3]
    TableRowType[Outer4]
    TableRowType[Outer5]
  }

  def datatypeM2: Unit = {
    import magnolify.datastore._
    implicit val intEf = EntityField.from[Long](_.toInt)(_.toLong)
    implicit val floatEf = EntityField.from[Double](_.toFloat)(_.toDouble)
    EntityType[Outer1]
    EntityType[Outer2]
    EntityType[Outer3]
    EntityType[Outer4]
    EntityType[Outer5]
  }*/
}
