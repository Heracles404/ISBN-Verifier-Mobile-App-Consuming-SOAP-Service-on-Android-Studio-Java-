
package ISBN_SOAP;
//------------------------------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 9.0.0.0
//
// Created by Quasar Development 
//
// This class has been generated for test purposes only and cannot be used in any commercial project.
// To use it in commercial project, you need to generate this class again with Premium account.
// Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
//
// Licence: 0E82BB53D374CBB02A5D6DB247B4AD180F99172EE4DDF1D07982F040C4BD3E2400CD3EB79369F9377A0240F7D661830EE033E13F7CFAF67A52887A4000CBADBE
//------------------------------------------------------------------------

import java.util.*;


public class FKMISBNServiceSoapBinding12
{
    private String url = "http://webservices.daehosting.com/services/isbnservice.wso";
    private HashMap< String,String> httpHeaders = new HashMap< String,String>();
    private boolean enableLogging = false;
    private String userName=null;
    private String password=null;
    private FKMConnectionProvider connectionProvider=new FKMHttpConnectionProvider();

    public FKMISBNServiceSoapBinding12()
    {
    }

    public FKMISBNServiceSoapBinding12(String url)
    {
        this.url=url;
    }

    public FKMISBNServiceSoapBinding12 (String url,FKMConnectionProvider connectionProvider) {
        this.url=url;
        this.connectionProvider=connectionProvider;
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
    

    
    protected FKMRequestResultHandler createRequestResultHandler()
    {
        FKMRequestResultHandler handler = new FKMRequestResultHandler(FKMSoapVersion.v1_2);
        return handler;
    }
    
    private org.w3c.dom.Document createIsValidISBN13Request(final String sISBN,FKMRequestResultHandler __handler) throws java.lang.Exception
    {
        org.w3c.dom.Document __xml=__handler.createEnvelopeXml();
        org.w3c.dom.Element __mainNode=__handler.writeElement("http://webservices.daehosting.com/ISBN","IsValidISBN13",__xml);
        __handler.getBody().appendChild(__mainNode);

        org.w3c.dom.Element __sISBNItemElement=__handler.writeElementWithType(sISBN,String.class, "sISBN", "http://webservices.daehosting.com/ISBN", __mainNode, false);
        if(__sISBNItemElement != null)
        {
            __sISBNItemElement.setTextContent(sISBN.toString());
        }
        __handler.finishEnvelopeXml(__xml);
        return __xml;
    }
    
    public Boolean IsValidISBN13(final String sISBN) throws java.lang.Exception
    {
        FKMRequestResultHandler __handler =createRequestResultHandler();
        org.w3c.dom.Document __xml=createIsValidISBN13Request(sISBN, __handler);
        sendRequest("",__xml,__handler);
        org.w3c.dom.Node __result=FKMHelper.getResult(__handler.getOutputBody(), "IsValidISBN13Result",false);
        if(__result!=null)
        {
            return FKMHelper.toBoolFromString(((org.w3c.dom.Element)__result).getTextContent());
        }
        return null;
    }
    private org.w3c.dom.Document createIsValidISBN10Request(final String sISBN,FKMRequestResultHandler __handler) throws java.lang.Exception
    {
        org.w3c.dom.Document __xml=__handler.createEnvelopeXml();
        org.w3c.dom.Element __mainNode=__handler.writeElement("http://webservices.daehosting.com/ISBN","IsValidISBN10",__xml);
        __handler.getBody().appendChild(__mainNode);

        org.w3c.dom.Element __sISBNItemElement=__handler.writeElementWithType(sISBN,String.class, "sISBN", "http://webservices.daehosting.com/ISBN", __mainNode, false);
        if(__sISBNItemElement != null)
        {
            __sISBNItemElement.setTextContent(sISBN.toString());
        }
        __handler.finishEnvelopeXml(__xml);
        return __xml;
    }
    
	/**
	* This method is available in Premium account only. To test if generated classes work correctly with your webservice, please use different method. Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
	*/
    public String IsValidISBN10(final String sISBN) throws java.lang.Exception
    {
        
/*This feature is available in Premium account. To test if generated classes work correctly with your webservice, please use different method. Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.*/
        throw new UnsupportedOperationException("This feature is available in Premium account. To test if generated classes work correctly with your webservice, please use different method. Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.");
    }
    protected void sendRequest(String soapAction,org.w3c.dom.Document soapBody, FKMRequestResultHandler handler) throws java.lang.Exception
    {
        HashMap< String,String> __headers = new HashMap<>();
        __headers.putAll(httpHeaders);
        __headers.put("SOAPAction",soapAction);
        __headers.put("user-agent","easyWSDL Generator 9.0.0.0");
        __headers.put("content-type","application/soap+xml; charset=utf-8");
        if (userName != null)
        {
            String authStr = userName+":"+password;
            String data=Base64.getEncoder().encodeToString(authStr.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            String authValue = "Basic "+data;
            __headers.put("authorization",authValue);
        }
        String __requestBody=FKMHelper.getStringFromDocument(soapBody);
        if(enableLogging)
        {
            System.out.println("requestDump: "+__requestBody);
        }
        FKMResponseData response=connectionProvider.sendRequest(url,__requestBody, __headers,handler);
        if(enableLogging)
        {
             System.out.println("responseDump: "+response.getBody());
        }
        handler.setResponse(response);
    }
}
