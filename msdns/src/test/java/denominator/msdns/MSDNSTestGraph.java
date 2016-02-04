package denominator.msdns;

import denominator.DNSApiManagerFactory;

import static feign.Util.emptyToNull;
import static java.lang.System.getProperty;

public class MSDNSTestGraph extends denominator.TestGraph {

  private static final String url = emptyToNull(getProperty("msdns.url"));
  private static final String zone = emptyToNull(getProperty("msdns.zone"));

  public MSDNSTestGraph() {
    super(DNSApiManagerFactory.create(new MSDNSProvider(url)), zone);
  }
}
