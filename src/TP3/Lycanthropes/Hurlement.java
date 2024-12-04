package TP3.Lycanthropes;

public class Hurlement {
    private String appartenance; // Appartenance de l'emmetteur
    private Lycanthrope emetteur; // Nom ou identifiant du lycanthrope émetteur
    private String expression; // Ce qui est exprimé par un hurlement


    public Hurlement(Lycanthrope emetteur) {
        this.emetteur = emetteur;
        this.appartenance = emetteur.getMeute();
    }

    public void afficherCaracteristiques() {
        System.out.println("Hurlement émis par : " + emetteur.getNomComplet());
        System.out.println("Appartenance : " + appartenance);
    }

    public void exprimer(){
        if (this.expression == null) {
            this.expression = "domination";
        }
        else {
            if (this.emetteur.getRangDom()== 'ω'){
                this.expression = "";
            }

        }
    }
}