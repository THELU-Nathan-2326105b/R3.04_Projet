package TP3;

import java.util.ArrayList;

public class Crypte extends ServiceMedical{

    private int nivVent;
    private int temp;

    public Crypte(String nom, int superficie, int nombreMaximumCreatures, ArrayList<Creature> listeCreatures, String budget, int nivVent, int temp) {
        super(nom, superficie, nombreMaximumCreatures, listeCreatures, budget);
        this.nivVent = nivVent;
        this.temp = temp;
    }

    public int getNivVent() { return nivVent; }

    public int getTemp() { return temp; }

    public void setNivVent(int nivVent) { this.nivVent = nivVent; }

    public void setTemp(int temp) { this.temp = temp; }

}
