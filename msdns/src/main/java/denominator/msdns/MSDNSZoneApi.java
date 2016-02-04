package denominator.msdns;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListSet;

import denominator.model.ResourceRecordSet;
import denominator.model.Zone;
import denominator.model.rdata.SOAData;

import static denominator.common.Preconditions.checkState;
import static denominator.common.Util.filter;
import static denominator.model.ResourceRecordSets.nameAndTypeEqualTo;
import static denominator.model.ResourceRecordSets.ns;
import static denominator.model.ResourceRecordSets.soa;
import static denominator.model.Zones.nameEqualTo;
import static java.util.Arrays.asList;

final class MSDNSZoneApi implements denominator.ZoneApi {

  private static final Comparator<ResourceRecordSet<?>> TO_STRING =
      new Comparator<ResourceRecordSet<?>>() {
        @Override
        public int compare(ResourceRecordSet<?> arg0, ResourceRecordSet<?> arg1) {
          return arg0.toString().compareTo(arg1.toString());
        }
      };


  MSDNSZoneApi() {
    put(Zone.create("denominator.io.", "denominator.io.", 86400, "nil@denominator.io."));
  }

  @Override
  public Iterator<Zone> iterator() {
    final Iterator<Entry<String, Collection<ResourceRecordSet<?>>>>
        delegate = null;// data.entrySet().iterator();
    return new Iterator<Zone>() {
      @Override
      public boolean hasNext() {
        return delegate.hasNext();
      }

      @Override
      public Zone next() {
        Entry<String, Collection<ResourceRecordSet<?>>> next = delegate.next();
        String name = next.getKey();
        Iterator<ResourceRecordSet<?>> soas =
            filter(next.getValue().iterator(), nameAndTypeEqualTo(name, "SOA"));

        checkState(soas.hasNext(), "SOA record for zone %s was not present", name);
        ResourceRecordSet<SOAData> soa = (ResourceRecordSet<SOAData>) soas.next();
        SOAData soaData = soa.records().get(0);
        return Zone.create(name, name, soa.ttl(), soaData.rname());
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("remove");
      }
    };
  }

  @Override
  public Iterator<Zone> iterateByName(String name) {
    return filter(iterator(), nameEqualTo(name));
  }

  @Override
  public String put(Zone zone) {
    
    return zone.name();
  }

  @Override
  public void delete(String name) {
	  //TODO
  }
}
