package denominator.ultradns.lagacy;

import static denominator.common.Preconditions.checkNotNull;
import static denominator.common.Util.equal;

public class WebForward {
	  private final String requestTo;
	  private final String redirectTo;
	  private final String zoneName;
	  private final String forwardType;
	  private final String guid;

	  WebForward(String requestTo, String redirectTo, String zoneName, String forwardType, String guid) {
		    this.requestTo = checkNotNull(requestTo, "requestTo");
		    this.redirectTo = checkNotNull(redirectTo, "redirectTo");
		    this.zoneName = checkNotNull(zoneName, "zoneName");
		    this.forwardType = checkNotNull(forwardType, "forwardType");
		    this.guid = guid;
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

	public String forwardType() {
		return forwardType;
	}

	public String guid() {
		return guid;
	}

	  @Override
	  public boolean equals(Object obj) {
	    if (obj instanceof WebForward) {
	    	WebForward other = (WebForward) obj;
	      return equal(zoneName(), other.zoneName())
	             && requestTo().equals(other.requestTo())
	             && redirectTo().equals(other.redirectTo())
	             && forwardType().equals(other.forwardType())
	             && guid().equals(other.guid());
	    }
	    return false;
	  }

	  @Override
	  public int hashCode() {
	    int result = 17;
	    result = 31 * result + zoneName().hashCode();
	    result = 31 * result + requestTo().hashCode();
	    result = 31 * result + redirectTo().hashCode();
	    result = 31 * result + forwardType().hashCode();
	    result = 31 * result + guid().hashCode();
	    return result;
	  }

	  @Override
	  public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("WebForward [");
	    builder.append("zoneName=").append(zoneName());
	    builder.append(", ").append("requestTo=").append(requestTo());
	    builder.append(", ").append("redirectTo=").append(redirectTo());
	    builder.append(", ").append("forwardType=").append(forwardType());
	    builder.append(", ").append("guid=").append(guid());
	    builder.append("]");
	    return builder.toString();
	  }

	  /**
	   * @param id    nullable, corresponds to {@link #id()}
	   * @param name  corresponds to {@link #name()}
	   * @param ttl   corresponds to {@link #ttl()}
	   * @param email corresponds to {@link #email()}
	   */
	  public static WebForward create(String zoneName, String requestTo, String redirectTo, String forwardType, String guid) {
	    return new WebForward(zoneName, requestTo, redirectTo, forwardType, guid);
	  }

}
