package denominator.ultradns;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import denominator.DNSApiManagerFactory;
import denominator.Live;

import static feign.Util.emptyToNull;
import static java.lang.System.getProperty;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    UltraDNSLiveTest.CheckConnectionLiveTest.class,
    UltraDNSLiveTest.ReadOnlyLiveTest.class,
    UltraDNSLiveTest.WriteCommandsLiveTest.class,
    UltraDNSLiveTest.RoundRobinWriteCommandsLiveTest.class,
    UltraDNSLiveTest.GeoReadOnlyLiveTest.class,
    UltraDNSLiveTest.GeoWriteCommandsLiveTest.class
})
public class UltraDNSLiveTest {

  private static final String url = emptyToNull(getProperty("ultradns.url"));
  private static final String zone = emptyToNull(getProperty("ultradns.zone"));

  public static class TestGraph extends denominator.TestGraph {

    public TestGraph() {
      super(DNSApiManagerFactory.create(new UltraDNSProvider(url)), zone);
    }
  }

  @Live.UseTestGraph(TestGraph.class)
  public static class CheckConnectionLiveTest extends denominator.CheckConnectionLiveTest {

  }

  @Live.UseTestGraph(TestGraph.class)
  public static class ReadOnlyLiveTest extends denominator.ReadOnlyLiveTest {

  }

  @Live.UseTestGraph(TestGraph.class)
  public static class WriteCommandsLiveTest extends denominator.WriteCommandsLiveTest {

  }

  @Live.UseTestGraph(TestGraph.class)
  public static class RoundRobinWriteCommandsLiveTest
      extends denominator.RoundRobinWriteCommandsLiveTest {

  }

  @Live.UseTestGraph(TestGraph.class)
  public static class GeoReadOnlyLiveTest extends denominator.profile.GeoReadOnlyLiveTest {

  }

  @Live.UseTestGraph(TestGraph.class)
  public static class GeoWriteCommandsLiveTest
      extends denominator.profile.GeoWriteCommandsLiveTest {
  }
}
