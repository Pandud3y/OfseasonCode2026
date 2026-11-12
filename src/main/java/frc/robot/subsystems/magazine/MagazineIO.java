package frc.robot.subsystems.magazine;

import org.littletonrobotics.junction.AutoLog;

public interface MagazineIO {

  @AutoLog
  public static class MagazineIOInputs {
    public boolean isUp = false;
    public boolean isDown = false;
    public double magazineRPM = 0.0;
  }

  public default void updateInputs(MagazineIOInputs inputs) {}

  public default void setMagazineVoltage(double volts) {}

  public default boolean getUpLimitSwitch() {
    return false;
  }

  public default boolean getDownLimitSwitch() {
    return false;
  }

  public default void stop() {
    setMagazineVoltage(0.0);
  }
}
