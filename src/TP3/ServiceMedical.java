package TP3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Représente un service médical dans l'hôpital.
 * Les services médicaux gèrent des créatures et leur traitement.
 */
public class ServiceMedical {
    private String nom;
    private int superficie;
    private int nombreMaximumCreatures;
    private ArrayList<Creature> listeCreatures;
    private String budget;
    private ArrayList<Medecin> medecins; // Nouvel attribut pour gérer les médecins

    /**
     * Constructeur pour créer un service médical.
     * @param nom Nom du service.
     * @param nombreMaximumCreatures Capacité maximale.
     */
    public ServiceMedical(String nom, int superficie, int nombreMaximumCreatures, ArrayList<Creature> listeCreatures, String budget) {
        if (budget == null || (!budget.equals("inexistant") && !budget.equals("médiocre") && !budget.equals("insuffisant") && !budget.equals("faible"))) {
            throw new IllegalArgumentException("Erreur de création : budget invalide.");
        }
        this.nom = nom;
        this.superficie = superficie;
        this.nombreMaximumCreatures = nombreMaximumCreatures;
        this.listeCreatures = new ArrayList<>(listeCreatures); // Copie défensive
        this.budget = budget;
        this.medecins = new ArrayList<>(); // Initialisation de la liste des médecins
    }

    public String getNom() {
        return nom;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getNombreMaximumCreatures() {
        return nombreMaximumCreatures;
    }

    public int getNombreCreaturesPresente() {
        return listeCreatures.size();
    }

    public ArrayList<Creature> getListeCreatures() {
        return listeCreatures;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        if (budget == null ||
                (!budget.equals("inexistant") &&
                        !budget.equals("médiocre") &&
                        !budget.equals("insuffisant") &&
                        !budget.equals("faible") &&
                        !budget.equals("moyen") &&
                        !budget.equals("élevé"))) {
            throw new IllegalArgumentException("Erreur de création : budget invalide.");
        }
        this.budget = budget;
    }

    public void afficherCaracteristiques() {
        System.out.println("Nom : " + nom);
        System.out.println("Superficie : " + superficie);
        System.out.println("Nombre maximum de créatures : " + nombreMaximumCreatures);
        System.out.println("Nombre de créatures présentes : " + getNombreCreaturesPresente());
        System.out.println("Budget : " + budget);
        System.out.println("Médecins affectés : " + medecins.size());
        System.out.println("Liste des créatures :");
        for (Creature creature : listeCreatures) {
            System.out.println(" - " + creature);
        }
    }

    /**
     * Ajoute une créature au service médical.
     * @param creature Créature à ajouter.
     */
    public void ajouterCreature(Creature creature) {
        if (listeCreatures.size() >= nombreMaximumCreatures) {
            throw new IllegalStateException("Le service médical est plein.");
        }
        if (!estTypeCompatible(creature)) {
            throw new IllegalArgumentException("Type de créature incompatible avec ce service médical.");
        }
        listeCreatures.add(creature);
    }

    // Ajout de la méthode pour retirer une créature
    public void retirerCreature(Creature creature) {
        if (listeCreatures.contains(creature)) {
            listeCreatures.remove(creature);
            System.out.println(creature.getNomComplet() + " a été retirée du service " + this.nom + ".");
        } else {
            System.out.println(creature.getNomComplet() + " n'est pas présente dans ce service.");
        }
    }

    public void soignerCreatures() {
        Random r1 = new Random();
        for (Creature creature : listeCreatures) {
            for (Maladie maladie : creature.getListeMaladie()) {
                int nb = r1.nextInt(5);
                if (nb == 0) {
                    int niveauActuel = maladie.getNiveauActuel();
                    if (niveauActuel > 0) {
                        maladie.setNiveauActuel(niveauActuel - 1);
                        System.out.println("La maladie " + maladie.getNomComplet() + " de " + creature.getNomComplet() + " a été soignée, nouveau niveau : " + maladie.getNiveauActuel());
                    }
                }
            }
        }
    }

    public void reduireMoralDesAutres(Creature creatureMorte) {
            for (Creature creature : listeCreatures) {
                if (!creature.equals(creatureMorte)) { // Ne pas affecter la créature morte
                    Random random = new Random();
                    double valeur = random.nextDouble(); // Génère une valeur aléatoire entre 0.0 et 1.0
                    if (valeur < 0.5) {
                        creature.setMoralIndic(creature.getMoralIndic() - 1); // Réduire le moral de 1
                        System.out.println("Le moral de " + creature.getNom() + " est maintenant de " + creature.getMoralIndic());
                    }
                }
            }
    }

    // Gestion des médecins
    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }

    public ArrayList<Medecin> getMedecins() {
        return medecins;
    }

    protected boolean estTypeCompatible(Creature creature) {
        return true; // À surcharger pour des types spécifiques
    }

    public void contaminer(Creature creatureMorte) {
        for (Creature creature : listeCreatures) {
            if (!creature.equals(creatureMorte)) { // Ne pas affecter la créature morte
                Random random = new Random();
                double valeur = random.nextDouble(); // Génère une valeur aléatoire entre 0.0 et 1.0
                if (valeur < 0.5) {

                    creature.getListeMaladie().add(creatureMorte.getListeMaladie().getFirst());
                    System.out.println("La créature " + creature.getNom() + " a attrapé la maladie " + creatureMorte.getListeMaladie().getFirst());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : ").append(nom).append("\n");
        sb.append("Superficie : ").append(superficie).append("\n");
        sb.append("Nombre maximum de créatures : ").append(nombreMaximumCreatures).append("\n");
        sb.append("Créatures présentes : ").append(listeCreatures.size()).append("\n");

        sb.append("Liste des créatures :\n");
        for (Creature creature : listeCreatures) {
            sb.append(" - ").append(creature.getNomComplet()).append("\n");
        }

        return sb.toString();
    }

}