package loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanApplicationService {
    private static final String FRAUD_SERVICE_JSON_VERSION_1 =
            "application/vnd.fraud.v1+json";

    private RestTemplate restTemplate;

    private int port = 6565;

    @Autowired
    public LoanApplicationService(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public LoanApplicationResult loanApplication(LoanApplication application) {
        FraudServiceRequest request = new FraudServiceRequest(application);
        FraudServiceResponse response = sendRequestToFraudDetectionService(request);
        return buildResponseFromFraudResult(response);
    }

    private FraudServiceResponse sendRequestToFraudDetectionService(
            FraudServiceRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, FRAUD_SERVICE_JSON_VERSION_1);

        ResponseEntity<FraudServiceResponse> response =
                restTemplate.exchange("http://localhost:" + port + "/fraudcheck", HttpMethod.PUT,
                        new HttpEntity<>(request, httpHeaders),
                        FraudServiceResponse.class);

        return response.getBody();
    }

    private LoanApplicationResult buildResponseFromFraudResult(FraudServiceResponse response) {
        LoanApplicationStatus applicationStatus = null;
        if (FraudCheckStatus.OK == response.getFraudCheckStatus()) {
            applicationStatus = LoanApplicationStatus.LOAN_APPLIED;
        } else if (FraudCheckStatus.FRAUD == response.getFraudCheckStatus()) {
            applicationStatus = LoanApplicationStatus.LOAN_APPLICATION_REJECTED;
        }

        return new LoanApplicationResult(applicationStatus, response.getRejectionReason());
    }

    public void setPort(int port) {
        this.port = port;
    }
}
