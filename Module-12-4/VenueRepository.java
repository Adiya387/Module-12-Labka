package module12Home2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class VenueRepository {
    private  List<Venue> allVenues = new ArrayList<>();

    public VenueRepository() {
        allVenues.add(new Venue("V1", "Большой конференц-зал", 200));
        allVenues.add(new Venue("V2", "Малый банкетный зал", 80));
        allVenues.add(new Venue("V3", "Терраса на крыше", 50));
    }

    public List<Venue> findAvailableVenues(LocalDate date, int guestsCount) {
        List<Venue> result = new ArrayList<>();
        boolean isWeekend = date.getDayOfWeek().getValue() >= 6;

        for (Venue v : allVenues) {
            if (v.getCapacity() < guestsCount) {
                continue;
            }
            if (isWeekend && "V2".equals(v.getId())) {
                continue;
            }
            result.add(v);
        }
        return result;
    }
}
