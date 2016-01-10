/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author ThinhND
 */
public class HandPoker {

    /**
     */
    public static List<PokerCard> handPoker = new ArrayList<>();
    public static List<Integer> list1 = new ArrayList<>();
    public static List<Integer> list2 = new ArrayList<>();
    public static List<String> cardNum  = new ArrayList<>();
    public static List<String> cardRank  = new ArrayList<>();
    public static String Rank;
	private static Scanner key;

    public static void main(String[] args) {
        addCard();
        showListcard();
        checkCard();
    }
    
    public static void addDetail(){
        cardNum.add("2");
        cardNum.add("3");
        cardNum.add("4");
        cardNum.add("5");
        cardNum.add("6");
        cardNum.add("7");
        cardNum.add("8");
        cardNum.add("9");
        cardNum.add("10");
        cardNum.add("J");
        cardNum.add("Q");
        cardNum.add("K");
        cardNum.add("A");
        cardRank.add("Bich");
        cardRank.add("Tep");
        cardRank.add("Ro");
        cardRank.add("Co");
    }
    
    public static void checkCard() {
        if (checkSF() != 1) {
            checkFOK_TOK_F_DF_FH(0);
        }
    }

    public static void addCard() {
        int cardNum, cardSuit;
        key = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Insert card " + (i + 1));
            cardNum = key.nextInt();
            cardSuit = key.nextInt();
            PokerCard card = new PokerCard(cardNum, cardSuit);
            handPoker.add(card);
        }
    }

    public static void showListcard() {
        addDetail();
        for (int i = 0; i < handPoker.size(); i++) {
            System.out.println("card " + (i + 1) + ": " + cardNum.get(handPoker.get(i).getCardNumber()) + "-" + cardRank.get(handPoker.get(i).getCardSuit()));
        }
    }

    public static int forList(int id) {
        int flag = 0;
        for (int i = 0; i < 4; i++) {
            if (id != i) {
                if (handPoker.get(id).getCardNumber() == handPoker.get(i).getCardNumber()) {
                    flag = flag + 1;
                }
            }
        }
        return flag;
    }

    public static void forList2(int id) {
        for (int i = 0; i < 5; i++) {
            if (id != i) {
                if (handPoker.get(id).getCardNumber() != handPoker.get(i).getCardNumber()) {
                    list1.add(i);
                    list2.add(handPoker.get(i).getCardNumber());
                }
            }
        }
    }

    public static int forList3(int id) {
        int flag = 0;
        for (int i = 0; i < list2.size(); i++) {
            if (id != i) {
                if (Objects.equals(list2.get(id), list2.get(i))) {
                    flag = flag + 1;
                }
            }
        }
        return flag;
    }

    public static void checkF(int id) {
        int flag = 0;
        flag = forList3(id);
        switch (flag) {
            case 1:
                System.out.println("This Rank is Two Pairs");
                break;
            case 2:
                System.out.println("This Rank is Full House");
                break;
            default:
                if (id == (list2.size() - 1)) {
                    System.out.println("This Rank is Pair");
                } else {
                    if (id < list2.size()) {
                        id++;
                    }
                    checkF(id);
                }
                break;
        }
    }

    public static void checkFOK_TOK_F_DF_FH(int id) {
        int flag = 0;
        int flag2 = 0;
        flag = forList(id);
        switch (flag) {
            case 3:
                System.out.println("This Rank is Four of Kind");
                break;
            case 2:
                forList2(id);
                if (list1.size() == 2) {
                    if (handPoker.get(list1.get(0)).getCardNumber() == handPoker.get(list1.get(1)).getCardNumber()) {
                        System.out.println("This Rank is Full House");
                    } else {
                        System.out.println("This Rank is Three of Kind");
                    }
                }
                break;
            case 1:
                forList2(id);
                checkF(0);
                break;
            default:
                if (id == 4) {
                    flag2 = checkSuit();
                    if (flag2 == 1) {
                        System.out.println("This Rank is Flush");
                    } else {
                        System.out.println("This Rank is Hight Card");
                    }
                } else {
                    if (id < 5) {
                        id++;
                    }
                    checkFOK_TOK_F_DF_FH(id);
                }
                break;

        }
    }

    public static int checkMinimunS() {
        int flag = 0;
        sortListCard();
        if (handPoker.get(0).getCardNumber() == 13 && handPoker.get(1).getCardNumber() == 2) {
            for (int i = 1; i < 4; i++) {
                if ((handPoker.get(i).getCardNumber() == (-1 + handPoker.get(i + 1).getCardNumber()))) {
                    flag = flag + 1;
                }
            }
            if (flag == 3) {
                System.out.println("This Rank is Straight");
                return 1;
            }
            else{
                return 0;
            }
        } else {
            return checkS();
        }
    }

    public static int checkSF() {
        int flag = 0;
        int flagSuit = 0;
        sortListCard();
        flagSuit = checkSuit();
        if (flagSuit == 1) {
            for (int i = 0; i < 4; i++) {
                if ((handPoker.get(i).getCardNumber() == (-1 + handPoker.get(i + 1).getCardNumber()))) {
                    flag = flag + 1;
                }
            }
            if (flag == 4) {
                System.out.println("This Rank is Straight Flush");
                return 1;
            } else {
                return 0;
            }
        }else{
            return checkMinimunS();
        }
    }

    public static int checkS() {
        int flag = 0;
        sortListCard();
        for (int i = 0; i < 4; i++) {
            if ((handPoker.get(i).getCardNumber() == (-1 + handPoker.get(i + 1).getCardNumber()))) {
                flag = flag + 1;
            }
        }
        if (flag == 4) {
            System.out.println("This Rank is Straight");
            return 1;
        }
        else{
            return 0;
        }
    }

    public static int checkSuit() {
        int flag = 0;
        for (int i = 0; i < 4; i++) {
            if (handPoker.get(i).getCardSuit() == handPoker.get(i + 1).getCardSuit()) {
                flag = flag + 1;
            }
        }
        if (flag == 4) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void sortListCard() {
        Collections.sort(handPoker, (PokerCard p1, PokerCard p2) -> p1.getCardNumber() - p2.getCardNumber());
    }
}
