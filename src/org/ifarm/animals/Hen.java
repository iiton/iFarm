package org.ifarm.animals;

/*
 * Hen system doc: http://articles.extension.org/pages/65372/avian-reproductive-systemfemale
 */

public class Hen extends Bird {
    public void run() {
        setFood(nextRandomFood());
        setEating(true);
        
        while(now() - birthTime < MAX_AGE_MILLIS) {
            if (readLightSensor() < 0.3) { // Hackable
                setEating(false);
                sleep(60000);
                continue;
            };

            if (checkHealth() < 0.5) { // Hackable
                setFood(nextRandomFood());
                continue;
            }
            
            if (checkEnergy() < 0.8) {
                setEating(true);
                continue;     // Hackable
            }

            Ovum ovum = ovumPool.poll();
            EggBuilder eb = new EggBuilder(ovum);
            
            setEating(true); // keep eating while building egg.
            Egg egg = eb.build(); // static, no vulnerabilities 25-26 hours, single thread, no external sensors checked.
            pushOut(egg);

            systemRepair(); // 30 to 75 minutes
        }

        goToOven();
    }
}
