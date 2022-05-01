package nariux.com.conection;

import android.os.StrictMode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import nariux.com.model.Super;
import nariux.com.utils.Utilidades;

public class ClientHttp <T>{


    private static final MediaType MediaTypeJSON = MediaType
            .parse("application/json; charset=utf-8");
//  private static final String URI_SUPER = "http://192.168.1.81:8000/api/super";
    private static final String URI_SUPER = "https://super.nariux.com/api/super";

    private OkHttpClient httpclient;
    private ObjectMapper mapper;

    public ClientHttp(){

        httpclient = new OkHttpClient();
        mapper = new ObjectMapper();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    private Super agregaList(Super s) throws IOException {
        Super createdSuper;
        String json = mapper.writeValueAsString(s);
        Request request = new Request.Builder()
                .url(URI_SUPER)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(MediaTypeJSON, json)).build();
        Response response = httpclient.newCall(request).execute();// {
        if (response.isSuccessful()) {
            return mapper.readValue(response.body().bytes(), Super.class);
        }else {
            return null;
        }
    }

    private ArrayList<Super> getSuperc() throws IOException {
        Super createdSuper = null;
        ArrayList<Super> listSuper=null;
        Request request = new Request.Builder().url(URI_SUPER).addHeader("Content-Type", "application/json")
                .get().build();
        Response response = httpclient.newCall(request).execute();//
        if (response.isSuccessful()) {
            listSuper = new ArrayList<Super>( Arrays.asList(mapper.readValue(response.body().string(), Super[].class))) ;
        }

        return listSuper;
    }

    private ArrayList<Super> getSuperc(String area, boolean comprado, String atiende) throws IOException {
        Super createdSuper = null;
        String URL_EXTRA="/"+area+"/comprado/"+comprado+"/atiende/"+atiende;
        System.out.println(URI_SUPER+URL_EXTRA);
        ArrayList<Super> listSuper=null;
        Request request = new Request.Builder().url(URI_SUPER+URL_EXTRA).addHeader("Content-Type", "application/json")
                .get().build();
        Response response = httpclient.newCall(request).execute();//
            if (response.isSuccessful()) {
            listSuper = new ArrayList<Super>( Arrays.asList(mapper.readValue(response.body().string(), Super[].class))) ;
        }

            return listSuper;
    }

    private Super setEliminaId(Super s)  throws IOException {
        Super createdSuper;
        Request request = new Request.Builder().url(URI_SUPER+"/"+s.getId()).addHeader("Content-Type", "application/json")
                .delete().build();
        Response response = httpclient.newCall(request).execute();// {
        if (response.isSuccessful()) {
            // Get back the response and convert it to a Book object
            createdSuper = mapper.readValue(response.body().bytes(), Super.class);

        }else {
            createdSuper = null;
        }
        return createdSuper;
    }

    private void actualizaCompradoArticulo(Super s)  throws IOException {
        String json = mapper.writeValueAsString(s);

        // build a request
        Request request = new Request.Builder().url(URI_SUPER+"/comprado").addHeader("Content-Type", "application/json")
                .put(RequestBody.create(MediaTypeJSON, json)).build();
        // build a request
        //Response response = httpclient.newCall(request).execute();

        //try (
        Response response = httpclient.newCall(request).execute();// {

    }

    private void vacia()throws IOException {
        Request request = new Request.Builder()
                .url(URI_SUPER+"/vacia")
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(MediaTypeJSON, "")).build();
        Response response = httpclient.newCall(request).execute();// {

    }

    public Super agregarLista(Super s){
        Super ret= new Super();
        try {
            ret = agregaList(s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    public ArrayList<Super> getSuper(){
        ArrayList<Super> listSuper= null;
        try {
            listSuper = getSuperc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listSuper;
        //return null;
    }

    public ArrayList<Super> getSuper(String area, boolean comprado, String atiende){
        ArrayList<Super> listSuper= null;
        try {
            listSuper = getSuperc(area,  comprado, atiende);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listSuper;
        //return null;
    }

    public Super setElimina(Super s){
        Super r = null;
        try {
            r = setEliminaId(s);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return r;
        }
    }

    public void actualizaComprado(Super s){
        try {
            actualizaCompradoArticulo(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void vaciar(){

        try {
            vacia();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Super actualizaArticulo(Super s){
        return s;
    }
}
