package com.rbkmoney.error.mapping;

import com.rbkmoney.damsel.domain.Failure;
import com.rbkmoney.woody.api.flow.error.WUndefinedResultException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.rbkmoney.error.mapping.ErrorMappingTest.WildCardEM.*;
import static org.junit.Assert.assertEquals;

public class ErrorMappingTest {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private ErrorMapping errorMapping;

    @Before
    public void setUp() {
        ClassLoader current = ErrorMappingTest.class.getClassLoader();
        InputStream inputStream = current.getResourceAsStream("fixture/errors.json");

        errorMapping = new ErrorMapping(inputStream);
        errorMapping.validateMappingFormat();
    }

    @Test(expected = ErrorMappingException.class)
    public void testMakeFailureByDescriptionException() {
        errorMapping.getFailureByCodeAndDescription("wrong code", "wrong description");
    }

    @Test(expected = WUndefinedResultException.class)
    public void testUndenfinedException() {
        Map<String, String> map = new HashMap<>();
        map.put(TIMEOUT.getErrorCode(),TIMEOUT.getErrorDescription());
        map.forEach((k, v) -> {errorMapping.getFailureByCodeAndDescription(k, v); });
    }

    @Test
    public void testGetFailureByCodeAndDescription() {
        Map<String, String> map = new HashMap<>();
        map.put(DECLINED_3DS_PAYMENT.getErrorCode(),"Failure(code:authorization_failed, reason:'DECLINED' - 'DECLINED', sub:SubFailure(code:unknown))");
        map.put(UNABLE_TO_PERFORM_AN_OPERATION.getErrorCode(),"Failure(code:authorization_failed, reason:'209' - '209', sub:SubFailure(code:unknown))");
        map.put(TRANSACTION_PROHIBITED_AT_LEVEL_OF_FINANCIAL_INSTITUTION.getErrorCode(),"Failure(code:authorization_failed, reason:'304' - '304', sub:SubFailure(code:operation_blocked))");
        map.put(LOST_OR_STOLEN_CARD.getErrorCode(),"Failure(code:authorization_failed, reason:'305' - '305', sub:SubFailure(code:account_stolen))");
        map.put(INVALID_CARD_STATUS.getErrorCode(),"Failure(code:authorization_failed, reason:'306' - '306', sub:SubFailure(code:operation_blocked))");
        map.put(LIMITED_CARD.getErrorCode(),"Failure(code:authorization_failed, reason:'307' - '307', sub:SubFailure(code:operation_blocked))");
        map.put(UNABLE_TO_AUTHORIZE.getErrorCode(),"Failure(code:authorization_failed, reason:'308' - '308', sub:SubFailure(code:payment_tool_rejected, sub:SubFailure(code:bank_card_rejected, sub:SubFailure(code:issuer_not_found))))");
        map.put(CARD_USAGE_LIMIT_EXCEEDED.getErrorCode(),"Failure(code:authorization_failed, reason:'309' - '309', sub:SubFailure(code:account_limit_exceeded, sub:SubFailure(code:number)))");
        map.put(SYSTEM_ERROR_396.getErrorCode(),"Failure(code:authorization_failed, reason:'396' - '396', sub:SubFailure(code:unknown))");
        map.put(CARD_CAPTURE_REQUIRED_502.getErrorCode(),"Failure(code:authorization_failed, reason:'502' - '502', sub:SubFailure(code:operation_blocked))");

        map.forEach((k, v) -> {
                    Failure failure = errorMapping.getFailureByCodeAndDescription(k, k);
                    logger.info(failure.toString());
                    assertEquals(v, failure.toString());
                }
        );
    }

