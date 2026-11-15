package frc.robot.subsystems.transfer;

import edu.wpi.first.wpilibj.DriverStation;
// import frc.robot.subsystems.drive.Transfer.TransferIOInputsAutoLogged;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Transfer extends SubsystemBase {
  final TransferIO io;
  private final TransferIOInputsAutoLogged inputs = new TransferIOInputsAutoLogged();

  public Transfer(TransferIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Transfer", inputs);

    if (DriverStation.isDisabled()) {
      io.stop();
    }
  }

  public TransferIO getIO() {
    return io;
  }
}
