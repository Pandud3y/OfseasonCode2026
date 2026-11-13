// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;
import frc.robot.subsystems.intake.IntakeIOReal;
import frc.robot.subsystems.transfer.Transfer;
import frc.robot.subsystems.transfer.TransferIO;
import frc.robot.subsystems.transfer.TransferIOReal;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

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
public class RobotContainer {
  // Subsystems
  // private final Drive drive;
  private final Transfer transfer;
  private final Intake intake;

  // Controller
  private final CommandXboxController oppController = new CommandXboxController(0);

  // Dashboard inputs
  private final LoggedDashboardChooser<Command> autoChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        transfer = new Transfer(new TransferIOReal());
        intake = new Intake(new IntakeIOReal(null));

        break;

      case SIM:
        /*sim currently doesn't work but its not a priority
        but this needs to be changed to proper sim code when it does work*/
        transfer = new Transfer(new TransferIO() {});
        intake = new Intake(new IntakeIO() {});
        break;

      default:
        transfer = new Transfer(new TransferIO() {});
        intake = new Intake(new IntakeIO() {});

        break;
    }

    // Set up auto routines
    autoChooser = new LoggedDashboardChooser<>("Auto Choices", AutoBuilder.buildAutoChooser());

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*option for button layout:
    Left bump: shoot
    Right bump: shoot "mode" (transfer and intake move forward, magazine moves up and down)
    a: intake + transfer forward
    b: intake + transfer reverse
    y: magazine up
    x: magazine down
    left stick: drive north/south/east/west
    right stick: rotate
    OPTIONAL:
    D-pad: change from field relitive to field centric/robot centric depending on the game
    left trigger: lift up(use trigger to control speed of lift)
    right trigger: lift down
    */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.get();
  }
}
