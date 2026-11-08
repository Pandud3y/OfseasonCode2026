package frc.robot.subsystems.transfer;

import edu.wpi.first.wpilibj.DriverStation;
// import frc.robot.subsystems.drive.Transfer.TransferIOInputsAutoLogged;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

/*Prayer to the Omnissiah:
Omnissiah, which art in this machine
Hallowed by thy name. Thy capacitors fill.
Thy logic be true in this function, as it is in others.
I give thee this day thine daily maintenance, and forgive us our illogical or recursive arguments, As
we forgive poorly written error messages. And lead us not into dispair,
but deliver us from evil. For thine is the
processor, the power, and the microchip, for
ever and ever. Amen.
*/

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
}
