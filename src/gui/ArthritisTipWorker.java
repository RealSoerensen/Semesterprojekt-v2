package gui;

import javax.swing.*;
import java.util.Random;

public class ArthritisTipWorker extends SwingWorker<Void, String> {
    private final JLabel label;

    public ArthritisTipWorker(JLabel label) {
        this.label = label;
    }

    @Override
    protected Void doInBackground() throws Exception {
        Random random = new Random();
        String[] tips = {
        		"Dyrk regelmæssig motion for at holde leddene aktive.",
                "Undgå at belaste dine led ved at bruge hjælpemidler som kørestole eller krykker.",
                "Hold din vægt på et sundt niveau for at reducere belastningen på dine led.",
                "Tag regelmæssige pauser og skift position for at undgå overbelastning af leddene.",
                "Konsulter en læge eller specialist for at få en præcis diagnose og behandlingsplan.",
                "Følg din læges anbefalinger og tag medicin som ordineret.",
                "Undersøg alternative behandlingsmetoder som akupunktur eller fysioterapi.",
                "Få tilstrækkelig hvile og søvn for at give kroppen tid til at reparere og genoprette sig.",
                "Lær afslapningsteknikker som meditation eller dyb vejrtrækning for at håndtere smerte og stress.",
        };

        while (!isCancelled()) {
            String tip = tips[random.nextInt(tips.length)];
            publish(tip);
            Thread.sleep(10000);
        }

        return null;
    }

    @Override
    protected void process(java.util.List<String> chunks) {
        for (String tip : chunks) {
        	label.setText(tip);
        }
    }
}
