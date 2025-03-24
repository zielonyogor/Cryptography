package bbs;

import utility.OutputFileWriter;

public class Main {
    public static void main(String[] args) {
        int p = 6691;
        int q = 7127;

        int n = 20000;

        BBSGenerator generator = new BBSGenerator(p, q, n);
        String bitOutput = generator.getBBSOutput();

        OutputFileWriter.WriteOutput("bbs_output.txt" ,bitOutput);

        System.out.println("Result of Single Bit Test: " + FIPSTest.testSingleBit(bitOutput));
        System.out.println("Result of Series Test: " + FIPSTest.testSeries(bitOutput));
        System.out.println("Result of Long Series Test: " + FIPSTest.testLongSeries(bitOutput));
        System.out.println("Result of Poker Test: " + FIPSTest.testPoker(bitOutput));

    }
}