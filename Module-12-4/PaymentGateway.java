package module12Home2;

interface PaymentGateway {
    PaymentStatus processPrepayment(String bookingId, double amount);
}

class FakePaymentGateway implements PaymentGateway {

    private final boolean forceSuccess;

    public FakePaymentGateway(boolean forceSuccess) {
        this.forceSuccess = forceSuccess;
    }

    @Override
    public PaymentStatus processPrepayment(String bookingId, double amount) {
        System.out.println("Отправляем запрос на предоплату в платёжный шлюз...");
        System.out.println("BookingId=" + bookingId + ", amount=" + amount);

        if (forceSuccess) {
            System.out.println("Платёжный шлюз: платёж ПРОШЁЛ.");
            return PaymentStatus.SUCCESS;
        } else {
            System.out.println("Платёжный шлюз: платёж ОТКЛОНЁН.");
            return PaymentStatus.FAILED;
        }
    }
}
