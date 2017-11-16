import de.qaware.chronix.spark.api.java.ChronixSparkContext;
import de.qaware.chronix.spark.api.java.ChronixRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.solr.client.solrj.SolrQuery;
import de.qaware.chronix.storage.solr.ChronixSolrCloudStorage;

public class Cs744ChronixSparkBenchMarks {
  public static void main( String[] args ) {
    // Create ChronixSparkContext from a SparkContext/JavaSparkContext
    SparkConf conf = new SparkConf().setAppName("CS744 Chronix Spark Benchmarks");
    ChronixSparkContext csc = new ChronixSparkContext(new JavaSparkContext(conf));
    SolrQuery query = new SolrQuery("metric:\"java.lang:type=Memory/HeapMemoryUsage/used\"");
    ChronixRDD rdd;
    try {
      rdd = csc.query(query,
                        "128.104.222.123:2181", // Zookeeper host
                        "jp_us_exchange_rates", // Solr Collection for Chronix
                        new ChronixSolrCloudStorage());
      System.out.println("MIN: " + rdd.min());
    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println( "Hello World!" );
  }
}
