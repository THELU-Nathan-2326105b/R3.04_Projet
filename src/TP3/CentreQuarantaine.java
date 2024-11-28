package TP3;

import java.util.ArrayList;

public class CentreQuarantaine extends ServiceMedical {
    private boolean isolation;

    public CentreQuarantaine(String nom, int superficie, int nombreMaximumCreatures, ArrayList<Creature> listeCreatures, String budget, boolean isolation) {
        super(nom, superficie, nombreMaximumCreatures, listeCreatures, budget);
        this.isolation = isolation;
    }

    public boolean ajouterCreatureQ(Creature creature) {
        if (creature.aMaladieContagieuse()) {
            if (getNombreCreaturesPresente() < getNombreMaximumCreatures()) {
                getListeCreatures().add(creature);
                return true;
            } else {
                System.out.println("Centre de quarantaine plein !");
            }
        } else {
            System.out.println("La créature n'a pas de maladie contagieuse et ne peut pas être admise !");
        }
        return false;
    }

    public boolean isIsolation() {
        return isolation;
    }

    public void setIsolation(boolean isolation) {
        this.isolation = isolation;
    }

    @Override
    public void setBudget(String budget) {
        super.setBudget(budget);
        if (isolation) {
            System.out.println("Révision du budget incluant l'isolation.");
        }
    }
}
