package frc.robot.subsystems.magazine;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class MagazineCommands {

  public static Command up(double magazineVolts, Magazine magazine, MagazineIO io) {
    return magazine
        .runEnd(() -> io.setMagazineVoltage(magazineVolts), () -> io.stop())
        .until(() -> io.getUpLimitSwitch());
  }

  public static Command down(double magazineVolts, Magazine magazine, MagazineIO io) {
    return magazine
        .runEnd(() -> io.setMagazineVoltage(-magazineVolts), () -> io.stop())
        .until(() -> io.getDownLimitSwitch());
  }

  public static Command upDown(double magazineVolts, Magazine magazine, MagazineIO io) {
    return Commands.repeatingSequence(
            down(magazineVolts, magazine, io), up(magazineVolts, magazine, io))
        .finallyDo(() -> io.stop());
  }
}
