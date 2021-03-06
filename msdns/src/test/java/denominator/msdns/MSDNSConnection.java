package denominator.msdns;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import denominator.DNSApiManager;
import denominator.Denominator;
import feign.Logger;

import static denominator.CredentialsConfiguration.credentials;
import static feign.Util.emptyToNull;
import static java.lang.System.getProperty;

public class MSDNSConnection {

  final DNSApiManager manager;
  final String mutableZone;

  MSDNSConnection() {
    String username = emptyToNull(getProperty("ultradns.username"));
    String password = emptyToNull(getProperty("ultradns.password"));
    if (username != null && password != null) {
      manager = create(username, password);
    } else {
      manager = null;
    }
    mutableZone = emptyToNull(getProperty("ultradns.zone"));
  }

  static DNSApiManager create(String username, String password) {
    MSDNSProvider provider = new MSDNSProvider(emptyToNull(getProperty("msdns.url")));
    return Denominator.create(provider, credentials(username, password), new Overrides());
  }

  @Module(overrides = true, library = true)
  static class Overrides {

    @Provides
    @Singleton
    Logger.Level provideLevel() {
      return Logger.Level.FULL;
    }

    @Provides
    @Singleton
    Logger provideLogger() {
      return new Logger.JavaLogger().appendToFile("build/http-wire.log");
    }
  }
}
