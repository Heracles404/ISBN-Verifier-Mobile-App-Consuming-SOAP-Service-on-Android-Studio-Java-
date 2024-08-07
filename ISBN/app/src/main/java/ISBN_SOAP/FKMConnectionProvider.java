
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
import java.net.URL;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public interface FKMConnectionProvider
{
    FKMResponseData sendRequest(String url,String requestBody,HashMap< String,String> httpHeaders, FKMRequestResultHandler handler) throws java.lang.Exception;
}

class FKMHttpConnectionProvider implements FKMConnectionProvider{

    public void prepareRequest(HttpURLConnection url,String requestBody, FKMRequestResultHandler handler) throws java.lang.Exception {
        OutputStreamWriter wr = new OutputStreamWriter(url.getOutputStream());
        wr.write(requestBody);
        wr.flush();
    }

    @Override
    public FKMResponseData sendRequest(String url,String requestBody, HashMap< String, String> httpHeaders,FKMRequestResultHandler handler ) throws java.lang.Exception
    {
        URL urlObject=new URL(url);
        HttpURLConnection connection=(HttpURLConnection)urlObject.openConnection();
        connection.setRequestMethod("POST");
        for(Map.Entry<String, String> entry : httpHeaders.entrySet())
        {
            connection.setRequestProperty(entry.getKey(),entry.getValue());
        }

        prepareRequest(connection,requestBody,handler);


        FKMResponseData response=new FKMResponseData();
        for(Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet())
        {
            if(entry.getKey()!=null)
            {
                String values = "";
                for (String value : entry.getValue()) {
                    values = values + value;
                }
                response.getHeaders().put(entry.getKey(),values);
            }
        }
        
        try(InputStream dataStream = getResponseStream(connection,response,handler))
        {
            response.setBody(FKMHelper.streamToString(dataStream));
        }
        
        return response;
    }

    protected InputStream getResponseStream(
        HttpURLConnection url,
        FKMResponseData response,
        FKMRequestResultHandler handler
    ) throws java.lang.Exception
    {
        try{
            return url.getInputStream();
        }
        catch (IOException ex)
        {
            return url.getErrorStream();
        }
    }
}