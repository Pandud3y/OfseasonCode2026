package frc.robot.subsystems.transfer;

import edu.wpi.first.wpilibj2.command.Command;

public class TransferCommands {

  public static Command transferOnly(double transferVolts, Transfer transfer, TransferIO io) {
    return transfer.runEnd(() -> io.setTransferVoltage(transferVolts), () -> io.stop());
  } 

  public static Command preShootOnly(double preShootVolts, Transfer transfer, TransferIO io) {
    return transfer.runEnd(() -> io.setPreShootVoltage(preShootVolts), () -> io.stop());
  }

  public static Command both(
      double transferVolts, double preShootVolts, Transfer transfer, TransferIO io) {
    return transfer.runEnd(() -> io.setBoth(transferVolts, preShootVolts), () -> io.stop());
  }
}
