package module12Home2;

import java.time.LocalDate;
import java.util.List;

public class EventBookingDemo {

    public static void main(String[] args) {
        VenueRepository venueRepository = new VenueRepository();

        PaymentGateway gateway = new FakePaymentGateway(true);

        BookingSystem bookingSystem = new BookingSystem(venueRepository, gateway);

        BookingRequest request = new BookingRequest(
                LocalDate.now().plusDays(7),
                60,
                "банкет, живая музыка"
        );

        List<Venue> available = bookingSystem.requestAvailability(request);

        if (available.isEmpty()) {
            System.out.println("\nКлиент: площадок нет, попробую другие даты.");
            return;
        }

        Venue chosen = available.get(0);
        System.out.println("\nКлиент выбирает площадку: " + chosen.getName());

        Booking booking = bookingSystem.confirmBookingAndPay(
                chosen,
                request.getEventDate(),
                50000.0
        );

        System.out.println("\nИтоговый статус бронирования: " + booking.getStatus());
    }
}
