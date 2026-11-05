package frc.robot.subsystems.transfer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.RunEnd;

public class TransferCommands {

public static Command transferOnly(double transferVolts) {
    return new RunEnd(
        () -> io.setTransferVoltage(transferVolts),
        () -> io.stop()
    );
  }

  public static Command preShootOnly(double preShootVolts) {
    return new RunCommand(
        () -> transfer.setPreShootVoltage(preShootVolts), transfer);
  }

  public static Command both(double transferVolts, double preShootVolts) {
    return new RunCommand(
        () -> transfer.setBoth(transferVolts, preShootVolts), transfer);
  }


    
}
