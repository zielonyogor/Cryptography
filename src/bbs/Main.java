package bbs;

import utility.OutputFileWriter;

public class Main {
    public static void main(String[] args) {
        int p = 6691;
        int q = 7127;

        int n = 20000;

        BBSGenerator generator = new BBSGenerator(p, q, n);
        String bitOutput = generator.GetBBSOutput();

//        System.out.println("Output: ");
//        System.out.println(bitOutput);
        OutputFileWriter.WriteOutput("bbs_output.txt" ,bitOutput);

        System.out.println("Result of Single Bit Test: " + FIPSTest.TestSingleBit(bitOutput));
        System.out.println("Result of Series Test: " + FIPSTest.TestSeries(bitOutput));
        System.out.println("Result of Long Series Test: " + FIPSTest.TestLongSeries(bitOutput));
        System.out.println("Result of Poker Test: " + FIPSTest.TestPoker(bitOutput));

    }
}