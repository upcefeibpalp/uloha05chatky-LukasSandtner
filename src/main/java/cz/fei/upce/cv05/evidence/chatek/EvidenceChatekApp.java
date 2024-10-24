package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    // Konstanty pro definovani jednotlivych operaci (pouze pro cisty kod)
    private static final int KONEC_PROGRAMU = 0;
    private static final int OBSAZENOST = 1;
    private static final int PRAZDNE_CHATKY = 2;
    private static final int PRIDAVANI = 3;
    private static final int ODEBIRANI = 4;
    private static final int CELEK_LIDI = 5;
    private static final int LIDI_V_CHATCE = 6;
    private static final int POCET_CHATEK = 10;
    private static final int KAPACITA_CHATEK = 4;
    private static final int[] chatky = new int[POCET_CHATEK];
    private static final Scanner vstup = new Scanner(System.in);

    public static void main(String[] args) {
        int moznost;
        do {
            System.out.println("CHATKOVY SYSTEM");
            System.out.println("----------------");
            System.out.println("Vyber moznost zadanim prislusneho cisla:");
            System.out.println("0. Konec programu\n"
                    + "1. Obsazenost chatek\n"
                    + "2. Vypsani prazdnych chatek\n"
                    + "3. Pridat lidi do chatky\n"
                    + "4. Odebrat lidi z chatky\n"
                    + "5. Celkovy pocet lidi v chatkach\n"
                    + "6. Konkretni chatka");
            moznost = vstup.nextInt();

            switch (moznost) {
                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                    System.exit(0);
                }
                case OBSAZENOST -> {
                    String obsazenost = vypsatObsazenost();
                    System.out.println(obsazenost);
                }
                case PRAZDNE_CHATKY -> {
                    String prazdneChatky = vypsatPrazdneChatky();
                    System.out.println(prazdneChatky);
                }
                case PRIDAVANI -> pridatLidiChatky();
                case ODEBIRANI -> odebratLidiChatky();
                case CELEK_LIDI -> {
                    int celkovyPocetLidi = vypsatCelkovyPocetLidi();
                    System.out.println("Celkovy pocet lidi v chakach (max 40): "
                            + celkovyPocetLidi);
                }
                case LIDI_V_CHATCE -> konkretniChatka();
                default -> System.out.println("Neplatna volba, zkus to znova!");
            }
        } while (moznost != 0);
    }

    private static int cisloChatkyNaIndex(int cisloChatky) {
        return cisloChatky - 1;
    }

    private static String vypsatObsazenost() {
        String vysledek = "Obsazenost chatek:"
                + "\n-----------------\n";
        for (int i = 0; i < POCET_CHATEK; i++) {
            vysledek += "Chatka " + (i + 1) + ": " + chatky[i] + " lidi\n";
        }
        return vysledek;
    }
    
    private static String vypsatPrazdneChatky() {
        System.out.println("Prazdne chatky: ");
        String chatka = "Chatka: ";
        for (int i = 0; i < POCET_CHATEK; i++) {
            if (chatky[i] == 0) {
                //System.out.println("Chatka" + (i + 1));
                chatka += "\n" + (i + 1);
            }
        }
        return chatka;
    }

    private static void pridatLidiChatky() {
        System.out.println("Zadej cislo chatky (maximalni cislo 10): ");
        int cisloChatky = vstup.nextInt();
        System.out.println("Zadej kolik lidi chces pridat (max 4): ");
        int pocetLidi = vstup.nextInt();
        if (cisloChatky > 0 && cisloChatky <= POCET_CHATEK) {
            if (chatky[cisloChatkyNaIndex(cisloChatky)] + pocetLidi 
                    <= KAPACITA_CHATEK) {
                chatky[cisloChatkyNaIndex(cisloChatky)] += pocetLidi;
                System.out.println(pocetLidi + "byli/o pridani/o do chatky"
                        + cisloChatky);
            } else {
                System.out.println("PREKROCENA KAPACITA CHATKY,"
                        + " POCET NEPRIDAN!");
            }
        } else {
            System.out.println("Neplatne cislo chatky!");
        }
    }

    private static void odebratLidiChatky() {
        System.out.println("Zadej cislo chatky (max 10): ");
        int cisloChatky = vstup.nextInt();
        System.out.println("Zadej kolik lidi chces odebrat: ");

        int pocetLidi = vstup.nextInt();
        if (cisloChatky > 0 && cisloChatky <= POCET_CHATEK) {
            if (chatky[cisloChatkyNaIndex(cisloChatky)] - pocetLidi >= 0) {
                chatky[cisloChatkyNaIndex(cisloChatky)] -= pocetLidi;
                System.out.println(pocetLidi
                        + " lidi byli/o odebrani/o z chatky "
                        + cisloChatky);
            } else {
                System.out.println("Prekrocena kapacita odebrani v chatce!");
            }
        } else {
            System.out.println("Neplatne cislo chatky!");
        }
    }

    private static int vypsatCelkovyPocetLidi() {
        int celkovyPocet = 0;
        for (int i = 0; i < chatky.length; i++) {
            celkovyPocet += chatky[i];
        }
        return celkovyPocet;
    }

    private static void konkretniChatka() {
        System.out.println("Zadej cislo chatky (max 10): ");
        int cisloChatky = vstup.nextInt();
        if (cisloChatky <= POCET_CHATEK && cisloChatky > 0) {
            int lidiChatka = chatky[cisloChatkyNaIndex(cisloChatky)];
            System.out.println("V chatce " + cisloChatky + " je tolik lidí : "
                    + lidiChatka);
        } else {
            System.out.println("Chatka neexistuje!");
        }
    }
}
