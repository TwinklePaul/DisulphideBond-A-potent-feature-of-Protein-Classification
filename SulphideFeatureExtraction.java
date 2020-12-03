import java.util.ArrayList;

public class SulphideFeatureExtraction {

    ArrayList<Integer> PositionSequence = new ArrayList<>();
    ArrayList<Integer> ParallelBonds = new ArrayList<>();
    ArrayList<Integer> AlternateBonds = new ArrayList<>();
    ArrayList<Integer> QuadCoreBonds = new ArrayList<>();

    int Count;
    double avgp, avga, avgq;

    public void getPositionSequence(String ProteinSequence) {
        Count = 0;
        PositionSequence.clear();

        for (Character Amino : ProteinSequence.toCharArray()) {
            Count = ProteinSequence.indexOf('C', Count);
            if (Count != -1)
                PositionSequence.add(Count);
            else
                break;
            Count++;
        }
        System.out.println("\n\nPosition Sequence:");
        //PositionSequence.forEach(System.out::println);
        System.out.println(PositionSequence);
        System.out.println();
    }


    public void getTypes(String ProteinSequence) {

        ParallelBonds.clear();
        AlternateBonds.clear();
        QuadCoreBonds.clear();

        getPositionSequence(ProteinSequence);

        int Element1, Element2;


        for (int i = 1; i < PositionSequence.size(); i += 2) {
            Element1 = PositionSequence.get(i);
            Element2 = PositionSequence.get(i - 1);
            ParallelBonds.add(Element1 - Element2);
        }


        int i = 3;
        while (i < PositionSequence.size()) {
            Element1 = PositionSequence.get(i);
            Element2 = PositionSequence.get(i - 2);
            AlternateBonds.add(Element1 - Element2);
            Element1 = PositionSequence.get(i - 1);
            Element2 = PositionSequence.get(i - 3);
            AlternateBonds.add(Element1 - Element2);
            i += 4;
            if (PositionSequence.size() == i) {
                Element1 = PositionSequence.get(i - 1);
                Element2 = PositionSequence.get(i - 3);
                AlternateBonds.add(Element1 - Element2);
            }
        }


        i = 3;
        while (i < PositionSequence.size()) {
            Element1 = PositionSequence.get(i);
            Element2 = PositionSequence.get(i - 3);
            QuadCoreBonds.add(Element1 - Element2);
            Element1 = PositionSequence.get(i - 1);
            Element2 = PositionSequence.get(i - 2);
            QuadCoreBonds.add(Element1 - Element2);

            i += 4;
            if (PositionSequence.size() == i) {
                Element1 = PositionSequence.get(i - 1);
                Element2 = PositionSequence.get(i - 2);
                QuadCoreBonds.add(Element1 - Element2);
            }
        }


        avgp = 0.0;
        avga = 0.0;
        avgq = 0.0;

        System.out.println("\nParallel Bonds" + ParallelBonds);
        //ParallelBonds.forEach(System.out::println);
        for (int X : ParallelBonds)
            avgp += X;

        if (ParallelBonds.isEmpty())
            avgp = 0;
        else
            avgp /= ParallelBonds.size();


        System.out.println("Alternate Bonds" + AlternateBonds);
        //AlternateBonds.forEach(System.out::println);
        for (int X : AlternateBonds)
            avga += X;

        if (AlternateBonds.isEmpty())
            avga = 0;
        else
            avga /= AlternateBonds.size();


        System.out.println("QuadCore Bonds" + QuadCoreBonds);
        //QuadCoreBonds.forEach(System.out::println);
        for (int X : QuadCoreBonds)
            avgq += X;

        if (QuadCoreBonds.isEmpty())
            avgq = 0;
        else
            avgq /= QuadCoreBonds.size();
    }

}




