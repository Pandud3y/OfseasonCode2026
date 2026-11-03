package frc.robot.subsystems.transfer;

import org.littletonrobotics.junction.AutoLog;

public interface TransferIO {

  @AutoLog
  public static class TransferIOInputs {
    public double transferVelocityRPM = 0.0;
    public double preShootVelocityRPM = 0.0;
  }

  public default void updateInputs(TransferIOInputs inputs) {}

  public default void setTransferVoltage(double volts) {}

  public default void setPreShootVoltage(double volts) {}
}
