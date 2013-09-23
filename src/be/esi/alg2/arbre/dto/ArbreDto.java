package be.esi.alg2.arbre.dto;

import be.esi.alg2.dto.Dto;

/**
 *
 * Dto d'un arbre binaire se contentant de porter le nom de l'arbre
 */
public class ArbreDto extends Dto<String> {
    private String nom;
    
    public ArbreDto(String s) {
        this.nom = s;
    }
    
    public String getNom() {
        return this.nom;
    }
}
