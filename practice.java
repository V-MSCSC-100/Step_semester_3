//Question 1
class PatientRecord {
    private String patientId;
    protected String wardCode;
    private double vitalsScore;
    public String facilityName;

    public PatientRecord(
        String patientId,
        String wardCode,
        double vitalsScore,
        String facilityName
    ) {
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

public class Main {
    static String classifyAccess(
        String fieldModifier,
        String accessorContext
    ) {
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                ? "ALLOWED"
                : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                ? "ALLOWED"
                : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                ? "ALLOWED"
                : "DENIED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("default", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));

        try {
            new PatientRecord(
                "MT9",
                "W3",
                98.2,
                "MediTrack Central"
            );
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        PatientRecord p = new PatientRecord(
            "MT94",
            "W3",
            98.2,
            "MediTrack Central"
        );

        System.out.println(p.facilityName);
    }
}

//Question 2

class AccessRuleEngine {
    static String classifyAccess(
        String fieldModifier,
        String accessorContext
    ) {
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                ? "ALLOWED"
                : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                ? "ALLOWED"
                : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {
        String[] parts = accessorContext.split("_");
        StringBuilder result = new StringBuilder();

        for (String part : parts) {
            if (result.length() > 0) {
                result.append(" ");
            }

            result.append(
                part.substring(0, 1).toUpperCase()
            );

            result.append(
                part.substring(1).toLowerCase()
            );
        }

        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(
            AccessRuleEngine.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessRuleEngine.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        System.out.println(
            AccessRuleEngine.describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );
    }
}

//Question 3

class PatientVitals {
    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {
        readings = new double[500];
        count = 0;

        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading > 0 && reading <= 45 && count < 500) {
            readings[count] = reading;
            count++;
        }
    }

    public double getAverage() {
        if (count == 0) {
            return 0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }

    public double[] getAllReadings() {
        double[] result = new double[count];

        for (int i = 0; i < count; i++) {
            result[i] = readings[i];
        }

        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(
            new double[]{36.5, -2, 37.1}
        );

        double[] readings = v.getAllReadings();

        for (double reading : readings) {
            System.out.print(reading + " ");
        }

        System.out.println();

        double[] copy = v.getAllReadings();
        copy[0] = 999;

        System.out.println(v.getAllReadings()[0]);

        v.recordReading(40.0);

        System.out.println(v.getAverage());
    }
}

//Question 4

class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (patientId == null) {
            patientId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("\\d{4,6}")) {
            lockerPinHash = Integer.toHexString(pin.hashCode());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PatientProfile p1 = new PatientProfile("Arjun Iyer");

        System.out.println(p1.getPatientId());

        PatientProfile p2 =
            new PatientProfile("MT2026-0142", "Arjun Iyer");

        System.out.println(p2.getPatientId());

        PatientProfile p3 = new PatientProfile();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());

        p3.setDischarged(true);

        System.out.println(p3.isDischarged());

        p3.setLockerPin("123456");
    }
}

//Question 5

class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static {
        System.setProperty("discharge.summary.initialized", "true");
    }

    public DischargeSummary(
        String patientId,
        String[] medicationCodes
    ) {
        if (medicationCodes == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (String code : medicationCodes) {
            if (code == null || !code.matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
        int index,
        String newCode
    ) {
        if (index < 0 ||
            index >= medicationCodes.length ||
            newCode == null ||
            !newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("construction rejected");
        }

        String[] corrected = medicationCodes.clone();
        corrected[index] = newCode;

        return new DischargeSummary(patientId, corrected);
    }

    public static String processNightlyBatch(
        DischargeSummary[] summaries
    ) {
        int processed = 0;
        int skipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                skipped++;
            } else {
                processed++;

                if (summary instanceof CriticalCareDischargeSummary) {
                    criticalCare++;
                } else {
                    routine++;
                }
            }
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               criticalCare + " critical-care | " +
               routine + " routine";
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(
        String patientId,
        String[] medicationCodes,
        int icuDays
    ) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A", "bad"}
            );
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d = new DischargeSummary(
            "MT2026-0142",
            new String[]{"MED-A", "MED-B"}
        );

        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";

        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary corrected =
            d.withCorrectedMedication(0, "MED-C");

        System.out.println(corrected.getMedicationCodes()[0]);

        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary(
                "MT001",
                new String[]{"MED-X"},
                4
            ),
            null,
            new DischargeSummary(
                "MT002",
                new String[]{"MED-Y"}
            )
        };

        System.out.println(
            DischargeSummary.processNightlyBatch(summaries)
        );
    }
}
