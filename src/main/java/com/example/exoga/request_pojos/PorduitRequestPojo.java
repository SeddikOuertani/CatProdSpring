package com.example.exoga.request_pojos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PorduitRequestPojo {
    private String idProduit;
    private String nom;
    private String qt;
    private String disponible;
    private CategorieRequestPojo categorie;
}
