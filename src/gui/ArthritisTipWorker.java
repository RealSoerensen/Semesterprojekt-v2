package gui;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class ArthritisTipWorker extends SwingWorker<Void, String> {
    private final JLabel label;
    private final String[] tips = {
            "Tip: Dyrk regelmæssig motion for at holde leddene aktive.",
            "Tip: Undgå at belaste dine led ved at bruge hjælpemidler som kørestole eller krykker.",
            "Tip: Hold din vægt på et sundt niveau for at reducere belastningen på dine led.",
            "Tip: Tag regelmæssige pauser og skift position for at undgå overbelastning af leddene.",
            "Tip: Konsulter en læge eller specialist for at få en præcis diagnose og behandlingsplan.",
            "Tip: Følg din læges anbefalinger og tag medicin som ordineret.",
            "Tip: Undersøg alternative behandlingsmetoder som akupunktur eller fysioterapi.",
            "Tip: Få tilstrækkelig hvile og søvn for at give kroppen tid til at reparere og genoprette sig.",
            "Tip: Lær afslapningsteknikker som meditation eller dyb vejrtrækning for at håndtere smerte og stress.",
    };

    public ArthritisTipWorker(JLabel label) {
        this.label = label;
    }

    @Override
    protected Void doInBackground() throws Exception {
        Random random = new Random();

        while (!isCancelled()) {
            publish(tips[random.nextInt(tips.length)]);
            Thread.sleep(10000);
        }

        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        for (String tip : chunks) {
        	label.setText(tip);
        }
    }
}
