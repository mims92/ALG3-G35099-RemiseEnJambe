/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.alg2.arbre.db;

import be.esi.alg2.arbre.dto.ArbreCompletDto;
import be.esi.alg2.arbre.dto.ArbreDto;
import be.esi.alg2.arbre.mvc.Valeur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe utilitaire d'accès aux arbres persistants
 */
public class ArbreDB {

    /**
     * Persiste un arbre complet en BD.
     * @param aDto Arbre avec noeuds qui doit être sauvé.
     * @throws ArbreDbException Exception levée lorsqu'une erreur au niveau de la BD
     * se produit.
     */
    public static void saveArbre(ArbreCompletDto aDto) throws ArbreDbException {
        try {
            String query = "INSERT INTO ArbreBinaire(nom, ts) " + " values(?, ?)";

            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert = connexion.prepareStatement(query);
            insert.setString(1, aDto.getId());
            insert.setTimestamp(2, new java.sql.Timestamp(aDto.getTimesTamp().getTime()));
            insert.execute();
            query = "INSERT INTO Noeud (cle, libelle, nomArbre, numOrdre) " + " values(?, ?, ?, ?)";
            insert = connexion.prepareStatement(query);
            int i = 1;
            for (Valeur v : aDto.getListe()) {
                insert.setInt(1, v.getCle());
                insert.setString(2, v.getLibelle());
                insert.setString(3, aDto.getId());
                insert.setInt(4, i);
                insert.execute();
                i++;
            }
            
        } catch (SQLException ex) {
            throw new ArbreDbException("Ajout d'arbre impossible:\r SQLException: " + ex.getMessage());
        }
    }

    /**
     * Liste les arbres dont le début du nom est passé en paramètre.
     * @param debNom Le début du nom de l'arbre cherché.
     * @return Retourne la liste des arbres persistés dont le nom correpond au début
     * du nom passé en paramètre.
     * @throws ArbreDbException Exception levée lorsqu'une erreur au niveau de la BD
     * se produit.
     */
    public static List<ArbreDto> listeNoms(String debNom) throws ArbreDbException {
            try {
                String query = "SELECT nom FROM Arbrebinaire "
                        + "WHERE nom LIKE ?";
                
                java.sql.Connection connexion = DBManager.getConnection();
                java.sql.PreparedStatement stmt = connexion.prepareStatement(query);
                stmt.setString(1, debNom+"%");
                java.sql.ResultSet rs = stmt.executeQuery();
                
                List<ArbreDto> liste = new ArrayList<ArbreDto>();

                while (rs.next()) {
                    liste.add(new ArbreDto(rs.getString("nom")));
                }
                
                return liste;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new ArbreDbException("Aucun arbre");
            }
    }

    /**
     * Récupère un arbre complet persisté.
     * @param nom Le nom de l'arbre à charger.
     * @return Retourne l'arbre complet.
     * @throws ArbreDbException Exception levée lorsqu'une erreur au niveau de la BD
     * se produit.
     */
    public static ArbreCompletDto chargeArbre(String nom) throws ArbreDbException {
        try {
            String query = "SELECT cle, libelle  FROM Noeud where nomArbre=? Order by numOrdre ";

            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt = connexion.prepareStatement(query);
            stmt.setString(1, nom);

            java.sql.ResultSet rs = stmt.executeQuery();
            List<Valeur> liste = new ArrayList<Valeur>();
            while (rs.next()) {
                liste.add(new Valeur(rs.getInt("cle"), rs.getString("libelle")));
            }

            ArbreCompletDto arbreDto = new ArbreCompletDto(nom, new Date(), liste);
            return arbreDto;
        } catch (SQLException e) {
            throw new ArbreDbException();
        }
    }
    
    /**
     * Supprime un arbre persisté.
     * @param nom Le nom de l'arbre à supprimer.
     * @throws ArbreDbException Exception levée lorsqu'une erreur au niveau de la BD
     * se produit.
     */
    public static void supprimeArbre(String nom) throws ArbreDbException {
        try {
            String query = "DELETE FROM arbrebinaire WHERE nom = ? ";

            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt = connexion.prepareStatement(query);
            stmt.setString(1, nom);

            stmt.execute();
 
        } catch (SQLException e) {
            throw new ArbreDbException();
        }
    }
}
