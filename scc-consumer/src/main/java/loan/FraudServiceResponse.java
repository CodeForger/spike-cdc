package loan;

public class FraudServiceResponse {
    private FraudCheckStatus fraudCheckStatus;
    private String rejectionReason;

    public FraudCheckStatus getFraudCheckStatus() {
        return fraudCheckStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
