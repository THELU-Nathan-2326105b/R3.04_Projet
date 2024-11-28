package TP3;

import java.util.ArrayList;

public class MedecinTest {
    public static void main(String[] args) {
        System.out.println("Début des tests pour la classe Medecin.");

        // Création des maladies
        Maladie maladie1 = new Maladie("Syndrome fear of missing out", "FOMO", 3, 5,true);
        Maladie maladie2 = new Maladie("Dépendance aux réseaux sociaux", "DRS", 2, 5,true);

        // Création de créatures avec des maladies
        ArrayList<Maladie> maladiesOrque = new ArrayList<>();
        maladiesOrque.add(maladie1);
        maladiesOrque.add(maladie2);
        Orque orque1 = new Orque("Gorgutz", "Mâle", 120, 180, 25, 3, maladiesOrque);

        ArrayList<Maladie> maladiesElfe = new ArrayList<>();
        maladiesElfe.add(new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 1, 5,false));
        Elfe elfe1 = new Elfe("Lindir", "Femelle", 65, 170, 120, 4, maladiesElfe);

        // Création d'un service médical
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(orque1);
        creatures.add(elfe1);
        ServiceMedical service = new ServiceMedical("Service Mixte", 100, 5, creatures, "médiocre");

        // Création d'un médecin
        Medecin medecin = new Medecin("Dr. Aenarion", "Mâle", 200);

        // Test : Examiner un service médical
        System.out.println("\nTest : Examiner un service médical");
        medecin.examiner(service);

        // Test : Soigner les créatures d'un service médical
        System.out.println("\nTest : Soigner les créatures du service médical");
        medecin.soigner(service);

        // Vérifications après soin
        assert orque1.getListeMaladie().size() == 1 : "Gorgutz devrait avoir une maladie restante.";
        assert orque1.getListeMaladie().getFirst().getNomComplet().equals("Dépendance aux réseaux sociaux") : "La maladie restante de Gorgutz devrait être DRS.";
        assert orque1.getMoralIndic() == 4 : "Le moral de Gorgutz devrait être augmenté de 1.";

        assert elfe1.getListeMaladie().isEmpty() : "Lindir ne devrait plus avoir de maladies après le soin.";
        assert elfe1.getMoralIndic() == 5 : "Le moral de Lindir devrait être augmenté de 1.";

        // Test : Réviser le budget d'un service médical
//        System.out.println("\nTest : Réviser le budget du service médical");
//        medecin.reviserBudget(service, "suffisant");

        // Vérification après révision du budget
        assert service.getBudget().equals("suffisant") : "Le budget du service devrait être 'suffisant'.";

        System.out.println("\nTous les tests pour la classe Medecin ont réussi.");
    }
}
