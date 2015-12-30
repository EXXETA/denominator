package denominator.ultradns.lagacy;

import static denominator.common.Preconditions.checkNotNull;
import static denominator.common.Util.equal;

public class WebForward {
	  private final String requestTo;
	  private final String redirectTo;
	  private final String zoneName;

	  WebForward(String requestTo, String redirectTo, String zoneName) {
		    this.requestTo = checkNotNull(requestTo, "requestTo");
		    this.redirectTo = checkNotNull(redirectTo, "redirectTo");
		    this.zoneName = checkNotNull(zoneName, "zoneName");
	  }

	  /**
	   * The potentially transient and opaque string that uniquely identifies the zone. This may be null
	   * when used as an input object.
	   *
	   * @since 4.5
	   */
	  public String zoneName() {
	    return zoneName;
	  }

	  public String requestTo() {
		    return requestTo;
		  }

	  public String redirectTo() {
		    return redirectTo;
		  }


	  @Override
	  public boolean equals(Object obj) {
	    if (obj instanceof WebForward) {
	    	WebForward other = (WebForward) obj;
	      return equal(zoneName(), other.zoneName())
	             && requestTo().equals(other.requestTo())
	             && redirectTo().equals(other.redirectTo());
	    }
	    return false;
	  }

	  @Override
	  public int hashCode() {
	    int result = 17;
	    result = 31 * result + zoneName().hashCode();
	    result = 31 * result + requestTo().hashCode();
	    result = 31 * result + redirectTo().hashCode();
	    return result;
	  }

	  @Override
	  public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("WebForward [");
	    builder.append("zoneName=").append(zoneName());
	    builder.append(", ").append("requestTo=").append(requestTo());
	    builder.append(", ").append("redirectTo=").append(redirectTo());
	    builder.append("]");
	    return builder.toString();
	  }

	  /**
	   * @param id    nullable, corresponds to {@link #id()}
	   * @param name  corresponds to {@link #name()}
	   * @param ttl   corresponds to {@link #ttl()}
	   * @param email corresponds to {@link #email()}
	   */
	  public static WebForward create(String zoneName, String requestTo, String redirectTo) {
	    return new WebForward(zoneName, requestTo, redirectTo);
	  }

}
