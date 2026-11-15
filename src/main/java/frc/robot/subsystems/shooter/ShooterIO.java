package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {

  @AutoLog
  public static class ShooterIOInputs {
    public double shooterVelocityRadPerSec = 0.0;
    public double shooterPositionRad = 0.0;
    public double shooterAppliedVolts = 0.0;
    public double shooterCurrentAmps = 0.0;
    public boolean shooterClosedLoop = false;
  }

  public default void updateInputs(ShooterIOInputs inputs) {}

  public default void setShooterVelocity(double velocityRadPerSec) {}

  public default void setShooterVoltage(double volts) {}

  public default void stop() {
    setShooterVoltage(0.0);
  }
}