    @Test(expected = RuntimeException.class)
    public void testUnexpectedException() {
        Map<String, String> map = new HashMap<>();

        map.put(UNSUPPORTED_VERSION.getErrorCode(), UNSUPPORTED_VERSION.getErrorDescription());
        map.put(UNSUPPORTED_LANGUAGE.getErrorCode(),UNSUPPORTED_LANGUAGE.getErrorDescription());
        map.put(UNSUPPORTED_COMMAND.getErrorCode(),UNSUPPORTED_COMMAND.getErrorDescription());
        map.put(AUTHENTICATION_FAILED.getErrorCode(),AUTHENTICATION_FAILED.getErrorDescription());
        map.put(ERROR_PARSING_MESSAGE.getErrorCode(),ERROR_PARSING_MESSAGE.getErrorDescription());
        map.put(INVALID_AMOUNT.getErrorCode(),INVALID_AMOUNT.getErrorDescription());
        map.put(REFUSAL_WITHOUT_EXPLANATION_REASONS_FROM_ISSUER.getErrorCode(),REFUSAL_WITHOUT_EXPLANATION_REASONS_FROM_ISSUER.getErrorDescription());
        map.put(SYSTEM_ERROR_006.getErrorCode(),SYSTEM_ERROR_006.getErrorDescription());
        map.put(CRYPTOGRAPHY_ERROR.getErrorCode(),CRYPTOGRAPHY_ERROR.getErrorDescription());
        map.put(WRONG_NUMBER_PARAMETERS.getErrorCode(),WRONG_NUMBER_PARAMETERS.getErrorDescription());
        map.put(NO_FUNDS_ON_ACCOUNT.getErrorCode(),NO_FUNDS_ON_ACCOUNT.getErrorDescription());
        map.put(CAN_NOT_PERFORM_AN_OPERATION.getErrorCode(),CAN_NOT_PERFORM_AN_OPERATION.getErrorDescription());
        map.put(UNSUPPORTED_TRANSACTION.getErrorCode(),UNSUPPORTED_TRANSACTION.getErrorDescription());
        map.put(SYSTEM_ERROR.getErrorCode(),SYSTEM_ERROR.getErrorDescription());
        map.put(ZERO_TRANSACTION_AMOUNT.getErrorCode(),ZERO_TRANSACTION_AMOUNT.getErrorDescription());
        map.put(ACCOUNT_NOT_FOUND.getErrorCode(),ACCOUNT_NOT_FOUND.getErrorDescription());
        map.put(INVALID_TRANSACTION.getErrorCode(),INVALID_TRANSACTION.getErrorDescription());
        map.put(INVALID_RECIPIENT_CARD_NUMBER.getErrorCode(),INVALID_RECIPIENT_CARD_NUMBER.getErrorDescription());
        map.put(CARD_TRANSACTION_LIMIT_EXCEEDED.getErrorCode(),CARD_TRANSACTION_LIMIT_EXCEEDED.getErrorDescription());
        map.put(FORMAT_ERROR.getErrorCode(),FORMAT_ERROR.getErrorDescription());
        map.put(INVALID_3D_SECURE_DATA_VALUE.getErrorCode(),INVALID_3D_SECURE_DATA_VALUE.getErrorDescription());
        map.put(CARD_NOT_SUPPORTED.getErrorCode(),CARD_NOT_SUPPORTED.getErrorDescription());
        map.put(CARD_EXPIRED.getErrorCode(),CARD_EXPIRED.getErrorDescription());
        map.put(CONNECTION_WITH_ISSUER.getErrorCode(),CONNECTION_WITH_ISSUER.getErrorDescription());
        map.put(CARD_CAPTURE_REQUIRED_501.getErrorCode(),CARD_CAPTURE_REQUIRED_501.getErrorDescription());
        map.put(PAYMENT_INFORMATION_IS_INCORRECT.getErrorCode(),PAYMENT_INFORMATION_IS_INCORRECT.getErrorDescription());
        map.put(INVALID_TERMINAL_IDENTIFIER.getErrorCode(),INVALID_TERMINAL_IDENTIFIER.getErrorDescription());
        map.put(ORIGINAL_OPERATION_NOT_FOUND.getErrorCode(),ORIGINAL_OPERATION_NOT_FOUND.getErrorDescription());
        map.put(TIMEOUT_WHEN_MAKING_TRANSACTION.getErrorCode(),TIMEOUT_WHEN_MAKING_TRANSACTION.getErrorDescription());
        map.put(WRONG_CVV2.getErrorCode(),WRONG_CVV2.getErrorDescription());
        map.put(INVALID_SESSION_NUMBER.getErrorCode(),INVALID_SESSION_NUMBER.getErrorDescription());

        map.forEach((k, v) -> {errorMapping.getFailureByCodeAndDescription(k, v); });
    }


