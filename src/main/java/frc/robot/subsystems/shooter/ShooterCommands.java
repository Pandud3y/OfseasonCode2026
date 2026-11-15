package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.Command;

public class ShooterCommands {

  public static Command runVoltage(double volts, Shooter shooter) {
    return shooter.runEnd(
        () -> shooter.getIO().setShooterVoltage(volts), () -> shooter.getIO().stop());
  }

  public static Command runVelocity(double velocityRadPerSec, Shooter shooter) {
    return shooter.runEnd(
        () -> shooter.getIO().setShooterVelocity(velocityRadPerSec), () -> shooter.getIO().stop());
  }
}
