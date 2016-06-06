package denominator.ultradns;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import denominator.LagacyApi;
import denominator.ultradns.lagacy.WebForward;

public final class UltraDNSLagacyApi implements LagacyApi {

	private final UltraDNS api;
	private final String zoneName;

	@Inject
	UltraDNSLagacyApi(UltraDNS api, String zoneName) {
		this.api = api;
		this.zoneName = zoneName;
	}

	public void putWebForward(WebForward webForward) {
		WebForward existingWebForward = null;
		for(WebForward wf : webForwards()){
			if (wf.requestTo().equals(webForward.requestTo())){
				existingWebForward=wf;
			}
		}
		if (existingWebForward==null){
			api.addWebForward(zoneName, webForward.requestTo(), webForward.redirectTo(), webForward.forwardType());
		}else {
			api.updateWebForward(existingWebForward.guid(), webForward.requestTo(), webForward.redirectTo(),
					webForward.forwardType());
		}

	}

	public Iterator<WebForward> getWebForwards() {
		Iterator<WebForward> forwards = webForwards().iterator();
		return forwards;
	}

	private List<WebForward> webForwards() {
		return api.queryWebForwards(zoneName);
	}

	public void deleteWebForwardByRequestTo(String requestTo) {
		for (WebForward wf : webForwards()) {
			if (wf.requestTo().equals(requestTo)) {
				api.deleteWebForward(wf.guid());
			}
		}
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
