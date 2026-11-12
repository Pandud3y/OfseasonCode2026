package frc.robot.subsystems.magazine;

import edu.wpi.first.wpilibj2.command.Command;

public class MagazineCommands {

    public static Command up(double magazineVolts, Magazine magazine, MagazineIO io) {
        return magazine.runEnd(() -> io.setMagazineVoltage(magazineVolts), () -> io.stop()).until();
    }
    
}
