package ISBN_SOAP;

import java.util.Base64;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class FKMISBNServiceSoapBinding {

    private String url = "http://webservices.daehosting.com/services/isbnservice.wso";
    private HashMap<String, String> httpHeaders = new HashMap<>();
    private boolean enableLogging = false;
    private String userName = null;
    private String password = null;
    private FKMConnectionProvider connectionProvider = new FKMHttpConnectionProvider();

    public FKMISBNServiceSoapBinding() {
    }

    public FKMISBNServiceSoapBinding(String url) {
        this.url = url;
    }

    public FKMISBNServiceSoapBinding(String url, FKMConnectionProvider connectionProvider) {
        this.url = url;
        this.connectionProvider = connectionProvider;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public boolean isLoggingEnabled() {
        return enableLogging;
    }

    public void setLoggingEnabled(boolean enableLogging) {
        this.enableLogging = enableLogging;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected FKMRequestResultHandler createRequestResultHandler() {
        return new FKMRequestResultHandler(FKMSoapVersion.v1_1);
    }

    public Boolean IsValidISBN13(final String sISBN) throws java.lang.Exception {
        FKMRequestResultHandler __handler = createRequestResultHandler();
        Document __xml = createIsValidISBN13Request(sISBN, __handler);
        sendRequest("http://webservices.daehosting.com/ISBN/IsValidISBN13", __xml, __handler);
        Node __result = FKMHelper.getResult(__handler.getOutputBody(), "IsValidISBN13Result", false);
        if (__result != null) {
            return FKMHelper.toBoolFromString(((Element) __result).getTextContent());
        }
        return null;
    }

    private Document createIsValidISBN13Request(final String sISBN, FKMRequestResultHandler __handler) throws java.lang.Exception {
        Document __xml = __handler.createEnvelopeXml();
        Element __mainNode = __handler.writeElement("http://webservices.daehosting.com/ISBN", "IsValidISBN13", __xml);
        __handler.getBody().appendChild(__mainNode);

        Element __sISBNItemElement = __handler.writeElementWithType(sISBN, String.class, "sISBN", "http://webservices.daehosting.com/ISBN", __mainNode, false);
        if (__sISBNItemElement != null) {
            __sISBNItemElement.setTextContent(sISBN);
        }
        __handler.finishEnvelopeXml(__xml);
        return __xml;
    }

    public Boolean IsValidISBN10(final String sISBN) throws java.lang.Exception {
        FKMRequestResultHandler __handler = createRequestResultHandler();
        Document __xml = createIsValidISBN10Request(sISBN, __handler);
        sendRequest("http://webservices.daehosting.com/ISBN/IsValidISBN10", __xml, __handler);
        Node __result = FKMHelper.getResult(__handler.getOutputBody(), "IsValidISBN10Result", false);
        if (__result != null) {
            return FKMHelper.toBoolFromString(((Element) __result).getTextContent());
        }
        return null;
    }

    private Document createIsValidISBN10Request(final String sISBN, FKMRequestResultHandler __handler) throws java.lang.Exception {
        Document __xml = __handler.createEnvelopeXml();
        Element __mainNode = __handler.writeElement("http://webservices.daehosting.com/ISBN", "IsValidISBN10", __xml);
        __handler.getBody().appendChild(__mainNode);

        Element __sISBNItemElement = __handler.writeElementWithType(sISBN, String.class, "sISBN", "http://webservices.daehosting.com/ISBN", __mainNode, false);
        if (__sISBNItemElement != null) {
            __sISBNItemElement.setTextContent(sISBN);
        }
        __handler.finishEnvelopeXml(__xml);
        return __xml;
    }

    protected void sendRequest(String soapAction, Document soapBody, FKMRequestResultHandler handler) throws java.lang.Exception {
        HashMap<String, String> __headers = new HashMap<>();
        __headers.putAll(httpHeaders);
        __headers.put("SOAPAction", soapAction);
        __headers.put("user-agent", "easyWSDL Generator 9.0.0.0");
        __headers.put("content-type", "text/xml;charset=UTF-8");
        if (userName != null) {
            String authStr = userName + ":" + password;
            String data = Base64.getEncoder().encodeToString(authStr.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            String authValue = "Basic " + data;
            __headers.put("authorization", authValue);
        }
        String __requestBody = FKMHelper.getStringFromDocument(soapBody);
        if (enableLogging) {
            System.out.println("requestDump: " + __requestBody);
        }
        FKMResponseData response = connectionProvider.sendRequest(url, __requestBody, __headers, handler);
        if (enableLogging) {
            System.out.println("responseDump: " + response.getBody());
        }
        handler.setResponse(response);
    }
}
