package dh_algorithm;

public class Main {
    public static void main(String[] args) {
        DHAlgorithm dhAlgorithm = new DHAlgorithm();

        dhAlgorithm.AddUser("A", 1);
        dhAlgorithm.AddUser("B", 2);

        dhAlgorithm.ExchangeKeys();
    }
}
