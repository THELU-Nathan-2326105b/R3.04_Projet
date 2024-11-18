package TP3;

public class Medecin {
    private String nom;
    private String sexe;
    private int age;

    public Medecin(String nom, String sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
    }

    // Examiner un service médical
    public void examiner(ServiceMedical service) {
        System.out.println("Médecin " + nom + " examine le service : " + service.getNom());
        System.out.println("Superficie : " + service.getSuperficie() + " m²");
        System.out.println("Nombre de créatures : " + service.getNombreCreaturesPresente() + "/" + service.getNombreMaximumCreatures());
        System.out.println("Budget : " + service.getBudget());
        for (Creature creature : service.getListeCreatures()) {
            System.out.println(creature.getNomComplet());
            System.out.println("   Moral : " + creature.getMoralIndic());
            System.out.println("   Maladies :");
            if (creature.getListeMaladies().isEmpty()) {
                System.out.println("     Aucune maladie.");
            } else {
                for (Maladie maladie : creature.getListeMaladie()) {
                    System.out.println("     - " + maladie.getNomComplet() + " (Niveau : " + maladie.getNiveauActuel() + "/" + maladie.getNiveauMaximum() + ")");
                }
            }
        }
    }

    // Soigner les créatures d'un service médical
    public void soigner(ServiceMedical service) {
        System.out.println("Médecin " + nom + " soigne les créatures du service : " + service.getNom());
        for (Creature creature : service.getListeCreatures()) {
            if (!creature.getListeMaladies().isEmpty()) {
                // Soigner la première maladie
                Maladie maladie = creature.getListeMaladie().get(0);
                maladie.diminuerNiveau(1);
                System.out.println("  - " + creature.getNomComplet() + " : Soins apportés à " + maladie.getNomComplet() + ". Niveau actuel : " + maladie.getNiveauActuel());
                if (maladie.getNiveauActuel() <= 1) {
                    System.out.println("    => " + maladie.getNomComplet() + " est guérie.");
                    creature.getListeMaladies().remove(maladie);
                }
            }
            // Redonner un peu de moral à la créature
            creature.setMoralIndic(creature.getMoralIndic() + 1);
        }
    }

    // Réviser le budget d’un service médical
//    public void reviserBudget(ServiceMedical service, String nouveauBudget) {
//        System.out.println("Médecin " + nom + " révise le budget du service : " + service.getNom());
//        service.reviserBudget(nouveauBudget);
//        System.out.println("Nouveau budget : " + service.getBudget());
//    }

    @Override
    public String toString() {
        return "Médecin : " + nom + " | Sexe : " + sexe + " | Âge : " + age;
    }
}
