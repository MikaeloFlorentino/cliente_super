package nariux.com.conection;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import nariux.com.model.Super;
import nariux.com.utils.Utilidades;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClientHttp {


    private static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");
    private static final String URI_SUPER = "http://127.0.0.1:8000/api/super/";

    private OkHttpClient httpclient;

    public ClientHttp(){
        httpclient = new OkHttpClient();
    }

    private Super agregaList(Super s) throws IOException {
        Super createdSuper = null;
        // convert the book to JSON by Jackson
        ObjectMapper mapper = new ObjectMapper();
        String jsonBook = mapper.writeValueAsString(s);

        // build a request
        Request request = new Request.Builder().url(URI_SUPER).addHeader("Content-Type", "application/json")
                .post(RequestBody.create(MediaTypeJSON, jsonBook)).build();
        // build a request
        //Response response = httpclient.newCall(request).execute();

        //try (
                Response response = httpclient.newCall(request).execute();// {
            if (response.isSuccessful()) {
                // Get back the response and convert it to a Book object
                createdSuper = mapper.readValue(response.body().bytes(), Super.class);

            }else {
                createdSuper = null;
            }

        //}


        return createdSuper;
    }

    public Super agregarLista(Super s){
        Super ret= new Super();
        try {
            ret = agregaList(s);
        } catch (IOException e) {
            e.printStackTrace();
            ret.setId(-1);
        }
        /*try{
            ret = agregaList(s);
        }catch(Exception e){
            ret.setId(-1);
        }*/

        return ret;
    }

    public List<Super> getSuper(){
        return null;
    }

    public Super actualizaArticulo(Super s){
        return s;
    }
}
