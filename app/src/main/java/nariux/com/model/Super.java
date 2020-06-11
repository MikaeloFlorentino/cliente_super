package nariux.com.model;

import java.util.Date;

public class Super {
    private int id;
    private String area_super;
    private String area_casa;
    private String area_atiende;
    private String articulo;
    private boolean comprado;
    private Date created_at;
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

    public String getArea_atiende() {
        return area_atiende;
    }

    public void setArea_atiende(String area_atiende) {
        this.area_atiende = area_atiende;
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
