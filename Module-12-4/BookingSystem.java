package module12Home2;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

class BookingSystem {

    private  VenueRepository venueRepository;
    private  PaymentGateway paymentGateway;

    public BookingSystem(VenueRepository venueRepository, PaymentGateway paymentGateway) {
        this.venueRepository = venueRepository;
        this.paymentGateway = paymentGateway;
    }

    public List<Venue> requestAvailability(BookingRequest request) {
        System.out.println("Система: получен запрос от клиента на дату " +
                request.getEventDate() + ", гостей: " + request.getGuestsCount());

        List<Venue> available = venueRepository.findAvailableVenues(
                request.getEventDate(),
                request.getGuestsCount()
        );

        if (available.isEmpty()) {
            System.out.println("Система: подходящих площадок нет. Предлагаем альтернативы...");
        } else {
            System.out.println("Система: доступные площадки:");
            for (Venue v : available) {
                System.out.println(" - " + v);
            }
        }

        return available;
    }

    public Booking confirmBookingAndPay(Venue selectedVenue, LocalDate date, double amount) {
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, selectedVenue, date);
        booking.setStatus(BookingStatus.PENDING_PAYMENT);

        System.out.println("\nСоздано бронирование " + booking.getId() +
                " для площадки " + selectedVenue.getName());
        System.out.println("Запрашиваем предоплату...");

        PaymentStatus paymentStatus = paymentGateway.processPrepayment(bookingId, amount);

        if (paymentStatus == PaymentStatus.SUCCESS) {
            booking.setStatus(BookingStatus.CONFIRMED);
            System.out.println("Система: бронирование подтверждено.");
            System.out.println("Система: уведомляем клиента и администратора площадки.");
        } else {
            booking.setStatus(BookingStatus.PAYMENT_FAILED);
            System.out.println("Система: оплата не прошла. Уведомляем клиента и предлагаем повторить.");
        }

        return booking;
    }
}
