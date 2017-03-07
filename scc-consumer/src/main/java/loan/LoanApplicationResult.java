package loan;

public class LoanApplicationResult {
    private LoanApplicationStatus loanApplicationStatus;
    private String rejectionReason;

    public LoanApplicationResult(LoanApplicationStatus applicationStatus, String rejectionReason) {
        this.loanApplicationStatus = applicationStatus;
        this.rejectionReason = rejectionReason;
    }

    public LoanApplicationStatus getLoanApplicationStatus() {
        return loanApplicationStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
