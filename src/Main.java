import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Collection<String> list = new TreeSet<>();
//
//        String[] names = {"Anna", "Bob", "Carol", "David", "Edna"};
//        list.addAll(Arrays.asList(names));
//        System.out.println(list);
//
//        list.add("Fred");
//        list.addAll(Arrays.asList("George", "Gary", "Grace"));
//        System.out.println(list);
//        System.out.println("Gary is in the list? " + list.contains("Gary"));
//
//        list.removeIf(s -> s.charAt(0) == 'G');
//        System.out.println(list);
//        System.out.println("Gary is in the list? " + list.contains("Gary"));

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, 'A');
        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Aces of Hearts", 1);

        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("cards.size() = " + cards.size());

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
        Card.printDeck(acesOfHearts, "Aces of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, 'K');
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "Kings of Clubs", 1);

        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);
        Card.printDeck(cards, "Card Collection with Aces added", 2);

        Collections.copy(cards, kingsOfClubs);
        Card.printDeck(cards, "Card Collection with Kings copied", 2);

        cards = List.copyOf(kingsOfClubs);
        Card.printDeck(cards, "List Copy of Kings", 1);

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Collections.shuffle(deck);
        Card.printDeck(deck, "Shuffled Deck" , 4);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversed Deck of Cards:", 4);

        var sortingAlgorithm = Comparator.comparing(Card::rank)
                .thenComparing(Card::suit);

        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck, "Standard Deck sorted by rank, suit", 13);

        Collections.reverse(deck);
        Card.printDeck(deck, "Sorted by rank, suit reversed:", 13);

        List<Card> kings = new ArrayList<>(deck.subList(4, 8));
        Card.printDeck(kings, "Kings in deck", 1);

        List<Card> tens = new ArrayList<>(deck.subList(16, 20));
        Card.printDeck(tens, "Tens in deck", 1);

        int subListIndex = Collections.indexOfSubList(deck, tens);
        System.out.println("sublist index for tens = " + subListIndex);
        System.out.println("Contains = " + deck.containsAll(tens));

        boolean disjoint = Collections.disjoint(deck, tens);
        System.out.println("disjoint = " + disjoint);

        boolean disjoint2 = Collections.disjoint(kings, tens);
        System.out.println("disjoint = " + disjoint2);

        deck.sort(sortingAlgorithm); // binary search can sort lai
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        System.out.println("foundIndex = " + foundIndex);
        System.out.println("foundIndex = " + deck.indexOf(tenOfHearts));
        System.out.println(deck.get(foundIndex));

        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB, 10);
        Collections.replaceAll(deck, tenOfClubs, tenOfHearts);
        Card.printDeck(deck.subList(32, 36), "Tens row", 1);

        //Collections.replaceAll(deck, tenOfHearts, tenOfClubs);
        //Card.printDeck(deck.subList(32, 36), "Tens row", 1);

        if (Collections.replaceAll(deck, tenOfHearts, tenOfClubs)) {
            System.out.println("Tens of hearts replaced with tens of clubs");
        } else {
            System.out.println("No tens of hearts found in the list");
        }

        System.out.println("Ten of Clubs Cards = " +
                Collections.frequency(deck, tenOfClubs));

        System.out.println("Best Card = " + Collections.max(deck, sortingAlgorithm));
        System.out.println("Worst Card = " + Collections.min(deck, sortingAlgorithm));

        var sortBySuit = Comparator.comparing(Card::suit)
                .thenComparing(Card::rank);
        deck.sort(sortBySuit);
        Card.printDeck(deck, "Sorted by Suit, Rank", 4);

        List<Card> copied = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(copied, 2);
        System.out.println("UnRotated: " + deck.subList(0, 13));
        System.out.println("Rotated " + 2 + ": " + copied);

        copied = new ArrayList<>(deck.subList(0, 13));
        Collections.rotate(copied, -2);
        System.out.println("UnRotated: " + deck.subList(0, 13));
        System.out.println("Rotated " + -2 + ": " + copied);

        copied = new ArrayList<>(deck.subList(0, 13));
        for (int i = 0; i < copied.size() / 2; i++) {
            Collections.swap(copied, i, copied.size() - 1 - i);
        }
        System.out.println("Manual reverse :" + copied);

        copied = new ArrayList<>(deck.subList(0, 13));
        Collections.reverse(copied);
        System.out.println("Using reverse :" + copied);

        // set and map

        List<Contact> emails =  ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List", phones);
        printData("Email List", emails);

        System.out.println("--------------Streams api----------------");

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
//                System.out.println("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("----------------Use normal ways----------------");

//        List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf("O") == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n----------------------------------");

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("-----------------Use Stream------------");

        bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println("\n----------------------------------");

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
    }

    public static void printData(String header, Collection<Contact> contacts) {

        System.out.println("----------------------------------------------");
        System.out.println(header);
        System.out.println("----------------------------------------------");
        contacts.forEach(System.out::println);
    }
}