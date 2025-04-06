package dh_algorithm;

public class Main {
    public static void main(String[] args) {
        DHAlgorithm dhAlgorithm = new DHAlgorithm();

        dhAlgorithm.addUser("A");
        dhAlgorithm.addUser("B");

        dhAlgorithm.exchangeKeys();
    }
}
