package magnolify.jmh

import java.util.concurrent.TimeUnit

import magnolify.test.Simple._
import org.openjdk.jmh.annotations._
import org.scalacheck._

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class ScalaCheckBenchXS {
  import MagnolifyBench._
  import org.scalacheck.ScalacheckShapeless._
  private val arb = implicitly[Arbitrary[Nested]]
  private val co = implicitly[Cogen[Nested]]
  @Benchmark def arbitrary: Option[Nested] = arb.arbitrary(prms, seed)
  @Benchmark def cogen: rng.Seed = co.perturb(rng.Seed(0), nested)
}

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class AvroBenchXS {
  import shapeless.datatype.avro._
  import org.apache.avro.generic.GenericRecord
  import MagnolifyBench._
  private val avroType = AvroType[Nested]
  private val genericRecord = avroType.toGenericRecord(nested)
  @Benchmark def avroTo: GenericRecord = avroType.toGenericRecord(nested)
  @Benchmark def avroFrom: Option[Nested] = avroType.fromGenericRecord(genericRecord)
}

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class TableRowBenchXS {
  import com.google.api.services.bigquery.model.TableRow
  import shapeless.datatype.bigquery._
  import MagnolifyBench._
  private val tableRowType = BigQueryType[Nested]
  private val tableRow = tableRowType.toTableRow(nested)
  @Benchmark def tableRowTo: TableRow = tableRowType.toTableRow(nested)
  @Benchmark def tableRowFrom: Option[Nested] = tableRowType.fromTableRow(tableRow)
}

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class EntityBenchXS {
  import com.google.datastore.v1.Entity
  import shapeless.datatype.datastore._
  import MagnolifyBench._
  private val entityType = DatastoreType[Nested]
  private val entity = entityType.toEntity(nested)
  @Benchmark def entityTo: Entity.Builder = entityType.toEntityBuilder(nested)
  @Benchmark def entityFrom: Option[Nested] = entityType.fromEntity(entity)
}
