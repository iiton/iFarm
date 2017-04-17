package org.ifarm.animals;

public class Hen extends Bird {
    public void run() {
        while(now() - birthTime < MAX_AGE_MILLIS) {
            if (readLightSensor() < 0.3) continue; // Hackable
            if (checkHealt() < 0.5) continue;      // Hackable
            if (checkEnergy() < 0.8) continue;     // Hackable

            Ovum ovum = getNextOvum();
            EggBuilder eb = new EggBuilder(ovum);
            Egg egg = eb.build(); // static, no vulnerabilities 25-26 hours, single thread, no external sensors checked.
            pushOut(egg);

            systemRepair(); // 30 to 75 minutes
        }

        goToOven();
    }
}
