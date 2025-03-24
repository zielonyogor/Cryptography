package dh_algorithm;

public class Main {
    public static void main(String[] args) {
        DHAlgorithm dhAlgorithm = new DHAlgorithm();

        dhAlgorithm.addUser("A", 1);
        dhAlgorithm.addUser("B", 2);

        dhAlgorithm.exchangeKeys();
    }
}