    public enum WildCardEM {

        UNDEFINED("UNDEFINED", "UNDEFINED"),
        DECLINED_3DS_PAYMENT("DECLINED", "3DS DECLINED payment"),
        UNSUPPORTED_VERSION("001", "Unsupported version"),
        UNSUPPORTED_LANGUAGE("002", "Unsupported language"),
        UNSUPPORTED_COMMAND("003", "Unsupported command"),
        AUTHENTICATION_FAILED("004", "Authentication failed (invalid certificate)"),
        ERROR_PARSING_MESSAGE("005", "Error parsing the message"),
        SYSTEM_ERROR_006("006", "System error"),
        CRYPTOGRAPHY_ERROR("007", "Cryptography error"),
        TIMEOUT("008", "Timeout"),
        WRONG_NUMBER_PARAMETERS("009", "Wrong number of parameters"),
        ZERO_TRANSACTION_AMOUNT("010", "Zero transaction amount"),
        ACCOUNT_NOT_FOUND("201", "Account not found"),
        INVALID_TRANSACTION("202", "Invalid transaction"),
        INVALID_AMOUNT("203", "Invalid amount"),
        CAN_NOT_PERFORM_AN_OPERATION("204", "Can not perform an operation"),
        NO_FUNDS_ON_ACCOUNT("205", "No funds on the account"),
        PAYMENT_INFORMATION_IS_INCORRECT("206", "Payment information is incorrect"),
        INVALID_TERMINAL_IDENTIFIER("207", "Invalid terminal identifier"),
        ORIGINAL_OPERATION_NOT_FOUND("208", "Original operation not found"),
        UNABLE_TO_PERFORM_AN_OPERATION("209", "Unable to perform an operation"),
        INVALID_RECIPIENT_CARD_NUMBER("210", "Invalid recipient card number"),
        CARD_EXPIRED("301", "Card expired"),
        REFUSAL_WITHOUT_EXPLANATION_REASONS_FROM_ISSUER("302", "Refusal without explanation of reasons from the issuer"),
        UNSUPPORTED_TRANSACTION("303", "Unsupported transaction"),
        TRANSACTION_PROHIBITED_AT_LEVEL_OF_FINANCIAL_INSTITUTION("304", "Transaction prohibited at the level of a financial institution"),
        LOST_OR_STOLEN_CARD("305", "Lost or stolen card"),
        INVALID_CARD_STATUS("306", "Invalid card status"),
        LIMITED_CARD("307", "Limited card"),
        UNABLE_TO_AUTHORIZE("308", "Unable to authorize"),
        CARD_USAGE_LIMIT_EXCEEDED("309", "Card usage limit exceeded"),
        CARD_TRANSACTION_LIMIT_EXCEEDED("310", "Card transaction limit exceeded"),
        CARD_NOT_SUPPORTED("320", "Card not supported"),
        FORMAT_ERROR("333", "Format error"),
        TIMEOUT_WHEN_MAKING_TRANSACTION("334", "Timeout when making a transaction"),
        SYSTEM_ERROR_396("396", "System error"),
        CONNECTION_WITH_ISSUER("401", "A connection with the issuer (call issuer)"),
        INVALID_3D_SECURE_DATA_VALUE("410", "Invalid 3D-Secure data value (CAVV)"),
        WRONG_CVV2("411", "Wrong value of CVV2 / CVC2"),
        CARD_CAPTURE_REQUIRED_501("501", "Card expired. card capture required"),
        CARD_CAPTURE_REQUIRED_502("502", "Issuer's waiver. card capture required"),
        SYSTEM_ERROR("809", "System error"),
        INVALID_SESSION_NUMBER("888", "Invalid session number");


        private final String errorCode;
        private final String errorDescription;


        WildCardEM(String errorCode, String errorDescription) {
            this.errorCode = errorCode;
            this.errorDescription = errorDescription;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorDescription() {
            return errorDescription;
        }


        public static WildCardEM findByCode(String code) {
            return Arrays.stream(values())
                    .filter((wildcard) -> wildcard.getErrorCode().equals(code))
                    .findFirst()
                    .orElse(UNDEFINED);
        }

    }
}