package nariux.com.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Super {
    private int id;
    private String area_super;
    private String area_casa;
    private String atiende;
    private String articulo;
    private boolean comprado;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created_at;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea_super() {
        return area_super;
    }

    public void setArea_super(String area_super) {
        this.area_super = area_super;
    }

    public String getArea_casa() {
        return area_casa;
    }

    public void setArea_casa(String area_casa) {
        this.area_casa = area_casa;
    }

    public String getAtiende() {
        return atiende;
    }

    public void setAtiende(String atiende) {
        this.atiende = atiende;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
