package denominator.ultradns;

import java.util.Iterator;

import javax.inject.Inject;

import denominator.LagacyApi;
import denominator.ultradns.lagacy.WebForward;

public final class UltraDNSLagacyApi implements LagacyApi {

	private final UltraDNS api;
	private final String zoneName;

	@Inject
	UltraDNSLagacyApi(UltraDNS api, String zoneName){
		this.api = api;
		this.zoneName = zoneName;
	}

	public void addWebForward(String requestTo, String redirectTo) {
		api.addWebForward(zoneName, requestTo, redirectTo);
	}

	public Iterator<WebForward> getAllWebForwardsOfZone() {
		// this will list all basic or RR pool records.
		Iterator<WebForward> forwards = api.getWebForwardsOfZone(zoneName).iterator();
		return forwards;
	}

	
	static final class Factory implements denominator.LagacyApi.Factory {

		private final UltraDNS api;

		@Inject
		Factory(UltraDNS api) {
			this.api = api;
		}

		@Override
		public LagacyApi create(String name) {
			return new UltraDNSLagacyApi(api, name);
		}
	}

}
