package denominator.msdns;

import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

import denominator.common.Filter;
import denominator.model.ResourceRecordSet;
import denominator.profile.WeightedResourceRecordSetApi;

final class MSDNSWeightedResourceRecordSetApi extends MSDNSAllProfileResourceRecordSetApi
    implements WeightedResourceRecordSetApi {

  private static final Filter<ResourceRecordSet<?>>
      IS_WEIGHTED =
      new Filter<ResourceRecordSet<?>>() {
        @Override
        public boolean apply(ResourceRecordSet<?> in) {
          return in != null && in.weighted() != null;
        }
      };

  private final SortedSet<Integer> supportedWeights;

  MSDNSWeightedResourceRecordSetApi(String zoneName,
                                   SortedSet<Integer> supportedWeights) {
    super( zoneName, IS_WEIGHTED);
    this.supportedWeights = supportedWeights;
  }

  @Override
  public SortedSet<Integer> supportedWeights() {
    return supportedWeights;
  }

  @Override
  public void put(ResourceRecordSet<?> rrset) {
    put(IS_WEIGHTED, rrset);
  }
}
