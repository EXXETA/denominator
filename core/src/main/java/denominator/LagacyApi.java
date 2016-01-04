package denominator;


public interface LagacyApi {
	static interface Factory {

	    /**
	     * @return null if this feature isn't supported on the provider.
	     */
		LagacyApi create(String id);
	  }
}
