package com.example.exOGA.request_pojos;

import lombok.Data;

@Data
public class PorduitRequestPojo {
    private String idProduit;
    private String nom;
    private String qt;
    private String disponible;
    private CategorieRequestPojo categorie;
}
